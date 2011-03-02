package se.notima.bg.lb;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

public class LbTk3Record extends BgRecord {

	private int	m_recipientNo;
	private String	m_adress;
	private String	m_postal;
	private String	m_countryCode;
	private String	m_countryName;
	private String	m_bankCountryCode;
	
	public LbTk3Record(int recipientNo, String address, String postal, String countryCode) {
		super("3");
		m_recipientNo = recipientNo;
		m_adress = address;
		m_postal = postal;
		m_countryCode = countryCode;
		// Lookup country name
		m_countryName = "";
		m_postal += " " + m_countryName;
		// Lookup bank country code
		m_bankCountryCode = "  ";
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		return null;
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());
		line.append(BgUtil.fillToLength(new Integer(m_recipientNo).toString(),
				true, '0', 7));
		line.append(BgUtil.fillToLength(m_adress.toUpperCase(), false, ' ', 30));
		line.append(BgUtil.fillToLength(m_postal.toUpperCase(), false, ' ', 35));
		// Reserve field 74
		line.append(" ");
		// Bank country code
		line.append(m_bankCountryCode);
		// Reserve field 77
		line.append(" ");
		// Cost on sender or recipient 78
		line.append(" ");
		// Payment Form 79
		line.append(" ");
		// Payment method (Express etc) 80
		line.append(" ");
		
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
