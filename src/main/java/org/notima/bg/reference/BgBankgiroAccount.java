package org.notima.bg.reference;

import org.notima.bg.BgParseException;
import org.notima.util.NotimaUtil;

/**
 * This class represents a BgAccount (Bankgironummer).
 * <br>
 * The purpose of the class is to easily validate and use bankgiro numbers.
 * 
 * @author Daniel Tamm
 *
 */
public class BgBankgiroAccount extends BgReference {

	private String internalFormat; // Eight characters

	/**
	 * Creates a BG account from a string.
	 * 
	 * @param accountNo				The bg number. Only the digits in the string are considered
	 * @throws BgParseException		If the string can't be parsed to a BG-number.
	 */
	public BgBankgiroAccount(String accountNo) throws BgParseException {
		
		if (accountNo==null || accountNo.trim().length()<7)
			throwFormattedParseException(accountNo);
		
		String digitsOnly = NotimaUtil.toDigitsOnly(accountNo);
		// Length check again
		if (digitsOnly.length()<7 || digitsOnly.length()>8)
			throwFormattedParseException(accountNo);
		
		// Standardize the format by prepending a zero if length is 7
		internalFormat = NotimaUtil.fillToLength(digitsOnly, true, '0', 8);
		
	}
	
	/**
	 * Formats the bankgiro number to human readable format.
	 * 
	 * @return		The bankgiro number formatted as NNN-NNNN or NNNN-NNNN 	
	 */
	public String toHumanReadable() {
		
		String part1 = null;
		if (internalFormat.startsWith("0")) {
			part1 = internalFormat.substring(1, 4);
		} else {
			part1 = internalFormat.substring(0, 4);
		}
		
		String part2 = internalFormat.substring(4);
		
		return part1 + "-" + part2;
	}
	
	/**
	 * Formats the bankgiro number for use in BG-files.
	 * 
	 * @param positionsWide		Values lower than 8 means 8 characters wide.
	 * @return		Bankgiro number formatted for file use.  
	 */
	public String toMachineFormat(int positionsWide) {

		return NotimaUtil.fillToLength(internalFormat, true, '0', Math.max(positionsWide, 8));
		
	}

	/**
	 * Formats an exception message from the account number and throws it.
	 * 
	 * @param accountNo				The account number that failed.
	 * @throws BgParseException		Always.
	 */
	private void throwFormattedParseException(String accountNo) throws BgParseException {
		if (accountNo==null || accountNo.length()==0) {
			throw new BgParseException("Empty string not allowed.");
		} else 
			throw new BgParseException("Not a bankgiro number: '" + accountNo + "'");
	}
	
}
