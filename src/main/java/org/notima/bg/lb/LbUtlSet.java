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

import java.util.Date;

import org.notima.bg.BgFooter;
import org.notima.bg.BgHeader;
import org.notima.bg.BgRecord;
import org.notima.util.NotimaUtil;

public class LbUtlSet extends AbstractLbSet {

	private int	m_bankId;
	
	public static LbUtlSet createPayableSet(String senderAccount, String senderName, String senderAddress, int bankId) {
		String sA = NotimaUtil.toDigitsOnly(senderAccount);
		BgHeader h = new LbTk0Header(sA, senderName, senderAddress);
		LbTk9Footer f = new LbTk9Footer(sA);
		LbUtlSet set = new LbUtlSet(h,f, bankId);
		return(set);
	}
	
	public LbUtlSet(BgHeader header, BgFooter footer, int bankId) {
		super(header, footer);
		header.setParentSet(this);
		footer.setParentSet(this);
		m_bankId = bankId;
	}

	@Override
	public void addCreditRecord(BgRecord creditRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreditRecordDate(String recipientBg, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBankId() {
		return m_bankId;
	}

	public void setBankId(int mBankId) {
		m_bankId = mBankId;
	}
	

}
