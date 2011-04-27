package se.notima.bg.lb;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

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
		line.append(BgUtil.fillToLength(new Integer(m_recipientNo).toString(), 
				true, '0', 7)); // Pos 2-8
		line.append(BgUtil.fillToLength(m_invoiceRef, false, ' ', 25));
		// Kundbelopp - SEK
		line.append(BgUtil.getAmountStr(m_amount, 11, false));
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
