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


package org.notima.bg.lb;

import org.notima.bg.*;

public class LbRecordFactory implements BgRecordFactory {

	/**
	 * Parses a line and returns the record
	 * @param line
	 * @return	The parsed line as a BgRecord
	 * @throws BgParseException
	 */
	public BgRecord parseRecord(String line) throws BgParseException {
		if (line==null || line.trim().length()<2) return null;
		BgRecord record = null;
		String transCodeStr = line.substring(0,2);
		int transCode = Integer.parseInt(transCodeStr);
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
