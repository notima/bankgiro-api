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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import org.notima.bg.lb.AbstractLbSet;
import org.notima.bg.lb.LbPayment;
import org.notima.bg.lb.LbPaymentRecord;
import org.notima.bg.lb.LbRecordFactory;
import org.notima.bg.lb.LbSet;
import org.notima.bg.lb.LbTk11Header;
import org.notima.bg.lb.LbTk16Record;
import org.notima.bg.lb.LbTk29Record;


public class LbFile extends BgFile {

	public static final String FILE_PREFIX = "LB_ATER_";
	public static final String FILE_SUFFIX = ".txt";
	
	/**
	 * Constructs a new LbFile with an empty set.
	 */
	public LbFile() {
		records = new Vector<BgSet>();
	}
	
	/**
	 * Gets list of sets.
	 * @return A list of BgSet
	 */
	public List<BgSet> getRecords() {
		return records;
	}

	/**
	 * Adds a set to file
	 * 
	 * @param rec	The set to add
	 */
	public void addSet(AbstractLbSet rec) {
		if (records==null) {
			records = new Vector<BgSet>();
		}
		records.add(rec);
	}
	
	/**
	 * Sets records in file.
	 * @param records	The records
	 */
	public void setRecords(Vector<BgSet> records) {
		this.records = records;
	}

	/**
	 * Writes file
	 * @param	file	The file to write
	 * @param	cs		The charset to use
	 * @throws 	IOException	If file operations fail.
	 */
    public void writeToFile(File file, Charset cs) throws IOException {
        lastFile = file;
        FileOutputStream os = new FileOutputStream(file);
        writeToStream(os, cs);
    }

    /**
     * Splits the file into one file per sender
     * 
     * The files are named using the following pattern LB_ATER_XXXX_YYDDMM.txt
	 * @param src		File to split
	 * @param dir		Destination dir for split files
	 * @param cs		Character set of file to split
     * 
	 * @throws	IOException		If file can't be read / written
	 * @throws 	BgParseException	If file can't be parsed
     */
    public static void splitToFiles(File src, File dir, Charset cs) throws IOException, BgParseException {
    	
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream( src ), cs));
    	Date fileDate = null;
    	String line;
    	BgRecord record;
    	int code = 0;
    	BgRecordFactory factory = new LbRecordFactory();
    	Map<String, StringBuffer> outFiles = new TreeMap<String, StringBuffer>();
    	StringBuffer outFile = new StringBuffer();
    	while((line=reader.readLine())!=null) {
    		// Parse new line
    		record = factory.parseRecord(line);
    		outFile.append(line);
    		outFile.append("\r\n");
    		code = Integer.parseInt(record.getTransCode());
    		if (code==29) {
    			LbTk29Record rec = new LbTk29Record();
    			rec.parse(line);
    			if (outFiles.containsKey(rec.getSenderAccount())) {
    				reader.close();
    				throw new BgParseException("Sender account " + rec.getSenderAccount() + " appears more than once in the file");
    			}
    			outFiles.put(rec.getSenderAccount(), outFile);
    			outFile = new StringBuffer();
    		}
    		if (code==11 && fileDate==null) {
    			LbTk11Header rec = new LbTk11Header();
    			rec.parse(line);
    			fileDate = rec.getFileDate();
    		}
    		
    	}
    	reader.close();
    	
    	// Write files
    	FileOutputStream writer;
    	File file;
    	
    	for (String key : outFiles.keySet()) {
    		
    		file = new File(dir.getCanonicalPath() + File.separator + 
    						FILE_PREFIX + key + "_" + BgUtil.getDateString(fileDate) + FILE_SUFFIX);

    		if (file.exists()) {
    			throw new BgParseException("File " + file.getCanonicalPath() + " already exists.");
    		}
    		
            writer = new FileOutputStream(file);
    		writer.write(outFiles.get(key).toString().getBytes(cs));
    		writer.close();
    		
    	}
    	
    }
    
    /**
     * Returns the BG-senders in this file.
     * 
     * @return	A set of strings containing the BG-senders in the file
     */
    public Set<String> getBgSenders() {

    	Set<String> result = new TreeSet<String>();
    	
    	if (records==null)
    		return result;
    	
    	for(BgSet s : records) {
    		result.add(s.getSenderBankAccount());
    	}
    	
    	return result;
    	
    }
    
    /**
     * Reads a file and creates a data representation from the file.
     * @param file		The file to read
     * @param cs		Character set of file
     * @throws IOException		if file can't be read
     * @throws BgParseException		If file can't be parsed
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
    		code = Integer.parseInt(record.getTransCode());
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
    			if (currentSet==null) {
    				reader.close();
    				throw new BgParseException("Footer but no current set. Error in file.", line);
    			}
    			currentSet.setFooter((BgFooter)record);
    			// Add current set to file
    			records.add(currentSet);
    			currentSet = null;
    			completePayment = false;
    			continue;
    		}
    		// If we have no current set at this point, something is wrong.
    		if (currentSet==null) {
    			reader.close();
    			throw new BgParseException("Footer but no current set. Error in file.", line);
    		}
    		
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

	@Override
	public void writeToStream(OutputStream os, Charset cs) throws IOException {
        if (records!=null) {
            for (int i=0; i<records.size(); i++) {
                os.write(records.get(i).toRecordString().getBytes(cs));
            }
        }
        os.close();
	}

	@Override
	public BgFooter generateFileFooter() {
		return fileFooter;
	}
	
	
}
