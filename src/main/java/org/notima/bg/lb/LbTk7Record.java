package org.notima.bg.lb;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgSet;
import org.notima.bg.BgUtil;

/**
 * Centralbankspost med debetkontonummer
 * 
 * @author Daniel Tamm
 *
 */
public class LbTk7Record extends BgRecord {

	private int	m_recipientNo;
	private int	m_bankCode;	// Betalningskod i Visma, ex 101 = Varuimport/export
	private String	m_hbAccountNo; // Used for payments in handelsbanken	
	private int		bankId = 0;
	
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
		// Get parent set if any
		BgSet parentSet = this.getParentSet();
		if (parentSet!=null && parentSet instanceof LbUtlSet) {
			bankId = ((LbUtlSet)parentSet).getBankId();
		}
		
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
