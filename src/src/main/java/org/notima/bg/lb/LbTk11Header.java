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

package org.notima.bg.lb;

import java.util.*;
import java.util.regex.*;

import org.notima.bg.BgHeader;
import org.notima.bg.BgParseException;
import org.notima.bg.BgUtil;


public class LbTk11Header extends BgHeader {

	private String 			bgAccount;
	private java.util.Date	fileDate;
	private java.util.Date	payDate;	// Leave as null unless all records in this section have the same paydate
	private String			currency = "";

	private static Pattern	linePattern1 = Pattern.compile("11(\\d{10})(\\d{6})LEV.*");
	private static Pattern	linePattern2 = Pattern.compile(".*?BETALNINGAR(.{6}).{13}(.{3}).*");
	
	public LbTk11Header() {
		super("11");
	}
	
	/**
	 * Creates a new header using current time as file date.
	 * @param senderAccount
	 */
	public LbTk11Header(String senderAccount) {
		super("11");
		bgAccount = senderAccount;
		setHeaderType("LEVERANT\u00d6RSBETALNINGAR");
		fileDate = Calendar.getInstance().getTime();
	}

	public String getSenderAccount() {
		return(bgAccount);
	}
	
	/**
	 * If empty it defaults to SEK.
	 * @return
	 */
	public String getCurrency() {
		if (currency==null || currency.trim().length()==0) return("SEK");
		return currency;
	}

	/**
	 * The only valid currencies are:
	 * <li>SEK</li>
	 * <li>EUR</li>
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public java.util.Date getPayDate() {
		return payDate;
	}

	public void setPayDate(java.util.Date payDate) {
		this.payDate = payDate;
	}
	
	
	public String getBgAccount() {
		return bgAccount;
	}
	
	public void setBgAccount(String bgAccount) {
		this.bgAccount = bgAccount;
	}
	
	public java.util.Date getFileDate() {
		return fileDate;
	}
	
	public void setCreateDate(java.util.Date fileDate) {
		this.fileDate = fileDate;
	}
	
	public LbTk11Header parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			// We have a match of the first required part.
			bgAccount = BgUtil.trimLeadingZeros(m.group(1));
			try {
				fileDate = BgUtil.parseDateString(m.group(2));
			} catch (java.text.ParseException pe) {
				throw new BgParseException("File date: " + m.group(2) + " not valid. ", line);
			}
			m = linePattern2.matcher(line);
			if (m.matches()) {
				currency = m.group(2);
				try {
					payDate = BgUtil.parseDateString(m.group(1));
				} catch (java.text.ParseException pe2) {
					throw new BgParseException("Pay date: " + m.group(1) + " not valid. ", line);
				}
			}
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}
	
	public String toRecordString() {
		// Create "post-record"
		StringBuffer line = new StringBuffer("11");
		
		// Create bg-account
		StringBuffer account = new StringBuffer();
		if (bgAccount!=null) {
			char c;
			for (int i=0; i<bgAccount.length(); i++) {
				c = bgAccount.charAt(i);
				if (c>='0' && c<='9') {
					account.append(c);
				}
			}
		}
		// Left-pad with zeroes
		while(account.length()<10) {
			account.insert(0, '0');
		}
		line.append(account);
		
		if (fileDate==null) {
			fileDate = Calendar.getInstance().getTime();
		}
		String dateStr = BgUtil.getDateString(fileDate);
		line.append(dateStr);
		line.append(getHeaderType());
		if (payDate!=null) {
			line.append(BgUtil.getDateString(payDate));
		} else {
			line.append("      ");
		}
		line.append("             "); // Reserve 13 char
		line.append(currency);
        while(line.length()<80) {
            line.append(" ");
        }
		
		return(line.toString());
	}

	@Override
	public Date getCreateDate() {
		return fileDate;
	}
	
}
