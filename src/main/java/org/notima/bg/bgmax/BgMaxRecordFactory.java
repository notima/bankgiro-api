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


package org.notima.bg.bgmax;

import org.notima.bg.*;

public class BgMaxRecordFactory implements BgRecordFactory {

	/**
	 * Parses a line and returns the record
	 * @param line	The text line to be parsed
	 * @return	A BgRecord
	 * @throws BgParseException
	 */
	public BgRecord parseRecord(String line) throws BgParseException {
		if (line==null || line.trim().length()<2) return null;
		BgRecord record = null;
		String transCodeStr = line.substring(0,2);
		int transCode = Integer.parseInt(transCodeStr);
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
