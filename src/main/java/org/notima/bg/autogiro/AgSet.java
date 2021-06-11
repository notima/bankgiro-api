package org.notima.bg.autogiro;

import java.util.Date;
import java.util.Vector;

import org.notima.bg.BgSet;
import org.notima.bg.Transaction;

public class AgSet implements BgSet {

	private Vector<Transaction> records = new Vector<Transaction>();
	
	@Override
	public void setRecords(Vector<Transaction> records) {
		this.records = records;
	}

	@Override
	public Vector<Transaction> getRecords() {
		return records;
	}

	@Override
	public void addTransaction(Transaction trans) {
		records.add(trans);
	}

	@Override
	public Date getCreditRecordDate(String recipientBg, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toRecordString() {
		if (records==null || records.size()==0) return "";
		StringBuffer buf = new StringBuffer();
		for (Transaction tr : records) {
			buf.append(tr.toRecordString() + "\n");
		}
		return buf.toString();
	}

	@Override
	public String getCurrency() {
		return null;
	}

	@Override
	public String getRecipientBankAccount() {
		return null;
	}

	@Override
	public String getSenderBankAccount() {
		return null;
	}

}
