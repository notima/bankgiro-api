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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.util.NotimaUtil;


/**
 * Represents a credit record (negative payment)
 * 
 * @author Daniel Tamm
 *
 */
public class LbTk16Record extends BgRecord implements LbPaymentRecord {

	private static Pattern	linePattern1 = Pattern.compile("16(\\d{9}[\\d|\\s])([\\s|\\w|\\W]{25})(\\d{12})(\\d{6})(\\d).{4}(.*)"); 
	
    protected String    recipientBg;
	protected String	ocrRef;
	protected double	amount;
	protected int		statusCode;
	protected Date		payDate;
	protected String	ourRefText;
	
	public LbTk16Record() {
		super("16");
	}
	
    /**
     * Use this constructor if the payment is to a bank account. Then the
     * bank account is specified in corresponding a TK40 record.
     * @param amount
     * @param ourRef
     */
	public LbTk16Record(double amount, String ourRef, Date payDate) {
		super("16");
        this.recipientBg = null;
		this.amount = Math.abs(amount);
		ourRefText = ourRef;
        this.payDate = payDate;
	}

    /**
     * Use this constructor if the payment is to a BG account
     * Monitor date is set to one year ahead from today.
     * @param amount
     * @param ourRef
     */
	public LbTk16Record(String recipientBg, double amount, String ourRef) {
		super("16");
        this.recipientBg = NotimaUtil.toDigitsOnly(recipientBg);
		this.amount = Math.abs(amount);
		ourRefText = ourRef;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        this.payDate = cal.getTime();
	}
	

	@Override
	public LbTk16Record parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			recipientBg = m.group(1);
			ocrRef = m.group(2).trim();
			amount = NotimaUtil.parseAmountStr(m.group(3));
			try {
				payDate = NotimaUtil.parseDateString(m.group(4));
			} catch (java.text.ParseException pe) {
				throw new BgParseException("Can't parse date " + m.group(4), line);
			}
			try {
				statusCode = Integer.parseInt(m.group(5));
			} catch (Exception ee) {
				throw new BgParseException("Can't parse status code: " + m.group(5));
			}
			ourRefText = m.group(6).trim();
		} else {
			throw new BgParseException(line);
		}
		return null;
	}

	public String getRecipientBg() {
		return recipientBg;
	}

	public void setRecipientBg(String recipientBg) {
		this.recipientBg = recipientBg;
	}

	public String getOcrRef() {
		return ocrRef;
	}

	public void setOcrRef(String ocrRef) {
		this.ocrRef = ocrRef;
	}

	/**
	 * Returns a negative amount since it's a credit.
	 * @return	The amount for this record
	 */
	public double getAmount() {
		return -amount;
	}

	public void setAmount(double amount) {
		this.amount = Math.abs(amount);
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getOurRefText() {
		return ourRefText;
	}

	public void setOurRefText(String ourRefText) {
		this.ourRefText = ourRefText;
	}

	@Override
	public String toRecordString() {
		
		StringBuffer line = new StringBuffer(getTransCode());

		StringBuffer seqStr;
        if (recipientBg==null) {
            seqStr = new StringBuffer(Integer.toString(seqNo));
            while(seqStr.length()<9) {
                // Prepend with zeroes
                seqStr.insert(0, "0");
            }
            // No check digit, leave empty
            seqStr.append(" ");
        } else {
            seqStr = new StringBuffer(recipientBg);
            while(seqStr.length()<10) {
                // Prepend with zeroes
                seqStr.insert(0, "0");
            }
        }
		line.append(seqStr);
		StringBuffer ocrStr = new StringBuffer(ocrRef!=null ? ocrRef : "");
		// Right pad with spaces
		while(ocrStr.length()<25) {
			ocrStr.append(" ");
		}
		line.append(ocrStr);
		line.append(NotimaUtil.getAmountStr(amount));
		line.append(NotimaUtil.getDateString(payDate));
		// Append reserve field
		line.append("     ");
		if (ourRefText!=null) line.append(ourRefText);
		while(line.length()<80) {
			line.append(" ");
		}
		if (line.length()>80) {
			line.setLength(80);
		}
		return line.toString();
		
		
	}

}
