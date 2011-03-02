package se.notima.bg.lb;

import se.notima.bg.BgFooter;
import se.notima.bg.BgParseException;
import se.notima.bg.BgUtil;

public class LbTk9Footer extends BgFooter {
	
	private double 	totalSek;
	private int		totalRecipientsNo;
	private String	bgAccount;
	private double  totalUtl;
	
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
		line.append(BgUtil.getAmountStr(totalSek));
		// Reserve field 22-27
		line.append("      ");
		// Reserve field 28-31
		line.append("    ");
		// Nonsenstotal (all recipients no)
		if (totalRecipientsNo==0) {
			line.append("            ");
		} else {
			line.append(BgUtil.fillToLength(new Integer(totalRecipientsNo).toString(), true, ' ', 12));
		}
		line.append(BgUtil.fillToLength(new Integer(count).toString(), true, ' ', 12));
		// Reserve field 56-63
		line.append(BgUtil.fillToLength(" ", false, ' ', 8));
		totalUtl = totalUtl * 100;
		line.append(BgUtil.fillToLength(new Long(Math.round(totalUtl)).toString(), 
										true, '0', 15));

		while(line.length()<80) {
            line.append(" ");
        }

		return(line.toString());
	}

}
