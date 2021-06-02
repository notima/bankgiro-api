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

public class LbTk2Record extends BgRecord {

	private int	m_recipientNo;
	private String	m_name1;
	private String	m_name2;
	
	public LbTk2Record(int recipientNo, String name1, String name2) {
		super("2");
		m_recipientNo = recipientNo;
		m_name1 = NotimaUtil.onlyUSASCII(name1);
		m_name2 = NotimaUtil.onlyUSASCII(name2);
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		return null;
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());
		line.append(NotimaUtil.fillToLength(Integer.toString(m_recipientNo),
				true, '0', 7));
		line.append(NotimaUtil.fillToLength(m_name1.toUpperCase(), false, ' ', 30));
		line.append(NotimaUtil.fillToLength(m_name2.toUpperCase(), false, ' ', 35));
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
