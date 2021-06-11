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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * The BgFile class is the top abstraction of Bankgirot's files. All files sharing the same structure should implement this class.
 * 
 * @author Daniel Tamm
 *
 */
public abstract class BgFile {

	public static Charset BGFILE_CHARSET = Charset.forName("Cp850");
	
	protected BgHeader	fileHeader;
	protected BgFooter	fileFooter;
	
	protected List<BgSet>	records;
	protected BgSet			currentSet;
	
	protected File			lastFile;
	
	/**
	 * 
	 * @return	The date of the file (specified in the file)
	 */
	public abstract java.util.Date getFileDate();
	
	
	/**
	 * Updates the file footer according to the contents of the file.
	 * 
	 * @return		The updated filefooter
	 */
	public abstract BgFooter generateFileFooter();
	
	/**
	 * Adds a BG set to this file and sets it to current.
	 * 
	 * @param newSet		The set to be added
	 */
	public void addBgSet(BgSet newSet) {
		if (newSet==null) return;
		
		if (records==null) {
			records = new ArrayList<BgSet>();
		}
		
		records.add(newSet);
		currentSet = newSet;
	}
	
	/**
	 * 
	 * @param record
	 * @throws Exception
	 */
	public void addTransactionToCurrentSet(Transaction trans) throws Exception {
		if (currentSet==null) throw new Exception("There's no current set.");
		currentSet.addTransaction(trans);
	}
	
	/**
	 * Writes the contents to file
	 * 
	 * @param	file	The file to write
	 * @param	cs		The charset of the file
	 * @throws	IOException	if file operation fails.
	 */
	public void writeToFile(File file, Charset cs) throws IOException {
		lastFile = file;
		OutputStream os = new FileOutputStream(file);
		writeToStream(os, cs);
	}
    
    /**
     * Writes the contents of the file to a string.
     * 
     * @param cs	Charset. If null, the default charset for BG is used.
     * @return		The contents of the file as a String.
     * @throws IOException		
     */
    public String writeToString(Charset cs) throws IOException {
    	if (cs == null) cs = BGFILE_CHARSET;
    	ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	writeToStream(stream, cs);
    	String result = new String(stream.toByteArray());
    	return result;
    }
    
	/**
	 * Writes class' contents to an output stream
	 * 
	 * @param	os	The stream to write to
	 * @param	cs	Charset to use
	 */
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
    
    /**
     * Reads a file and creates a data representation from the file.
     * @param file		The file to read from
     * @param cs		The charset in file
     * @throws IOException		If file operations fail.
     * @throws BgParseException		if the file can't be parsed.
     */
    public abstract void readFromFile(File file, Charset cs) throws IOException, BgParseException;
	
	/**
	 * 
	 * @return	The header of the file
	 */
	public BgHeader getFileHeader() {
		return fileHeader;
	}

	/**
	 * Sets the file header
	 * @param fileHeader	The header
	 */
	public void setFileHeader(BgHeader fileHeader) {
		this.fileHeader = fileHeader;
	}

	/**
	 * 
	 * @return	The footer of the file
	 */
	public BgFooter getFileFooter() {
		return fileFooter;
	}

	/**
	 * Sets the file footer
	 * @param fileFooter	The footer
	 */
	public void setFileFooter(BgFooter fileFooter) {
		this.fileFooter = fileFooter;
	}

	/**
	 * Returns the last file used with this object (if any)
	 * 
	 * @return	Last file used with this object.
	 */
	public File getFile() {
		return(lastFile);
	}
	
	/**
	 * 
	 * @return	The sets of records in the file
	 */
    public List<BgSet> getRecords() {
    	return records;
    }

}
