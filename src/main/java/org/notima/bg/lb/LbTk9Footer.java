package org.notima.bg.lb;

import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.BgSet;
import org.notima.bg.BgUtil;

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
		String bga = BgUtil.toDigitsOnly(bgAccount);
		account.append(bga);
		// Left-pad with zeroes
		while(account.length()<8) {
			account.insert(0, '0');
		}
		// Sender's bank account (bankgiro)
		line.append(account);
		// Total amount in SEK
		line.append(BgUtil.getAmountStr(amount));
		// Reserve field 22-27
		line.append("      ");
		// Reserve field 28-31
		line.append("    ");
		// Nonsenstotal (all recipients no)
		if (totalRecipientsNo==0) {
			line.append("            ");
		} else {
			line.append(BgUtil.fillToLength(Integer.toString(totalRecipientsNo), true, ' ', 12));
		}
		// Number of records
		if (bankId!=LbUtlSet.BANK_HANDELSBANKEN) {
			line.append(BgUtil.fillToLength(Integer.toString(count), true, ' ', 12));
		} else {
			line.append("            ");
		}
		// Reserve field 56-63
		line.append(BgUtil.fillToLength(" ", false, ' ', 8));
		double totalUtl = foreignAmount * 100;
		line.append(BgUtil.fillToLength(Long.toString(Math.round(totalUtl)), 
										true, '0', 15));

		while(line.length()<80) {
            line.append(" ");
        }

		return(line.toString());
	}

}
