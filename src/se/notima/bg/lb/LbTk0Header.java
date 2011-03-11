package se.notima.bg.lb;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import se.notima.bg.BgHeader;
import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgSet;
import se.notima.bg.BgUtil;

/**
 * Opening record for foreign payments
 * 
 * @author Daniel Tamm
 *
 */
public class LbTk0Header extends BgHeader {

	private String 			bgAccount = "";
	private String			name = "";
	private String			address = "";
	private java.util.Date	fileDate;
	private java.util.Date	payDate;	// Leave as null unless all records in this section have the same paydate
	private String			currency = "";

	private static Pattern	linePattern1 = Pattern.compile("11(\\d{10})(\\d{6})LEV.*");
	private static Pattern	linePattern2 = Pattern.compile(".*?BETALNINGAR(.{6}).{13}(.{3}).*");
	
	/**
	 * Creates a new header using current time as file date.
	 * @param senderAccount
	 */
	public LbTk0Header(String senderAccount, String aName, String aAddress) {
		super("0");
		bgAccount = senderAccount;
		setHeaderType("LEVERANT\u00d6RSBETALNINGAR");
		fileDate = Calendar.getInstance().getTime();
		name = aName;
		address = aAddress;
	}
	
	@Override
	public Date getCreateDate() {
		return fileDate;
	}

	@Override
	public String getCurrency() {
		return null;
	}

	@Override
	public Date getPayDate() {
		return payDate;
	}

	@Override
	public String getSenderAccount() {
		return bgAccount;
	}

	@Override
	public void setCurrency(String currency) {
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		return null;
	}

	@Override
	public String toRecordString() {
		// Get parent set if any
		int bankId = 0;
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
		line.append(account);
		
		if (fileDate==null) {
			fileDate = Calendar.getInstance().getTime();
		}
		String dateStr = BgUtil.getDateString(fileDate);
		line.append(dateStr);
		// Name of sender
		line.append(BgUtil.fillToLength(name.toUpperCase(), false, ' ', 23));
		// Address of sender
		line.append(BgUtil.fillToLength(address.toUpperCase(), false, ' ', 35));
		if (payDate!=null) {
			line.append(BgUtil.getDateString(payDate));
		} else {
			line.append("      ");
		}
		// Layout code
		if (bankId==LbUtlSet.BANK_HANDELSBANKEN) {
			line.append("2"); // Always 2 on HB
		} else {
			if (bga.length()==8) {
				line.append("2");
			} else {
				line.append("1");
			}
		}
        while(line.length()<80) {
            line.append(" ");
        }
		
		return(line.toString());
	}

}
