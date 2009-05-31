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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

/**
 * Girering till plusgiro
 *
 * @author Daniel Tamm
 */
public class LbTk54Record extends BgRecord implements LbPaymentRecord {

	private static Pattern	linePattern1 = Pattern.compile("54(\\d{10})([\\d|\\s]{25})(\\d{12})(\\d|\\w)(\\d{10})(.*)"); // From BG format
	
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
        this.recipientPg = BgUtil.toDigitsOnly(recipientPg);
        this.amount = amount;
        this.ocrRef = BgUtil.toDigitsOnly(ocrRef);
        this.ourRefText = ourRefText;
        this.payDate = payDate;
    }

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			recipientPg = BgUtil.trimLeadingZeros(m.group(1));
			ocrRef = m.group(2).trim();
			amount = BgUtil.parseAmountStr(m.group(3));
			processCode = m.group(4);
			recipientPgRedirected = BgUtil.trimLeadingZeros(m.group(5));
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
