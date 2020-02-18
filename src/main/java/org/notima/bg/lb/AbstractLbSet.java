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

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.notima.bg.BgFooter;
import org.notima.bg.BgHeader;
import org.notima.bg.BgRecord;
import org.notima.bg.BgSet;
import org.notima.bg.Transaction;


public abstract class AbstractLbSet implements BgSet {

	public static final int BANK_HANDELSBANKEN	= 1;
	public static final int BANK_SWEDBANK = 2;
	public static final int BANK_SEB = 3;
	public static final int BANK_NORDEA = 4;
	
	protected BgHeader	header;
	protected BgFooter	footer;
	protected Vector<Transaction>	records;
	protected Vector<Transaction> creditTransactions = new Vector<Transaction>();
	protected Map<String, Vector<BgRecord>>	creditRecords = new TreeMap<String, Vector<BgRecord>>();

	public AbstractLbSet(BgHeader header, BgFooter footer) {
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
	
	/**
	 * Adds a payment to the set
	 * @param payment		The payment to be added
	 */
    public void addTransaction(Transaction payment) {
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
        records.add((LbPayment)payment);
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
	
	public abstract java.util.Date getCreditRecordDate(String recipientBg, double amount);	
	
	public abstract void addCreditRecord(BgRecord creditRecord);	

}
