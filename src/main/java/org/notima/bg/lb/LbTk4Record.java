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

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.util.NotimaUtil;

public class LbTk4Record extends BgRecord {

	private int	m_recipientNo;
	private String	m_swift;
	private String	m_iban;
	
	public LbTk4Record(int recipientNo, String swift, String iban) {
		super("4");
		m_recipientNo = recipientNo;
		m_swift = swift;
		m_iban = iban;
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		return null;
	}

	@Override
	public String toRecordString() {

		// Handelsbanken format
		StringBuffer line = new StringBuffer(getTransCode()); // Pos 1
		line.append(NotimaUtil.fillToLength(Integer.toString(m_recipientNo), 
				true, '0', 7)); // Pos 2-8
		line.append(NotimaUtil.fillToLength(m_iban.toUpperCase(), false, ' ', 59)); // Pos 9-67
		line.append(NotimaUtil.fillToLength(m_swift.toUpperCase(), false, ' ', 11)); // Pos 68-78
		// HB
		line.append("BP");
		
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
