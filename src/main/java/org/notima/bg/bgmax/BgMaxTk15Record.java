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

package org.notima.bg.bgmax;


import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.BgUtil;

public class BgMaxTk15Record extends BgRecord {

	private static Pattern	linePattern1 = Pattern.compile("15(\\d{35})(\\d{8})(\\d{5})(\\d{18})(\\w{3})(\\d{8})(.*)");	
	private static SimpleDateFormat	dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private String	toBankAccount;
	private String	clearing;
	private String	accountNo;
	private Date	transactionDate;
	private String	receiptNo;
	private double	amount;
	private String	currency;
	private int		count;
	
	public BgMaxTk15Record() {
		super("15");
	}

	@Override
	public BgRecord parse(String line) throws BgParseException {
		Matcher m = linePattern1.matcher(line);
		if (m.matches()) {
			toBankAccount = m.group(1);
			clearing = BgUtil.trimLeadingZeros(toBankAccount.substring(19, 23));
			accountNo = toBankAccount.substring(23, 35);
			try {
				transactionDate = dateFormat.parse(m.group(2));
			} catch (ParseException pe) {
				throw new BgParseException("Set date: " + m.group(2) + " not valid. ", line);
			}
			receiptNo = m.group(3);
			amount = BgUtil.parseAmountStr(m.group(4));
			currency = m.group(5);
			count = Integer.parseInt(m.group(6));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {
		StringBuffer line = new StringBuffer(this.getTransCode());
		
		// Bank (clearing)
		line.append(BgUtil.fillToLength(BgUtil.toDigitsOnly(clearing), false, '0', 4));
		// Bank (account number)
		line.append(BgUtil.fillToLength(BgUtil.toDigitsOnly(accountNo), true, '0', 31));
		// Date of payments
		line.append(dateFormat.format(transactionDate));
		// Receipt No
		line.append(BgUtil.fillToLength(receiptNo, true, '0', 5));
		// Total amount
		line.append(BgUtil.getAmountStr(amount, 18, false));
		// Currency
		line.append(BgUtil.fillToLength(currency, true, 'X', 3));
		// Number of payments
		line.append(BgUtil.fillToLength(Integer.toString(count), true, '0', 8));
		// Type of receipt
		line.append(" "); // Normally blank
		
		return line.toString();
	}

	public String getToBankAccount() {
		return toBankAccount;
	}

	public void setToBankAccount(String toBankAccount) {
		this.toBankAccount = toBankAccount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getClearing() {
		return clearing;
	}

	public void setClearing(String clearing) {
		this.clearing = clearing;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
