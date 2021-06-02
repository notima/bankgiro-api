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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.util.NotimaUtil;


public class LbTk40Record extends BgRecord {

	protected String	clearingNo;
	protected String	accountNo;
	protected String	infoText;
	protected boolean	isSalary;

	// private static Pattern	linePattern1 = Pattern.compile("40(\\d{10})(\\d{4})(.*)"); // From BG format
	private static Pattern	linePattern2 = Pattern.compile("40(\\d{9})(\\d|\\s)(\\d{4})(\\d{12}).*"); // If bank account
	
	public LbTk40Record() {
		super("40");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern2.matcher(line);
		if (m.matches()) {
			seqNo = Integer.parseInt(m.group(1));
			clearingNo = m.group(3);
			accountNo = NotimaUtil.trimLeadingZeros(m.group(4));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}
	
	
	public LbTk40Record(String dstAccount, String comment) {
		super("40");
		boolean swedbank = dstAccount.startsWith("8");
		// Try to figure out clearing no
		int pos = dstAccount.indexOf("-");
		if (pos>0) {
			clearingNo = dstAccount.substring(0, pos - (swedbank ? 1 : 0));
			if (pos<(dstAccount.length()-1)) {
				accountNo = dstAccount.substring(pos+1,dstAccount.length());
			}
		} else {
			// No hyphen to indicate clearing id. Suggest the four first digits
			// to be clearing unless it's a Swedbank number with 5.
			// Swedbank's clearing number's fifth digit should be removed when
			// creating an LB-record.
			if (dstAccount.length()>4) {
				clearingNo = dstAccount.substring(0,4);
				accountNo = dstAccount.substring(swedbank ? 5 : 4, dstAccount.length());
			}
		}
		infoText = comment;
	}
	
	public String getClearingNo() {
		return clearingNo;
	}
	
	/**
	 * 
	 * @param aClearingNo
	 */
	public void setClearingNo(String aClearingNo) {
		this.clearingNo = NotimaUtil.toDigitsOnly(aClearingNo);
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String aAccountNo) {
		this.accountNo = NotimaUtil.toDigitsOnly(aAccountNo);
	}
	
	/**
	 * Written on the account statement of the recipient
	 * Maximum of 12 characters.
	 * 
	 * @return	InfoText
	 */
	public String getInfoText() {
		return infoText;
	}
	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}
	

	public String getFullAccountNo() {
		// Create accountno
		StringBuffer accountNoBuf = new StringBuffer();
		char c;
		// Only use digits (remove everything else)
		for (int i=0;i<accountNo.length();i++) {
			c = accountNo.charAt(i);
			if (c>='0' && c<='9') accountNoBuf.append(c);
		}
		// Left pad with zeroes to 12 characters
		while(accountNoBuf.length()<12) {
			accountNoBuf.insert(0, "0");
		}
		
		// Create clearing no
		StringBuffer clearingNoBuf = new StringBuffer();
		// Only use digits
		for (int i=0; i<clearingNo.length(); i++) {
			c = clearingNo.charAt(i);
			if (c>='0' && c<='9') clearingNoBuf.append(c);
		}
		if (clearingNo!=null && clearingNo.startsWith("8") && 
				clearingNo.length()>4) {
				
		}
				
		
		// if length > 4 move digits to account no
		if (clearingNoBuf.length()>4 && clearingNo.startsWith("8")) {
			// Swedbank, chop the fifth clearing no digit
			clearingNoBuf.setLength(4);
		} else {
			for (int i=clearingNoBuf.length()-1; i>3; i--) {
				accountNoBuf.setCharAt(i-4, clearingNoBuf.charAt(i));
			}
		}
		while(clearingNoBuf.length()<4) {
			clearingNoBuf.insert(0, "0");
		}
		// Clearing no is only 4 digits
		clearingNoBuf.setLength(4);
		return(clearingNoBuf.toString() + accountNoBuf.toString());
		
	}
	
	public boolean isSalary() {
		return isSalary;
	}
	public void setSalary(boolean isSalary) {
		this.isSalary = isSalary;
	}
	
	/**
	 * Converts the information in this record to a BG-record
	 * 
	 * @return	A BG-record represented as a string (line)
	 */
	public String toRecordString() {
		StringBuffer line = new StringBuffer(transCode);
		// Add reserve field
		line.append("0000");
		// Create sequence field
		StringBuffer seqBuf = new StringBuffer(Integer.toString(seqNo));
		while(seqBuf.length()<5) {
			seqBuf.insert(0,"0");
		}
		// Append blank since we don't have a check digit
		seqBuf.append(" ");
		line.append(seqBuf);
		
		String fullAccountStr = getFullAccountNo();
		line.append(fullAccountStr);
		
		StringBuffer refBuf = new StringBuffer();
		if (getInfoText()!=null) refBuf.append(getInfoText());
		if (refBuf.length()>12) {
			refBuf.setLength(12);
		} else {
			while(refBuf.length()<12) {
				refBuf.append(" ");
			}
		}
		line.append(refBuf.toString());
		
		if (isSalary()) {
			line.append("L");
		} else {
			line.append(" ");
		}
		// Fill line to length = 80
		while(line.length()<80) {
			line.append(" ");
		}
		
		return(line.toString());
	}

	
}
