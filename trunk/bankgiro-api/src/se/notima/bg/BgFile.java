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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;




public abstract class BgFile {

	protected File			lastFile;
	
    public abstract void writeToFile(File file, Charset cs) throws IOException;
    
    /**
     * Reads a file and creates a data representation from the file.
     * @param file
     * @param cs
     * @throws IOException
     * @throws BgParseException
     */
    public abstract void readFromFile(File file, Charset cs) throws IOException, BgParseException;
	
	/**
	 * Returns the last file used with this object (if any)
	 * 
	 * @return
	 */
	public File getFile() {
		return(lastFile);
	}

	public abstract java.util.Date getFileDate();
	
    public abstract List<BgSet> getRecords();

}
