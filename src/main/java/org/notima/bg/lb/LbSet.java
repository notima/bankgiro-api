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
import java.util.Vector;

import org.notima.bg.BgFooter;
import org.notima.bg.BgHeader;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;
import org.notima.bg.Transaction;


/**
 * An LbFile consists of one or more sets.
 * An LbSet consists of a header, a footer and records.
 * 
 * @author Daniel Tamm
 *
 */
public class LbSet extends AbstractLbSet {
	
	public static LbSet createPayableSet(String senderAccount) {
		String sA = BgUtil.toDigitsOnly(senderAccount);
		BgHeader h = new LbTk11Header(sA);
		BgFooter f = new LbTk29Record(sA, 0, 0.0);
		LbSet set = new LbSet(h,f);
		return(set);
	}
	
	public LbSet(BgHeader header, BgFooter footer) {
		super(header,footer);
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
			// Find the credit which this credit record belongs to and set transaction date from Tk21
			Transaction tr;
			LbPayment payment;
			for (Iterator<Transaction> it = creditTransactions.iterator(); it.hasNext();) {
				tr = it.next();
				payment = (LbPayment)tr;
				if (payment.getDstBg().equals(r.getRecipientBg()) && Math.abs(tr.getAmount())==r.getCreditAmount()) {
					payment.setTransactionDate(r.getCreditDate());
				}
			}
		 } else if (creditRecord instanceof LbTk20Record) {
			 // LbTk20Record is just an information message
			 LbTk20Record r = (LbTk20Record)creditRecord;
				Vector<BgRecord> recs = creditRecords.get(r.getRecipientBg());
				if (recs==null) {
					recs = new Vector<BgRecord>();
					creditRecords.put(r.getRecipientBg(), recs);
				}
				recs.add(r);			 
		 }
		
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
