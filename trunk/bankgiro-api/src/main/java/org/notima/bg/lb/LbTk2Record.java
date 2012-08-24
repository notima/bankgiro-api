package org.notima.bg.lb;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;

public class LbTk2Record extends BgRecord {

	private int	m_recipientNo;
	private String	m_name1;
	private String	m_name2;
	
	public LbTk2Record(int recipientNo, String name1, String name2) {
		super("2");
		m_recipientNo = recipientNo;
		m_name1 = BgUtil.onlyUSASCII(name1);
		m_name2 = BgUtil.onlyUSASCII(name2);
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
		line.append(BgUtil.fillToLength(m_name1.toUpperCase(), false, ' ', 30));
		line.append(BgUtil.fillToLength(m_name2.toUpperCase(), false, ' ', 35));
		while(line.length()<80) {
			line.append(" ");
		}
		return(line.toString());
	}

}
