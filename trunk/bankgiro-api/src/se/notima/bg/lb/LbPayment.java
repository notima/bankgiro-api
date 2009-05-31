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

import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;
import se.notima.bg.Transaction;

public class LbPayment implements Transaction {
	
	private Vector<BgRecord>	records;
	private double				amount;
	private String				dstName;
	private String				dstAccount;
	private String				dstBg;
	private String				dstPg;
	private String				ourRef;
	private Date				transDate;
	private String 				comment;
	private String				ocr;
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
     * @param recipientBg
     * @param OCR
     * @param amount
     * @param ourRef
     * @return
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
     * Creates a PlusGirot payment.
     *
     * @param recipientPg   Recipient's Plusgiro
     * @param OCR           Must be numeric
     * @param amount
     * @param ourRef        Used for matching payments
     * @param payDate
     * @param infoText      Array of text. Information that will be sent with the payment.
     * @return
     */
    public static LbPayment createPgPayment(String recipientPg, String OCR, double amount, String ourRef, Date payDate, String[] infoText) {
        LbPayment payment = new LbPayment();
        payment.amount = (double)Math.round(amount*100.0)/100.0;
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
		payment.dstPg = recipientPg;
		payment.ourRef = ourRef;
		payment.transDate = payDate;
		payment.ocr = OCR;
        
        return(payment);
    }

    /**
     * Adds a record and adjusts the payment record accordingly
     * 
     * @param record
     */
    public void addRecord(BgRecord record) {
    	int code = new Integer(record.getTransCode()).intValue();
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
	
}
