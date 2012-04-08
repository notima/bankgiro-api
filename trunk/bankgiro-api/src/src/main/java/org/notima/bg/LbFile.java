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

import java.util.*;
import java.io.*;
import java.nio.charset.Charset;

import org.notima.bg.lb.AbstractLbSet;
import org.notima.bg.lb.LbPayment;
import org.notima.bg.lb.LbPaymentRecord;
import org.notima.bg.lb.LbRecordFactory;
import org.notima.bg.lb.LbSet;
import org.notima.bg.lb.LbTk16Record;


public class LbFile extends BgFile {

	private List<BgSet>	records;
	
	public LbFile() {
		records = new Vector<BgSet>();
	}
	
	public List<BgSet> getRecords() {
		return records;
	}

	public void addSet(AbstractLbSet rec) {
		if (records==null) {
			records = new Vector<BgSet>();
		}
		records.add(rec);
	}
	
	public void setRecords(Vector<BgSet> records) {
		this.records = records;
	}

    public void writeToFile(File file, Charset cs) throws IOException {
        FileOutputStream writer = new FileOutputStream(file);
        if (records!=null) {
            for (int i=0; i<records.size(); i++) {
                writer.write(records.get(i).toRecordString().getBytes(cs));
            }
        }
        writer.close();
        lastFile = file;
    }

    /**
     * Reads a file and creates a data representation from the file.
     * @param file
     * @param cs
     * @throws IOException
     * @throws BgParseException
     */
    public void readFromFile(File file, Charset cs) throws IOException, BgParseException {
    	lastFile = file;
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream( file ), cs));
    	String line;
    	LbSet currentSet = null;
    	LbPayment	currentPayment = null;
    	BgRecord record;
    	boolean completePayment = false;
    	int code = 0;
    	BgRecordFactory factory = new LbRecordFactory();
    	while((line=reader.readLine())!=null) {
    		// Parse new line
    		record = factory.parseRecord(line);
    		// Read code from parsed record
    		code = new Integer(record.getTransCode()).intValue();
    		// If newly parsed record is a "comment record", add it to the current payment
    		if ((code==25 || code==65) && currentPayment!=null) {
    			// Comments attached at the bottom of the current payment
    			currentPayment.addRecord(record);
    			continue;
    		}
    		// If we have a current payment which is complete, add this now as a transaction 
    		if (code!=25 && code!=65 && code!=21 && code!=20 && completePayment) {
        		// Add the payment to current set when complete
    			currentSet.addTransaction(currentPayment);
    			currentPayment = null; // Set to null for safety.
    		}
    		// Continue and check the newly parsed record
    		// If the record is a header, create a new set using the record
    		if (record instanceof BgHeader) {
    			// Create new set
    			currentSet = new LbSet((BgHeader)record, null);
    			continue;
    		}
    		// If the record is a footer, close the set using the record
    		if (record instanceof BgFooter) {
    			if (currentSet==null) throw new BgParseException("Footer but no current set. Error in file.", line);
    			currentSet.setFooter((BgFooter)record);
    			// Add current set to file
    			records.add(currentSet);
    			currentSet = null;
    			continue;
    		}
    		// If we have no current set at this point, something is wrong.
    		if (currentSet==null) throw new BgParseException("Footer but no current set. Error in file.", line);
    		
    		// Check if credit description
    		if (code==21 || code==20) {
    			currentSet.addCreditRecord(record);
    			continue;
    		}
    		
    		// If we have no current payment at this point, create one here.
    		if (currentPayment==null) {
    			currentPayment = new LbPayment();
    			completePayment = false;
    		}
    		// Set pay date from set if not individually in the record (or if credit record)
    		if (record instanceof LbPaymentRecord) {
    			LbPaymentRecord r = (LbPaymentRecord)record;
    			if (r.getPayDate()==null || r instanceof LbTk16Record) {
    				((LbPaymentRecord)record).setPayDate(currentSet.getPayDate());
    			}
    		}
    		// Add current line to payment
    		currentPayment.addRecord(record);
    		
    		// Check if the payment is complete
    		if (code==14 || code==15 || code==16 || code==17 || code==54) {
    			completePayment = true;
    		}
    	}
    	reader.close();
    }
    
	public String toFileString() {
		StringBuffer fileBuf = new StringBuffer();
		if (records!=null) {
			for (int i=0; i<records.size(); i++) {
				fileBuf.append(records.get(i).toRecordString());
			}
		}
		return(fileBuf.toString());
	}

	public java.util.Date getFileDate() {
		if (records!=null && records.size()>0) {
			return(((LbSet)records.get(0)).getHeader().getCreateDate());
		} else {
			return(null);
		}
	}
	
	
}
