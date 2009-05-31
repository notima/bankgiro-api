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

package se.notima.bg;

import java.util.*;
import java.io.*;
import java.nio.charset.Charset;

import se.notima.bg.lb.LbPayment;
import se.notima.bg.bgmax.*;

public class BgMaxFile extends BgFile {

	private List<BgSet>	records = new Vector<BgSet>();
	private BgHeader	fileHeader;
	private BgFooter	fileFooter;
	
	@Override
	public void readFromFile(File file, Charset cs) throws IOException,
			BgParseException {
		lastFile = file;

    	BufferedReader reader = new BufferedReader(new FileReader(file));
    	String line;
    	BgMaxSet currentSet = null;
    	Transaction	currentTrans = null;
    	BgRecord record;
    	int code = 0;
    	// Read first line
    	line = reader.readLine();
    	if (line==null) throw new BgParseException("File is empty");
    	BgRecordFactory factory = new BgMaxRecordFactory();
    	fileHeader = (BgHeader)factory.parseRecord(line);
    	
    	while((line=reader.readLine())!=null) {
    		record = factory.parseRecord(line);
    		code = new Integer(record.getTransCode()).intValue();
    		if (code==1) {
    			// Create new set since it's the beginning of the file.
    			currentSet = new BgMaxSet((BgMaxTk01Header)record);
    			// Save the file header
    			fileHeader = (BgHeader)record;
    		}
    		if (code==5) {
    			// Create new set if none is already created
    			if (currentSet==null) {
    				currentSet = new BgMaxSet((BgMaxTk01Header)fileHeader);
    			}
    			currentSet.setSetHeader((BgMaxTk05Record)record);
    			continue;
    		}
    		if (code==15) {
    			if (currentSet==null) throw new BgParseException("Footer but no current set. Error in file.", line);
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

    @Override
    public List<BgSet> getRecords() {
        return(records);
    }

	@Override
	public void writeToFile(File file, Charset cs) throws IOException {
		lastFile = file;
	}

	@Override
	public Date getFileDate() {
		return(fileHeader.getCreateDate());
	}

}
