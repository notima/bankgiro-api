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

import java.util.*;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgSet;
import org.notima.bg.BgUtil;
import org.notima.bg.Transaction;


public class LbPayment implements Transaction {
	
	private Vector<BgRecord>	records;
	private BgSet				parentSet;
	private double				amount;
	private String				dstName;
	private String				dstAccount;
	private String				dstBg;
	private String				dstPg;
	private String				ourRef;
	private Date				transDate;
	private String 				comment;
	private String				ocr;
	private double				foreignAmount;
	private String				foreignCurrency;
	private boolean				foreign = false;
	private Vector<String>		info;

    public LbPayment() {
        records = new Vector<BgRecord>();
        info = new Vector<String>();
    }

    /**
     * Payment for paying to a bank account
     * @param dstAccount
     * @param amount
     * @param comment
     * @param ourRef
     */
	public static LbPayment createBankPayment(String dstAccount, double amount, String comment, String ourRef, Date payDate) {
        LbPayment payment = new LbPayment();
        payment.amount = (double)Math.round(amount*100.0)/100.0;
		BgRecord rec1 = new LbTk40Record(dstAccount, comment);
		BgRecord rec2;
		if (amount>=0.0) {
			rec2 = new LbTk14Record(amount, ourRef, payDate);	// Normal payment
		} else {
			rec2 = new LbTk16Record(amount, ourRef, payDate);	// Credit payment
		}
		payment.records = new Vector<BgRecord>();
		payment.records.add(rec1);
		payment.records.add(rec2);
		payment.dstAccount = dstAccount;
		payment.ourRef = ourRef;
		payment.transDate = payDate;
		payment.comment = comment;
		return(payment);
	}

    /**
     * Create a BG payment
     *
     * @param recipientBg		The recipient's bg number
     * @param OCR				The OCR-reference for the payment
     * @param amount			The amount 
     * @param ourRef			Our reference (that we wants returned back)
     * @return	A representation of a accounts payable payment
     */
    public static LbPayment createBgPayment(String recipientBg, String OCR, double amount, String ourRef, Date payDate) {
        LbPayment payment = new LbPayment();
        payment.amount = (double)Math.round(amount*100.0)/100.0;
        boolean digitsOnly = BgUtil.hasDigitsOnly(OCR);
		if (amount>=0.0) {
			LbTk14Record rec1 = new LbTk14Record(recipientBg, amount, ourRef);	// Normal payment
            rec1.setPayDate(payDate);
            if (digitsOnly) rec1.setOcrRef(OCR);
            payment.records.add(rec1);
		} else {
			LbTk16Record rec1 = new LbTk16Record(recipientBg, amount, ourRef);	// Credit payment
            if (digitsOnly) rec1.setOcrRef(OCR);
            payment.records.add(rec1);
		}
        if (!digitsOnly && OCR!=null && OCR.trim().length()>0) {
            // Create info record
            LbTk25Record rec = new LbTk25Record();
            rec.setRecipientBg(recipientBg);
            rec.setInfoText(OCR.trim());
            payment.records.add(rec);
        }
		payment.dstBg = recipientBg;
		payment.ourRef = ourRef;
		payment.transDate = payDate;
		payment.ocr = OCR;
        
        return(payment);
    }

    /**
     * Creates a PlusGirot payment. Credit / negative amounts not allowed since it's not supported by
     * bankgirots format.
     *
     * @param recipientPg   Recipient's Plusgiro
     * @param OCR           Must be numeric
     * @param amount
     * @param ourRef        Used for matching payments
     * @param payDate
     * @param infoText      Array of text. Information that will be sent with the payment.
     * @return	LbPayment. If amount is negative, null is returned.
     */
    public static LbPayment createPgPayment(String recipientPg, String OCR, double amount, String ourRef, Date payDate, String[] infoText) {
        LbPayment payment = new LbPayment();
        payment.amount = (double)Math.round(amount*100.0)/100.0;
        if (amount>=0.0) {
	        LbTk54Record rec1 = new LbTk54Record(recipientPg, OCR, amount, ourRef, payDate);
	        payment.records.add(rec1);
	        if (infoText!=null && infoText.length>0) {
	            LbTk65Record rec2;
	            for (int i=0; i<infoText.length; i++) {
	                if (infoText[i]!=null && infoText[i].trim().length()>0) {
	                    rec2 = new LbTk65Record(recipientPg, infoText[i]);
	                    payment.records.add(rec2);
	                    payment.info.add(infoText[i]);
	                }
	            }
	        }
        } else {
        	// Credit record not possible for plusgirot.
        	return null;
        }
		payment.dstPg = recipientPg;
		payment.ourRef = ourRef;
		payment.transDate = payDate;
		payment.ocr = OCR;
        
        return(payment);
    }

    /**
     * 
     * @param recipientNo	RecipientNo to identify this recipient. Should be unique for your 
     * 						vendors. If you have a vendor no in your system you can use that.
     * @param swift			SWIFT/BIC for international payment. Can be null. If null, check payment
     * 						is used.
     * @param iban			IBAN for international payment. Can be null. If null, check payment
     * 						is used. For check, address must exist.
     * @param name			Name of payee.
     * @param address		Address of payee
     * @param postal		Postal address of payee (ie zip and city)
     * @param countryCode	Country of payee
     * @param invoiceRef	The payees invoice ref. They identify the payment on this ref.
     * @param amount		Amount in SEK (optional, if zero it's not considered)
     * @param foreignAmount		Amount in foreign currency
     * @param currency		Foreign currency (ISO code)
     * @param ourRef		Our reference for this payment.
     * @param payDate		Date when payment should occur
     * @param bankCode		Bank code (101) (Varuimport/export)
     * @param costDist		CostDistribution, LbTk3Record.COST_* (who pays bank fees)
     * @param trxType		Transaction type, LbTk3Record.TRX_* (how fast)
     * @param hbAccountNo	If payments are done from Handelsbanken, this should be the account number
     * 						used for the payment.
     * @return	An LbPayment representing a UTL-payment.
     */
    public static LbPayment createUtlPayment(int recipientNo,
    										 String swift, 
    										 String iban,
    										 String name,
    										 String address,
    										 String postal,
    										 String countryCode,
    										 String invoiceRef,
    										 double amount,
    										 double foreignAmount,
    										 String currency,
    										 String ourRef,
    										 Date payDate,
    										 int bankCode,
    										 String costDist,
    										 String trxType,
    										 String hbAccountNo
    										 ) {
    	LbPayment payment = new LbPayment();
    	payment.setForeign(true);
    	payment.comment = invoiceRef;
    	payment.ourRef = ourRef;
    	payment.transDate = payDate;
    	payment.foreignAmount = (double)Math.round(foreignAmount*100.0)/100.0;
    	payment.amount = (double)Math.round(amount*100.0)/100.0;
    	// Add name record
    	LbTk2Record nameRec = new LbTk2Record(recipientNo, name, "");
    	payment.records.add(nameRec);
    	// Add address record
    	LbTk3Record addrRec = new LbTk3Record(recipientNo, address, postal, countryCode, costDist, trxType);
    	payment.records.add(addrRec);
    	// TODO: Better IBAN check
    	if (iban!=null & iban.length()>0) {
    		LbTk4Record ibanRec = new LbTk4Record(recipientNo, swift, iban);
    		payment.records.add(ibanRec);
    	}
    	// Amount
    	if (payment.foreignAmount>=0) {
	    	LbTk6Record amountRec = new LbTk6Record(recipientNo, invoiceRef, payment.amount, currency, payDate, payment.foreignAmount);
	    	payment.records.add(amountRec);
    	} else {  // Credit note
    		LbTk5Record amountRec = new LbTk5Record(recipientNo, invoiceRef, payment.amount, currency, payDate, payment.foreignAmount);
	    	payment.records.add(amountRec);
    	}
    	// Bank code
    	LbTk7Record bankRec = new LbTk7Record(recipientNo, bankCode, hbAccountNo);
    	payment.records.add(bankRec);
    	
    	return(payment);
    }
    
    /**
     * Adds a record and adjusts the payment record accordingly
     * 
     * @param record
     */
    public void addRecord(BgRecord record) {
    	record.setParentSet(parentSet);
    	int code = Integer.parseInt(record.getTransCode());
    	if (code==40) {
    		LbTk40Record r = (LbTk40Record)record;
    		dstAccount = r.getFullAccountNo();
    	}
    	if (code==14) {
    		LbTk14Record r = (LbTk14Record)record;
    		dstBg = BgUtil.trimLeadingZeros(r.recipientBg);
    		ocr = r.ocrRef.trim();
    		ourRef = r.ourRefText.trim();
    		amount = r.amount;
    		transDate = r.payDate;
    	}
    	// Credit record
    	if (code==16) {
    		LbTk16Record r = (LbTk16Record)record;
    		dstBg = BgUtil.trimLeadingZeros(r.getRecipientBg());
    		ocr = r.ocrRef.trim();
    		ourRef = r.ourRefText.trim();
    		amount = r.getAmount(); // Negative amount
    		transDate = r.getPayDate();
    	}
    	if (code==54) {
    		LbTk54Record r = (LbTk54Record)record;
    		dstPg = BgUtil.trimLeadingZeros(r.getRecipientPg());
    		ocr = r.getOcrRef();
    		ourRef = r.getOurRef();
    		amount = r.getAmount();
    		transDate = r.getPayDate();
    	}
    	if (code==26) {
    		dstName = ((LbTk26Record)record).getRecipientName();
    	}
    	records.add(record);
    }
    
    /**
     * Sets pay date for record
     * @param payDate
     */
    public void setTransactionDate(Date payDate) {
        BgRecord rec;
        for (Iterator<BgRecord> it = records.iterator(); it.hasNext();) {
            rec = it.next();
            if (rec instanceof LbTk14Record) {
                ((LbTk14Record)rec).setPayDate(payDate);
            }
            if (rec instanceof LbTk54Record) {
                ((LbTk54Record)rec).setPayDate(payDate);
            }
        }
        this.transDate = payDate;
    }

	/**
	 * Set's the sequence number for this payment
	 * 
	 * @param seqNo
	 */
	public void setSeqNo(int seqNo) {
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				records.get(i).setSeqNo(seqNo);
			}
		}
	}
	
	public double getAmount() {
		return(amount);
	}
	
	public String toRecordString() {
		StringBuffer lines = new StringBuffer();
		if (records!=null && records.size()>0) {
			for (int i=0; i<records.size(); i++) {
				lines.append(records.get(i).toRecordString() + "\n");
			}
		}
		return(lines.toString());
	}

	public Vector<BgRecord> getRecords() {
		return records;
	}

	public void setRecords(Vector<BgRecord> records) {
		this.records = records;
	}

	public String getDstName() {
		return dstName;
	}

	public void setDstName(String dstName) {
		this.dstName = dstName;
	}

	public String getDstAccount() {
		return dstAccount;
	}

	public void setDstAccount(String dstAccount) {
		this.dstAccount = dstAccount;
	}

	public String getDstPg() {
		return dstPg;
	}

	public void setDstPg(String dstPg) {
		this.dstPg = dstPg;
	}

	public String getOurRef() {
		return ourRef;
	}

	public void setOurRef(String ourRef) {
		this.ourRef = ourRef;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOcr() {
		return ocr;
	}

	public void setOcr(String ocr) {
		this.ocr = ocr;
	}

	public Vector<String> getInfo() {
		return info;
	}

	public void setInfo(Vector<String> info) {
		this.info = info;
	}

	public Date getTransactionDate() {
		return transDate;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isBgPayment() {
		return(dstBg!=null && dstBg.trim().length()>0);
	}
	
	public boolean isPgPayment() {
		return(dstPg!=null && dstPg.trim().length()>0);
	}
	
	public boolean isAccountPayment() {
		return(dstAccount!=null && dstAccount.trim().length()>0);
	}

	public String getDstBg() {
		return dstBg;
	}

	public void setDstBg(String dstBg) {
		this.dstBg = dstBg;
	}
	
	/**
	 * Returns the destination (pg/bg/account)
	 * 
	 * null if there's no known destination
	 */
	public String getDestination() {
		if (isBgPayment()) return(dstBg);
		if (isPgPayment()) return(dstPg);
		if (isAccountPayment()) return(dstAccount);
		return(null);
	}
	
	/**
	 * Returns the destination formatted (pg/bg/account)
	 * 
	 * null if there's no known destination
	 */
	public String getDestinationFormatted() throws BgParseException {
		if (isBgPayment()) return(BgUtil.formatBg(dstBg));
		if (isPgPayment()) return(BgUtil.formatPg(dstPg));
		if (isAccountPayment()) return(dstAccount); // TODO: Add formatter for account
		return(null);
	}

	public boolean isForeign() {
		return foreign;
	}

	public void setForeign(boolean international) {
		this.foreign = international;
	}

	public double getForeignAmount() {
		return foreignAmount;
	}

	public void setForeignAmount(double foreignAmount) {
		this.foreignAmount = foreignAmount;
	}

	public String getForeignCurrency() {
		return foreignCurrency;
	}

	public void setForeignCurrency(String currency) {
		this.foreignCurrency = currency;
	}

	public BgSet getParentSet() {
		return(parentSet);
	}

	/**
	 * Sets parent set of all containing records
	 */
	public void setParentSet(BgSet parentSet) {
		this.parentSet = parentSet;
		for (BgRecord rec : records) {
			rec.setParentSet(parentSet);
		}
	}
	
	
}
