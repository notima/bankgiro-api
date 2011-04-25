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

package se.notima.bg.lb;

import se.notima.bg.*;

public class LbRecordFactory implements BgRecordFactory {

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
			case 11:
				record = new LbTk11Header();
				break;
			case 14:
				record = new LbTk14Record();
				break;
			case 16:
				record = new LbTk16Record();
				break;
			case 20:
				record = new LbTk20Record();
				break;
			case 21:
				record = new LbTk21Record();
				break;
			case 25:
				record = new LbTk25Record();
				break;
			case 26:
				record = new LbTk26Record();
				break;
			case 29:
				record = new LbTk29Record();
				break;
			case 40:
				record = new LbTk40Record();
				break;
			case 54:
				record = new LbTk54Record();
				break;
			case 65:
				record = new LbTk65Record();
				break;
			default:
				throw new BgParseException("No parser for line " + line);
		}
		record.parse(line);
		return(record);
		
	}
	
}
