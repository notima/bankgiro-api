package org.notima.bg.reference;

import org.notima.bg.BgParseException;
import org.notima.util.NotimaUtil;

/**
 * Represents a bank account.
 * 
 * @author Daniel Tamm
 *
 */
public class BgBankAccount extends BgReference {

	private String clearingNo;
	private String accountNo;
	
	public BgBankAccount(String clearing, String account) throws BgParseException {
		clearingNo = clearing;
		accountNo = account;
		
		if (clearingNo==null || accountNo==null) 
			throwFormattedParseException();
		
		clearingNo = NotimaUtil.toDigitsOnly(clearingNo);
		accountNo = NotimaUtil.toDigitsOnly(accountNo);
		
		if (clearingNo.length()<4 || clearingNo.length()>5) {
			throwFormattedParseException();
		}
		if (clearingNo.length()==5) {
			// If five digits, the last is a check digit
			// remove it.
			clearingNo = clearingNo.substring(0, 4);
		}
		
		if (accountNo.length()>12) {
			throwFormattedParseException();
		}
		
	}
	
	/**
	 * @param	positionsWide	Values lower than 16 is not honored.
	 */
	@Override
	public String toMachineFormat(int positionsWide) {
		
		StringBuffer buf = new StringBuffer();
		buf.append(clearingNo);
		if (positionsWide<16)
			positionsWide = 16;
		
		String accountWithZeroes = NotimaUtil.fillToLength(accountNo, true, '0', positionsWide-4);
		buf.append(accountWithZeroes);
		
		return buf.toString();
	}
	
	@Override
	public String toHumanReadable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Formats an exception message from the account number and throws it.
	 * 
	 * @param accountNo				The account number that failed.
	 * @throws BgParseException		Always.
	 */
	private void throwFormattedParseException() throws BgParseException {
		if (clearingNo==null || clearingNo.length()==0) {
			throw new BgParseException("Empty string for clearing not allowed.");
		}
		if (accountNo==null || accountNo.length()==0) {
			throw new BgParseException("Empty string for account not allowed.");
		} else 
			throw new BgParseException("Not a valid bank account number: " + clearingNo + "-" + accountNo);
	}
	
	
}
