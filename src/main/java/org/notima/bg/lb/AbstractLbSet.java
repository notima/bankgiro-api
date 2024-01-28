/** ===================================================================
	Bankgiro Java API
    
    Copyright (C) 2009  Daniel Tamm
						Notima Consulting Group Ltd
						
	Copyright (C) 2019  Notima System Integration AB

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.						

    =================================================================== */


package org.notima.bg.lb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.notima.bg.BgFooter;
import org.notima.bg.BgHeader;
import org.notima.bg.BgRecord;
import org.notima.bg.BgSet;
import org.notima.bg.BgTransaction;
import org.notima.bg.Transaction;


public abstract class AbstractLbSet implements BgSet {

	public static final int BANK_HANDELSBANKEN	= 1;
	public static final int BANK_SWEDBANK = 2;
	public static final int BANK_SEB = 3;
	public static final int BANK_NORDEA = 4;
	
	protected BgHeader	header;
	protected BgFooter	footer;
	protected List<Transaction>	records;
	protected List<BgTransaction> creditTransactions = new ArrayList<BgTransaction>();
	protected Map<String, List<BgRecord>>	creditRecords = new TreeMap<String, List<BgRecord>>();

	public AbstractLbSet(BgHeader header, BgFooter footer) {
		this.header = header;
		this.footer = footer;
		records = new ArrayList<Transaction>();
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
	
	public List<Transaction> getRecords() {
		return records;
	}
	
	/**
	 * Adds a payment to the set
	 * @param trx		The payment to be added
	 */
    public void addTransaction(Transaction trx) {
		if (trx instanceof BgTransaction) {
			BgTransaction payment = (BgTransaction)trx;
			payment.setParentSet(this);
			if (footer!=null) {
				footer.incrementAmount(payment.getAmount());
				footer.incrementCount();
				if (payment.getForeignAmount()!=0) {
					footer.incrementForeignAmount(payment.getForeignAmount());
				}
			}
			if (payment.getAmount()<0) {
				creditTransactions.add(payment);
			}
		}
        records.add((LbPayment)trx);
    }

	
	public void setRecords(List<Transaction> records) {
		this.records = records;
		footer.setAmount(0.0);
		if (records!=null) {
			footer.setCount(records.size());
		}
		BgTransaction bgt;
		Transaction t;
		for (Iterator<Transaction> it = records.iterator(); it.hasNext();) {
			t = it.next();
			if (t instanceof BgTransaction) {
				bgt = (BgTransaction)t;
				footer.incrementAmount(bgt.getAmount());
			}
		}
	}

	public String toRecordString() {
		StringBuffer lines = new StringBuffer();
		lines.append(header.toRecordString() + "\n");
		Transaction t;
		BgTransaction payment;
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				t = records.get(i);
				if (t instanceof BgTransaction) {
					payment = (BgTransaction)t;
					payment.setSeqNo(i+1);
				}
				lines.append(t.toRecordString());
			}
		}
		
		lines.append(footer.toRecordString() + "\n");
		
		return(lines.toString());
	}
	
	public abstract java.util.Date getCreditRecordDate(String recipientBg, double amount);	
	
	public abstract void addCreditRecord(BgRecord creditRecord);	

}
