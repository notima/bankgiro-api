/** ===================================================================
	Bankgiro Java API
    
    Copyright (C) 2009  Daniel Tamm
                        Notima Consulting Group Ltd

    This API-library is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This API-library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this API-library.  If not, see <http://www.gnu.org/licenses/>.

    =================================================================== */

package se.notima.bg.lb;

import java.util.*;

import se.notima.bg.BgFooter;
import se.notima.bg.BgHeader;
import se.notima.bg.BgRecord;
import se.notima.bg.BgSet;
import se.notima.bg.BgUtil;
import se.notima.bg.Transaction;

/**
 * An LbFile consists of one or more sets.
 * An LbSet consists of a header, a footer and records.
 * 
 * @author Daniel Tamm
 *
 */
public class LbSet implements BgSet {

	private BgHeader	header;
	private BgFooter	footer;
	private Vector<Transaction>	records;
	private Vector<Transaction> creditTransactions = new Vector<Transaction>();
	private Map<String, Vector<BgRecord>>	creditRecords = new TreeMap<String, Vector<BgRecord>>();
	
	public static LbSet createPayableSet(String senderAccount) {
		String sA = BgUtil.toDigitsOnly(senderAccount);
		BgHeader h = new LbTk11Header(sA);
		BgFooter f = new LbTk29Record(sA, 0, 0.0);
		LbSet set = new LbSet(h,f);
		return(set);
	}
	
	public LbSet(BgHeader header, BgFooter footer) {
		this.header = header;
		this.footer = footer;
		records = new Vector<Transaction>();
	}

	public String getSenderBankAccount() {
		return(header.getSenderAccount());
	}

    public String getRecipientBankAccount() {
        return(null);
    }

	public String getCurrency() {
		return(header.getCurrency());
	}
	
	public void setCurrency(String currency) {
		header.setCurrency(currency);
	}
	
	public java.util.Date getPayDate() {
		return(header.getPayDate());
	}
	
	public BgHeader getHeader() {
		return header;
	}
	
	public void setHeader(BgHeader header) {
		this.header = header;
	}
	
	public BgFooter getFooter() {
		return(footer);
	}
	
	public void setFooter(BgFooter footer) {
		this.footer = footer;
	}
	
	public Vector<Transaction> getRecords() {
		return records;
	}
	
	public void setRecords(Vector<Transaction> records) {
		this.records = records;
		footer.setAmount(0.0);
		if (records!=null) {
			footer.setCount(records.size());
		}
		for (Iterator<Transaction> it = records.iterator(); it.hasNext();) {
			footer.incrementAmount(it.next().getAmount());
		}
	}

	public void addCreditRecord(BgRecord creditRecord) {
		if (creditRecord instanceof LbTk21Record) {
			LbTk21Record r = (LbTk21Record)creditRecord;
			Vector<BgRecord> recs = creditRecords.get(r.getRecipientBg());
			if (recs==null) {
				recs = new Vector<BgRecord>();
				creditRecords.put(r.getRecipientBg(), recs);
			}
			recs.add(r);
			// Find the credit which this credit record belongs to
			Transaction tr;
			LbPayment payment;
			for (Iterator<Transaction> it = creditTransactions.iterator(); it.hasNext();) {
				tr = it.next();
				payment = (LbPayment)tr;
				if (payment.getDstBg().equals(r.getRecipientBg()) && Math.abs(tr.getAmount())==r.getCreditAmount()) {
					payment.setTransactionDate(r.getCreditDate());
				}
			}
		}
	}
	
	/**
	 * Adds a payment to the set
	 * @param payment
	 */
    public void addTransaction(Transaction payment) {
    	if (footer!=null) {
    		footer.incrementAmount(payment.getAmount());
    		footer.incrementCount();
    	}
    	if (payment.getAmount()<0) {
    		creditTransactions.add(payment);
    	}
        records.add((LbPayment)payment);
    }

	public String toRecordString() {
		StringBuffer lines = new StringBuffer();
		lines.append(header.toRecordString() + "\n");
		Transaction payment;
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				payment = records.get(i);
				payment.setSeqNo(i+1);
				lines.append(payment.toRecordString());
			}
		}
		
		lines.append(footer.toRecordString() + "\n");
		
		return(lines.toString());
	}

	@Override
	public java.util.Date getCreditRecordDate(String recipientBg, double amount) {
		recipientBg = BgUtil.trimLeadingZeros(BgUtil.toDigitsOnly(recipientBg));
		Vector<BgRecord> recs = creditRecords.get(recipientBg);
		if (recs==null) return(null);
		LbTk21Record r;
		BgRecord rec;
		for (Iterator<BgRecord> it = recs.iterator(); it.hasNext();) {
			rec = it.next();
			if (rec instanceof LbTk21Record) {
				r = (LbTk21Record)rec;
				if (amount==r.getCreditAmount()) return(r.getCreditDate());
			}
		}
		return(null);
	}
	
}
