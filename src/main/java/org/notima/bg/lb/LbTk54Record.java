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
import org.notima.bg.BgUtil;
import org.notima.util.NotimaUtil;


/**
 * Girering till plusgiro
 *
 * @author Daniel Tamm
 */
public class LbTk54Record extends BgRecord implements LbPaymentRecord {

	private static Pattern	linePattern1 = Pattern.compile("54(\\d{10})(.{25})(\\d{12})(\\d|\\w)(\\d{10})(.*)"); // From BG format
	
    private String recipientPg;
    private String recipientPgRedirected;
    private String processCode;
    private double amount;
    private String ocrRef;
    private Date   payDate;
    private String ourRefText;


    public LbTk54Record() {
        super("54");
    }
    
    public LbTk54Record(String recipientPg, String ocrRef, double amount, String ourRefText, Date payDate) {
        super("54");
        this.recipientPg = NotimaUtil.toDigitsOnly(recipientPg);
        this.amount = amount;
        this.ocrRef = NotimaUtil.toDigitsOnly(ocrRef);
        this.ourRefText = ourRefText;
        this.payDate = payDate;
    }

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			recipientPg = NotimaUtil.trimLeadingZeros(m.group(1));
			ocrRef = m.group(2).trim();
			amount = NotimaUtil.parseAmountStr(m.group(3));
			processCode = m.group(4);
			recipientPgRedirected = NotimaUtil.trimLeadingZeros(m.group(5));
			ourRefText = m.group(6).trim();
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}
    
    @Override
    public String toRecordString() {

		StringBuffer line = new StringBuffer(getTransCode());

		StringBuffer seqStr = new StringBuffer(recipientPg);
		while(seqStr.length()<10) {
			// Prepend with zeroes
			seqStr.insert(0, "0");
		}
		line.append(seqStr);
		StringBuffer ocrStr = new StringBuffer(ocrRef!=null ? ocrRef : "");
		// Right pad with spaces
		while(ocrStr.length()<25) {
			ocrStr.append(" ");
		}
		line.append(ocrStr);
		line.append(BgUtil.getAmountStr(amount));
		line.append(BgUtil.getDateString(payDate));
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

    /**
     * @return the recipientPg
     */
    public String getRecipientPg() {
        return recipientPg;
    }

    /**
     * @param recipientPg the recipientPg to set
     */
    public void setRecipientPg(String recipientPg) {
        this.recipientPg = recipientPg;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the ocrRef
     */
    public String getOcrRef() {
        return ocrRef;
    }

    /**
     * @param ocrRef the ocrRef to set
     */
    public void setOcrRef(String ocrRef) {
        this.ocrRef = ocrRef;
    }

    /**
     * @return the payDate
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * @param payDate the payDate to set
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * @return the ourRef
     */
    public String getOurRef() {
        return ourRefText;
    }

    /**
     * @param ourRef the ourRef to set
     */
    public void setOurRef(String ourRef) {
        this.ourRefText = ourRef;
    }

	public String getRecipientPgRedirected() {
		return recipientPgRedirected;
	}

	public void setRecipientPgRedirected(String recipientPgRedirected) {
		this.recipientPgRedirected = recipientPgRedirected;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}


}
