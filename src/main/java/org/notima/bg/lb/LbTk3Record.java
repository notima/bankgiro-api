package org.notima.bg.lb;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;

public class LbTk3Record extends BgRecord {

	// Cost distribution constants
	public final static String COST_SHA = "0";		// Every part pays their cost
	public final static String COST_BEN = "1";		// Recipient pays all costs
	public final static String COST_OUR = "9";		// Sender pays all costs
	
	// Type of transfer
	public final static String TRX_NORMAL = "B";
	public final static String TRX_EXPRESS = "T";
	public final static String TRX_CHECK = "C"; 
	
	private int	m_recipientNo;
	private String	m_adress;
	private String	m_postal;
	private String	m_countryCode;
	private String	m_countryName;
	private String	m_bankCountryCode;
	private String	m_costDistribution;
	private String	m_trxType;
	
	public LbTk3Record(int recipientNo, String address, String postal, String countryCode, String costDist, String trxType) {
		super("3");
		m_recipientNo = recipientNo;
		m_adress = BgUtil.onlyUSASCII(address);
		m_postal = BgUtil.onlyUSASCII(postal);
		m_countryCode = countryCode;
		// Lookup country name
		m_countryName = "";
		m_postal += " " + m_countryName;
		// Lookup bank country code
		m_bankCountryCode = m_countryCode; // Handelsbanken
		m_costDistribution = costDist;
		m_trxType = trxType;
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		return null;
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(getTransCode());
		line.append(BgUtil.fillToLength(Integer.toString(m_recipientNo),
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
		if (m_costDistribution==null || m_costDistribution.length()<1) {
			line.append("0"); // Handelsbanken 0 = Mottagaren betalar utländska kostnader
		} else {
			line.append(m_costDistribution.charAt(0));
		}
		// Payment Form 79 (How fast, normal, express, check)
		if (m_trxType==null || m_trxType.length()<1) {
			line.append("B"); // Handelsbanken B, C  B = Betalning till annat EU land i SEK/EUR ?
		} else {
			line.append(m_trxType.charAt(0));
		}
		line.append(" "); // Blank Handelsbanken
		
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
