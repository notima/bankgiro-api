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

package se.notima.bg.bgmax;


import java.text.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import se.notima.bg.BgParseException;
import se.notima.bg.BgRecord;
import se.notima.bg.BgUtil;

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
			count = new Integer(m.group(6));
			return(this);
		} else {
			throw new BgParseException(line);
		}
	}

	@Override
	public String toRecordString() {
		// TODO Auto-generated method stub
		return null;
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

}
