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

package se.notima.bg.bgmax;

import se.notima.bg.*;

public class BgMaxRecordFactory implements BgRecordFactory {

	/**
	 * Parses a line and returns the record
	 * @param line
	 * @return
	 * @throws BgParseException
	 */
	public BgRecord parseRecord(String line) throws BgParseException {
		if (line==null || line.trim().length()<2) return null;
		BgRecord record = null;
		String transCodeStr = line.substring(0,2);
		int transCode = new Integer(transCodeStr).intValue();
		switch (transCode) {
			case 01: 
				record = new BgMaxTk01Header();
				break;
			case 05:
				record = new BgMaxTk05Record();
				break;
			case 15:
				record = new BgMaxTk15Record();
				break;
			case 20:
				record = new BgMaxTk20Record();
				break;
			case 21:
				record = new BgMaxTk21Record();
				break;
			case 22:
				record = new BgMaxTk22Record();
				break;
			case 23:
				record = new BgMaxTk23Record();
				break;
			case 25:
				record = new BgMaxTk25Record();
				break;
			case 26:
				record = new BgMaxTk26Record();
				break;
			case 27:
				record = new BgMaxTk27Record();
				break;
			case 28:
				record = new BgMaxTk28Record();
				break;
			case 29:
				record = new BgMaxTk29Record();
				break;
			case 70:
				record = new BgMaxTk70Footer();
				break;
			default:
				throw new BgParseException("Record not valid for this type of file", line);
		}
		record.parse(line);
		return(record);
		
	}
	
	
}
