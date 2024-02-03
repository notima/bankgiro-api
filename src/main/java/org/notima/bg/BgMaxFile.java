/** ===================================================================
	Bankgiro Java API
    
    Copyright (C) 2009  Daniel Tamm
						Notima Consulting Group Ltd
						
	Copyright (C) 2019  Notima System Integration AB

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.						

    =================================================================== */

package org.notima.bg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.notima.bg.bgmax.BgMaxReceipt;
import org.notima.bg.bgmax.BgMaxRecordFactory;
import org.notima.bg.bgmax.BgMaxSet;
import org.notima.bg.bgmax.BgMaxTk01Header;
import org.notima.bg.bgmax.BgMaxTk05Record;
import org.notima.bg.bgmax.BgMaxTk15Record;
import org.notima.bg.bgmax.BgMaxTk70Footer;

public class BgMaxFile extends BgFile {

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
	 * @return	A set of bg recipients in the file
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
	 * @param	file	The file to read
	 * @param	cs		Charset of file
	 */
	@Override
	public void readFromFile(File file, Charset cs) throws IOException,
			BgParseException {
		lastFile = file;

    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream( file ), cs));
    	String line;
    	BgMaxSet currentSet = null;
    	BgTransaction	currentTrans = null;
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
    		code = Integer.parseInt(record.getTransCode());
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
    			this.addBgSet(currentSet);
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

	/**
	 * @return	FileFooter
	 */
	public BgFooter getFileFooter() {
		return(fileFooter);
	}

	/**
	 * @return	A generated file footer
	 */
	@Override
	public BgFooter generateFileFooter() {
		if (fileFooter==null) {
			fileFooter = new BgMaxTk70Footer();
		}
		BgMaxTk70Footer footer = (BgMaxTk70Footer)fileFooter;
		
		int sets = records.size();
		int payments = 0;
		int reductions = 0;
		
		BgTransaction bgt;

		for (BgSet bg : records) {
			for (Transaction t : bg.getRecords()) {
				if (t instanceof BgTransaction) {
					bgt = (BgTransaction)t;
					if (bgt.getAmount()>0) {
						payments++;
					} else {
						reductions++;
					}
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
	 * @param	setDate			The reconciliation date of the set
	 * @param	currency		The currency of containing transactions
	 * @param	clearing		The clearing number of the receiving bank account
	 * @param	bankAccount		The account number of the bank account
	 * @param	bgAccount		The bankgiro account paid to
	 * 
	 * @return		The newly created BgMaxSet.
	 */
	public BgMaxSet createSet(Date setDate, String currency, String clearing, String bankAccount, String bgAccount) {
		
		BgMaxSet result = new BgMaxSet(setDate, currency, clearing, bankAccount, bgAccount);
		records.add(result);
		return result;
		
	}
	
	
	/**
	 * Gets the date of the file (as specified in the file header)
	 * @return	Date of file
	 */
	@Override
	public Date getFileDate() {
		return(fileHeader.getCreateDate());
	}


}
