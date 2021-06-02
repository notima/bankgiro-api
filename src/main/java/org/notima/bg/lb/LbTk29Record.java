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

import java.util.regex.*;

import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.util.NotimaUtil;


/**
 * Footer file
 * 
 * @author Daniel Tamm
 *
 */
public class LbTk29Record extends BgFooter {

	private String 	senderAccount;  
	
	private static Pattern	linePattern1 = Pattern.compile("29(\\d{10})(\\d{8})(\\d{12})(.{0,1}).*");	

	public LbTk29Record() {
		super("29");
	}
	
	@Override
	public BgFooter parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			senderAccount = m.group(1);
			count = Integer.parseInt(m.group(2));
			amount = NotimaUtil.parseAmountStr(m.group(3));
			if ("-".equals(m.group(4))) {
				amount = -amount;
			}
		} else {
			throw new BgParseException(line);
		}
		return this;
	}

	
	public LbTk29Record(String senderAccount, int count, double totalAmount) {
		super("29");
		this.senderAccount = NotimaUtil.toDigitsOnly(senderAccount); 
		this.setCount(count);
		this.setAmount(totalAmount);
	}
	
	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer("29");
		StringBuffer accountStr = new StringBuffer(NotimaUtil.toDigitsOnly(senderAccount));
		while(accountStr.length()<10) {
			accountStr.insert(0, "0");
		}
		line.append(accountStr.toString());
		StringBuffer countStr = new StringBuffer(Integer.toString(count));
		while(countStr.length()<8) {
			countStr.insert(0, "0");
		}
		line.append(countStr);
		StringBuffer amountStr = new StringBuffer(NotimaUtil.getAmountStr(amount));
		while(amountStr.length()<12) {
			amountStr.insert(0, "0");
		}
		line.append(amountStr);
		if (amount<0) {
			line.append("-");
		}
		while(line.length()<80) {
			line.append(" ");
		}
		return line.toString();
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	
	
}
