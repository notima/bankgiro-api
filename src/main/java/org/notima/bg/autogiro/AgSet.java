package org.notima.bg.autogiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.notima.bg.BgSet;
import org.notima.bg.Transaction;


public class AgSet implements BgSet {

	private List<Transaction> records = new ArrayList<Transaction>();
	
	public void setRecords(List<Transaction> records) {
		this.records = records;
	}

	public List<Transaction> getRecords() {
		return records;
	}

	public void addTransaction(Transaction trans) {
		records.add(trans);
	}

	public String toRecordString() {
		if (records==null || records.size()==0) return "";
		StringBuffer buf = new StringBuffer();
		for (Transaction tr : records) {
			buf.append(tr.toRecordString() + "\n");
		}
		return buf.toString();
	}

	public String getCurrency() {
		return null;
	}

	public String getRecipientBankAccount() {
		return null;
	}

	@Override
	public Date getCreditRecordDate(String recipientBg, double amount) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCreditRecordDate'");
	}

	@Override
	public String getSenderBankAccount() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getSenderBankAccount'");
	}


}
