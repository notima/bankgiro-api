/** ===================================================================
	Bankgiro Java API
    
    Copyright (C) 2009  Daniel Tamm
                        Notima Consulting Group Ltd

    This API-library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This API-library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this API-library.  If not, see <http://www.gnu.org/licenses/>.

    =================================================================== */

package org.notima.bg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import org.notima.bg.bgmax.BgMaxReceipt;
import org.notima.bg.bgmax.BgMaxRecordFactory;
import org.notima.bg.bgmax.BgMaxSet;
import org.notima.bg.bgmax.BgMaxTk01Header;
import org.notima.bg.bgmax.BgMaxTk05Record;
import org.notima.bg.bgmax.BgMaxTk15Record;
import org.notima.bg.bgmax.BgMaxTk20Record;
import org.notima.bg.bgmax.BgMaxTk21Record;
import org.notima.bg.bgmax.BgMaxTk22Record;
import org.notima.bg.bgmax.BgMaxTk23Record;
import org.notima.bg.bgmax.BgMaxTk70Footer;

public class BgMaxFile extends BgFile {

	private List<BgSet>	records = new Vector<BgSet>();

	/**
	 * Empty constructor
	 */
	public BgMaxFile() {
	}

	/**
	 * Constructor that initializes the file
	 */
	public BgMaxFile(java.util.Date createDate) {
		
		BgMaxTk01Header header = new BgMaxTk01Header();
		header.setCreateDate(createDate);
		fileHeader = header;
		
	}
	
	
	/**
	 * Returns a set of bg recipients in the file.
	 * Good for checking that the file belongs to the correct customer / account.
	 * 
	 * @return
	 */
	public Set<String> getBgRecipients() {

		Set<String> result = new TreeSet<String>();

		for (BgSet s : records) {
			result.add(s.getRecipientBankAccount());
		}
		
		return result;
		
	}
	
	/**
	 * Reads BGMax information from a file. Charset is mandatory. Normally the fileset is Cp850
	 * Charset cs = Charset.forName("Cp850"); 
	 * 
	 */
	@Override
	public void readFromFile(File file, Charset cs) throws IOException,
			BgParseException {
		lastFile = file;

    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream( file ), cs));
    	String line;
    	BgMaxSet currentSet = null;
    	Transaction	currentTrans = null;
    	BgRecord record;
    	int code = 0;
    	// Read first line
    	line = reader.readLine();
    	if (line==null) {
    		reader.close();
    		throw new BgParseException("File is empty");
    	}
    	BgRecordFactory factory = new BgMaxRecordFactory();
    	fileHeader = (BgHeader)factory.parseRecord(line);
    	
    	while((line=reader.readLine())!=null) {
    		record = factory.parseRecord(line);
    		code = new Integer(record.getTransCode()).intValue();
    		if (code==1) {
    			// Create new set since it's the beginning of the file.
    			currentSet = new BgMaxSet();
    			// Save the file header
    			fileHeader = (BgHeader)record;
    		}
    		if (code==5) {
    			// Create new set if none is already created
    			if (currentSet==null) {
    				currentSet = new BgMaxSet();
    			}
    			currentSet.setSetHeader((BgMaxTk05Record)record);
    			continue;
    		}
    		if (code==15) {
    			if (currentSet==null) {
    				reader.close();
    				throw new BgParseException("Footer but no current set. Error in file.", line);
    			}
    			// Add current (last) transaction
    			if (currentTrans!=null) {
    				currentSet.addTransaction(currentTrans);
    			}
    			currentSet.setSetFooter((BgMaxTk15Record)record);
    			// Add current set to file
    			records.add(currentSet);
    			currentSet = null;
    			currentTrans = null;
    			continue;
    		}
    		if (code==70) {
    			fileFooter = (BgFooter)record;
    			break; // File done
    		}
    		if (currentSet==null) {
    			reader.close();
    			throw new BgParseException("No current set. Error in file.", line);
    		}
    		if (code==20 || code==21) {
    			// If there's already a current transaction, add it to the set and create a new
    			if (currentTrans!=null) {
    				currentSet.addTransaction(currentTrans);
    			}
				currentTrans = new BgMaxReceipt(fileHeader.getPayDate());
    		}
    		// Add current line to transaction
    		currentTrans.addRecord(record);
    	}
    	reader.close();
    	
		
	}

	public BgFooter getFileFooter() {
		return(fileFooter);
	}

	public BgFooter generateFileFooter() {
		if (fileFooter==null) {
			fileFooter = new BgMaxTk70Footer();
		}
		BgMaxTk70Footer footer = (BgMaxTk70Footer)fileFooter;
		
		int sets = records.size();
		int payments = 0;
		int reductions = 0;
		
		for (BgSet bg : records) {
			for (Transaction t : bg.getRecords()) {
				if (t instanceof BgMaxTk20Record || 
					t instanceof BgMaxTk22Record ||
					t instanceof BgMaxTk23Record) {
					payments++;
				}
				if (t instanceof BgMaxTk21Record) {
					reductions++;
				}
			}
		}

		footer.setCountReceipts(payments);
		footer.setCountReductions(reductions);
		footer.setCountWhat(sets);
		
		return fileFooter;
	}
	
	/**
	 * Creates a new bgMaxSet for this file and adds it to the files set.
	 * 
	 * @return		The newly created BgMaxSet.
	 */
	public BgMaxSet createSet(Date setDate, String currency, String clearing, String bankAccount) {
		
		BgMaxSet result = new BgMaxSet(setDate, currency, clearing, bankAccount);
		records.add(result);
		return result;
		
	}
	
    @Override
    public List<BgSet> getRecords() {
        return(records);
    }

	@Override
	public void writeToFile(File file, Charset cs) throws IOException {
		lastFile = file;
		OutputStream os = new FileOutputStream(file);
		writeToStream(os, cs);
	}
	
	

	@Override
	public Date getFileDate() {
		return(fileHeader.getCreateDate());
	}

	@Override
	public void writeToStream(OutputStream os, Charset cs) throws IOException {

		if (fileFooter==null)
			generateFileFooter();
		
		os.write(getFileHeader().toRecordString().getBytes(cs));
		os.write('\n');
		
        if (records!=null) {
            for (int i=0; i<records.size(); i++) {
                os.write(records.get(i).toRecordString().getBytes(cs));
            }
        }
        
        os.write(getFileFooter().toRecordString().getBytes(cs));
        os.write('\n');
        
        os.close();
		
	}

}
