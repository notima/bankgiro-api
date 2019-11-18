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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public abstract class BgFile {

	protected BgHeader	fileHeader;
	protected BgFooter	fileFooter;
	
	protected File			lastFile;
	
    public abstract void writeToFile(File file, Charset cs) throws IOException;
    
    public abstract void writeToStream(OutputStream os, Charset cs) throws IOException;
    
    /**
     * Reads a file and creates a data representation from the file.
     * @param file
     * @param cs
     * @throws IOException
     * @throws BgParseException
     */
    public abstract void readFromFile(File file, Charset cs) throws IOException, BgParseException;
    
	public BgHeader getFileHeader() {
		return fileHeader;
	}

	public void setFileHeader(BgHeader fileHeader) {
		this.fileHeader = fileHeader;
	}

	public BgFooter getFileFooter() {
		return fileFooter;
	}

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

	public abstract java.util.Date getFileDate();
	
    public abstract List<BgSet> getRecords();

}
