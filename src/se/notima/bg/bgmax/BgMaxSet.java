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

package se.notima.bg.bgmax;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import se.notima.bg.BgFooter;
import se.notima.bg.BgHeader;
import se.notima.bg.BgParseException;
import se.notima.bg.BgSet;
import se.notima.bg.Transaction;

public class BgMaxSet implements BgSet {

	private BgHeader	fileHeader;
	private BgFooter	fileFooter;
	private Vector<Transaction>	records = new Vector<Transaction>();
	private BgMaxTk05Record	setHeader;
	private BgMaxTk15Record setFooter;

	
	public BgMaxSet(BgHeader fileHeader) {
		this.fileHeader = fileHeader;
	}
	
	public void setFileFooter(BgFooter fileFooter) {
		this.fileFooter = fileFooter;
	}

	@Override
	public void addTransaction(Transaction trans) {
		records.add(trans);
	}

	@Override
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
			tr.setTransactionDate(setFooter.getTransactionDate());
			total += tr.getAmount();
		}
		// Round total to two decimals. In certain cases differences may occur otherwise.
		total = ((double)Math.round(total * 100))/100;
		if (total!=setFooter.getAmount()) {
			throw new BgParseException("Sum of transactions and footer sum is not equal: " + total + " != " + setFooter.getAmount());
		}
	}

    public String getCurrency() {
        return(setHeader.getCurrency());
    }

    public String getRecipientBankAccount() {
        return(setHeader.getRecipientBg());
    }

    public String getSenderBankAccount() {
        return(null);
    }

	@Override
	public String toRecordString() {
		StringBuffer lines = new StringBuffer();
		lines.append(fileHeader.toRecordString() + "\n");
		Transaction receipt;
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				receipt = records.get(i);
				receipt.setSeqNo(i+1);
				lines.append(receipt.toRecordString());
			}
		}
		lines.append(fileFooter.toRecordString());
		return(lines.toString());
		
	}

	@Override
	public Date getCreditRecordDate(String recipientBg, double amount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
