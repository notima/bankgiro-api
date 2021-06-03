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
import org.notima.bg.BgUtil;
import org.notima.util.NotimaUtil;

public class LbTk6Record extends BgRecord {

	private int	m_recipientNo;
	private String	m_invoiceRef;
	private String	m_currencyCode;
	private double 	m_amount;
	private java.util.Date	m_payDate;
	private double	m_foreignAmount;
	
	
	public LbTk6Record(int recipientNo, String invoiceRef, double amount, String currencyCode, java.util.Date payDate, double foreignAmount) {
		super("6");
		m_recipientNo = recipientNo;
		m_invoiceRef = invoiceRef;
		m_amount = amount;
		m_currencyCode = currencyCode;
		m_payDate = payDate;
		m_foreignAmount = foreignAmount;
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
		line.append(NotimaUtil.fillToLength(m_invoiceRef, false, ' ', 25));
		// Kundbelopp - SEK (optional)
		if (m_amount!=0) {
			line.append(NotimaUtil.getAmountStr(m_amount, 11, false));
		} else {
			// Leave empty if zero
			line.append("           ");
		}
		// Valutakonto TODO: Implement
		// line.append("0000000000");
		// HB
		line.append("          ");
		// Currency code
		line.append(m_currencyCode.substring(0, 3).toUpperCase());
		// Pay date
		line.append(BgUtil.getDateString(m_payDate));
		// Kod
		line.append(" ");
		// Reserve
		line.append("0");
		// Invoice amount in currency Code
		line.append(BgUtil.getAmountStr(m_foreignAmount, 13, false));
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
