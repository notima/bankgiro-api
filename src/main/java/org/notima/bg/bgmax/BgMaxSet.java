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

package org.notima.bg.bgmax;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.notima.bg.BgParseException;
import org.notima.bg.BgSet;
import org.notima.bg.Transaction;


public class BgMaxSet implements BgSet {

	private Vector<Transaction>	records = new Vector<Transaction>();
	private BgMaxTk05Record	setHeader;
	private BgMaxTk15Record setFooter;

	public BgMaxSet(){};
	
	public BgMaxSet(Date setDate, String currency, String clearing, String recipientBankAcct, String bgAccount) {
		setHeader = new BgMaxTk05Record();
		setHeader.setRecipientBg(bgAccount);
		setHeader.setCurrency(currency);
		setFooter = new BgMaxTk15Record();
		setFooter.setCurrency(currency);
		setFooter.setTransactionDate(setDate);
		setFooter.setToBankAccount(recipientBankAcct);
	}

	private void initSetHeader() {
		if (setHeader==null) {
			setHeader = new BgMaxTk05Record();
		}
	}

	public void addTransaction(Transaction trans) {
		records.add(trans);
	}

	public void setRecords(Vector<Transaction> records) {
		this.records = records;
	}
	
    public Vector<Transaction> getRecords() {
        return(records);
    }

	public void setSetHeader(BgMaxTk05Record setHeader) {
		this.setHeader = setHeader;
	}
	
	/**
	 * When footer is set, set the transaction date of the payments and check sum of payments
	 * @param setFooter
	 */
	public void setSetFooter(BgMaxTk15Record setFooter) throws BgParseException {
		this.setFooter = setFooter;
		Transaction tr;
		double total = 0.0;
		for (Iterator<Transaction> it = records.iterator(); it.hasNext();) {
			tr = it.next();
			if (tr.getTransactionDate()==null)
				tr.setTransactionDate(setFooter.getTransactionDate());
			total += tr.getAmount();
		}
		// Round total to two decimals. In certain cases differences may occur otherwise.
		total = ((double)Math.round(total * 100))/100;
		if (total!=setFooter.getAmount()) {
			throw new BgParseException("Sum of transactions and footer sum is not equal: " + total + " != " + setFooter.getAmount());
		}
	}

	/**
	 * Calculates the footer from the transactions
	 */
	public void calculateFooter() {

		double total = 0.0;
		int count = 0;
		Transaction tr;
		for (Iterator<Transaction> it = records.iterator(); it.hasNext();) {
			tr = it.next();
			total += tr.getAmount();
			count++;
		}
		// Round total to two decimals. In certain cases differences may occur otherwise.
		total = ((double)Math.round(total * 100))/100;
		setFooter.setAmount(total);
		setFooter.setCount(count);
		
	}
	
    public String getCurrency() {
        return(setHeader.getCurrency());
    }

    public void setCurrency(String currency) {
    	if (currency==null)
    		return;
    	initSetHeader();
    	setHeader.setCurrency(currency.toUpperCase());
    }
    
    public void setRecipientBankAccount(String account) {
    	if (account==null)
    		return;
    	initSetHeader();
    	setHeader.setRecipientBg(account);
    }
    
    public String getRecipientBankAccount() {
        return(setHeader.getRecipientBg());
    }

    public String getSenderBankAccount() {
        return(null);
    }

    public BgMaxTk15Record generateFooter() throws BgParseException {
    	if (setFooter==null) {
    		setFooter = new BgMaxTk15Record();
    	}
    	
    	setFooter.setCurrency(setHeader.getCurrency());
    	calculateFooter();
    	
    	return setFooter;
    }
    
	public String toRecordString() {
		StringBuffer lines = new StringBuffer();
		if (records==null || records.size()==0) {
			return "";
		}
		lines.append(setHeader.toRecordString() + "\n");
		Transaction receipt;
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				receipt = records.get(i);
				receipt.setSeqNo(i+1);
				lines.append(receipt.toRecordString());
			}
		}
		try {
			if (setFooter==null)
				generateFooter();
			else {
				// Recalculate sums
				calculateFooter();
			}
			
		} catch (BgParseException e) {
			e.printStackTrace();
		}
		lines.append(setFooter.toRecordString() + "\n");
		return(lines.toString());
		
	}

	public Date getCreditRecordDate(String recipientBg, double amount) {
		return null;
	}
	
	
}
