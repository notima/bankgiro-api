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

import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.BgSet;
import org.notima.util.NotimaUtil;

public class LbTk9Footer extends BgFooter {
	
	private int		totalRecipientsNo;
	private String	bgAccount;
	private int		bankId = 0;
	
	public LbTk9Footer(String senderAccount) {
		super("9");
		bgAccount = senderAccount;
	}

	@Override
	public BgFooter parse(String line) throws BgParseException {
		return null;
	}

	@Override
	public String toRecordString() {
		// Get parent set if any
		BgSet parentSet = this.getParentSet();
		if (parentSet!=null && parentSet instanceof LbUtlSet) {
			bankId = ((LbUtlSet)parentSet).getBankId();
		}
		
		// Create "post-record"
		StringBuffer line = new StringBuffer(transCode);
		
		// Create bg-account
		StringBuffer account = new StringBuffer();
		String bga = NotimaUtil.toDigitsOnly(bgAccount);
		account.append(bga);
		// Left-pad with zeroes
		while(account.length()<8) {
			account.insert(0, '0');
		}
		// Sender's bank account (bankgiro)
		line.append(account);
		// Total amount in SEK
		line.append(NotimaUtil.getAmountStr(amount));
		// Reserve field 22-27
		line.append("      ");
		// Reserve field 28-31
		line.append("    ");
		// Nonsenstotal (all recipients no)
		if (totalRecipientsNo==0) {
			line.append("            ");
		} else {
			line.append(NotimaUtil.fillToLength(Integer.toString(totalRecipientsNo), true, ' ', 12));
		}
		// Number of records
		if (bankId!=LbUtlSet.BANK_HANDELSBANKEN) {
			line.append(NotimaUtil.fillToLength(Integer.toString(count), true, ' ', 12));
		} else {
			line.append("            ");
		}
		// Reserve field 56-63
		line.append(NotimaUtil.fillToLength(" ", false, ' ', 8));
		double totalUtl = foreignAmount * 100;
		line.append(NotimaUtil.fillToLength(Long.toString(Math.round(totalUtl)), 
										true, '0', 15));

		while(line.length()<80) {
            line.append(" ");
        }

		return(line.toString());
	}

}
