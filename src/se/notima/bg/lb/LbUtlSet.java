package se.notima.bg.lb;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import se.notima.bg.BgFooter;
import se.notima.bg.BgHeader;
import se.notima.bg.BgRecord;
import se.notima.bg.BgSet;
import se.notima.bg.BgUtil;
import se.notima.bg.Transaction;

public class LbUtlSet extends AbstractLbSet {

	public static LbUtlSet createPayableSet(String senderAccount, String senderName, String senderAddress) {
		String sA = BgUtil.toDigitsOnly(senderAccount);
		BgHeader h = new LbTk0Header(sA, senderName, senderAddress);
		BgFooter f = new LbTk9Footer(sA);
		LbUtlSet set = new LbUtlSet(h,f);
		return(set);
	}
	
	public LbUtlSet(BgHeader header, BgFooter footer) {
		super(header, footer);
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
	

}
