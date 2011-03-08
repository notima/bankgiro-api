package se.notima.bg.lb;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

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
		line.append(BgUtil.fillToLength(new Integer(m_recipientNo).toString(), 
				true, '0', 7)); // Pos 2-8
		line.append(BgUtil.fillToLength(m_iban.toUpperCase(), false, ' ', 59)); // Pos 9-67
		line.append(BgUtil.fillToLength(m_swift.toUpperCase(), false, ' ', 11)); // Pos 68-78
		// HB
		line.append("BP");
		
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
