package org.notima.bg.lb;

import java.util.Date;

import org.notima.bg.BgFooter;
import org.notima.bg.BgHeader;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;

public class LbUtlSet extends AbstractLbSet {

	private int	m_bankId;
	
	public static LbUtlSet createPayableSet(String senderAccount, String senderName, String senderAddress, int bankId) {
		String sA = BgUtil.toDigitsOnly(senderAccount);
		BgHeader h = new LbTk0Header(sA, senderName, senderAddress);
		LbTk9Footer f = new LbTk9Footer(sA);
		LbUtlSet set = new LbUtlSet(h,f, bankId);
		return(set);
	}
	
	public LbUtlSet(BgHeader header, BgFooter footer, int bankId) {
		super(header, footer);
		header.setParentSet(this);
		footer.setParentSet(this);
		m_bankId = bankId;
	}

	@Override
	public void addCreditRecord(BgRecord creditRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreditRecordDate(String recipientBg, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getBankId() {
		return m_bankId;
	}

	public void setBankId(int mBankId) {
		m_bankId = mBankId;
	}
	

}
