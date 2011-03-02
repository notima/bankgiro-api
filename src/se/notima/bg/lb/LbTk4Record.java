package se.notima.bg.lb;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

public class LbTk4Record extends BgRecord {

	private int	m_recipientNo;
	private String	m_swift;
	private String	m_iban;
	private String	m_bic;
	
	public LbTk4Record(int recipientNo, String swift, String iban, String bic) {
		super("4");
		m_swift = swift;
		m_iban = iban;
		m_bic = bic;
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
		line.append(BgUtil.fillToLength(m_swift.toUpperCase(), false, ' ', 12));
		line.append(BgUtil.fillToLength(m_iban.toUpperCase(), false, ' ', 30));
		
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
