package se.notima.bg.lb;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

public class LbTk7Record extends BgRecord {

	private int	m_recipientNo;
	private int	m_bankCode;	// Betalningskod i Visma
	private String	m_hbAccountNo; // Used for payments in handelsbanken	
	
	
	public LbTk7Record(int recipientNo, int bankCode, String hbAccountNo) {
		super("7");
		m_recipientNo = recipientNo;
		m_bankCode = bankCode;
		m_hbAccountNo = hbAccountNo;
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
		line.append(BgUtil.fillToLength(new Integer(m_bankCode).toString(), true, '0', 3));
		if (m_hbAccountNo!=null && m_hbAccountNo.trim().length()>0) {
			line.append("         DSE");
			line.append(BgUtil.toDigitsOnly(m_hbAccountNo));
		}
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
