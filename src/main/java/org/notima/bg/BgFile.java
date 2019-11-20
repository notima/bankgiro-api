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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public abstract class BgFile {

	protected BgHeader	fileHeader;
	protected BgFooter	fileFooter;
	
	protected File			lastFile;
	
	/**
	 * Writes the contents to file
	 * 
	 * @param	file	The file to write
	 * @param	cs		The charset of the file
	 * @throws	IOException	if file operation fails.
	 */
    public abstract void writeToFile(File file, Charset cs) throws IOException;
	
	/**
	 * Writes the contents to a stream
	 * 
	 * @param os	The output stream to write to
	 * @param cs	The charset to use.
	 * @throws IOException	if stream operation fails.
	 */
    public abstract void writeToStream(OutputStream os, Charset cs) throws IOException;
    
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
	 * @return	The date of the file (specified in the file)
	 */
	public abstract java.util.Date getFileDate();
	
	/**
	 * 
	 * @return	The sets of records in the file
	 */
    public abstract List<BgSet> getRecords();

}
