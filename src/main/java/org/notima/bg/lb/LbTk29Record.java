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

import java.util.regex.*;

import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.BgUtil;


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
			amount = BgUtil.parseAmountStr(m.group(3));
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
		this.senderAccount = BgUtil.toDigitsOnly(senderAccount); 
		this.setCount(count);
		this.setAmount(totalAmount);
	}
	
	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer("29");
		StringBuffer accountStr = new StringBuffer(BgUtil.toDigitsOnly(senderAccount));
		while(accountStr.length()<10) {
			accountStr.insert(0, "0");
		}
		line.append(accountStr.toString());
		StringBuffer countStr = new StringBuffer(Integer.toString(count));
		while(countStr.length()<8) {
			countStr.insert(0, "0");
		}
		line.append(countStr);
		StringBuffer amountStr = new StringBuffer(BgUtil.getAmountStr(amount));
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
