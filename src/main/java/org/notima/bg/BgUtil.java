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

package org.notima.bg;

import java.text.*;

import org.notima.util.NotimaUtil;

/**
 *
 * @author Daniel Tamm
 */
public class BgUtil {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");

	/**
	 * Method for converting date to standard date format for BG-files If the date
	 * is null it equals to immediate payment.
	 *
	 * @param d Date to be converted.
	 * @return A date in BG-format. If null, GENAST is returned.
	 */
	public static String getDateString(java.util.Date d) {
		if (d == null)
			return ("GENAST");
		return (dateFormat.format(d));
	}

	/**
	 * Return the date. If the date string is empty or "GENAST", null is returned.
	 * 
	 * @param dateStr Parses date format from BG.
	 * @return A java date.
	 * @throws ParseException if the string can't be parsed.
	 */
	public static java.util.Date parseDateString(String dateStr) throws ParseException {
		if (dateStr == null || dateStr.trim().length() == 0 || "GENAST".equalsIgnoreCase(dateStr)
				|| "000000".equals(dateStr)) {
			return (null);
		}
		return (dateFormat.parse(dateStr));
	}

	/**
	 * Perform very basic validation (length checks)
	 * 
	 * @param swift Swift to be validated.
	 * @param iban  Iban to be validated.
	 * @return True if the IBAN passes a basic validation
	 */
	public static boolean validateIban(String swift, String iban) {
		boolean pass = false;
		if (swift != null && swift.trim().length() > 0 && iban != null && iban.trim().length() > 0) {
			pass = true;
		}
		return (pass);
	}

	/**
	 * Validate Bankgiro
	 * 
	 * @param Bankgiro A string containing the bankgiro to be validated.
	 * @return true if the bankgiro has passed length test.
	 */
	public static boolean validateBankgiro(String Bankgiro) {
		int length = NotimaUtil.toDigitsOnly(Bankgiro).length();
		if (length > 0 && length < 10)
			return true;
		return false;
	} // validateBankgiro

	/**
	 * Format bankgiro Formats a sequence of digits to the general form of a
	 * bankgiro account. If it's not a bg account no formatting occurs, the string
	 * is returned as is. (ie \d{3,4}-\d{4})
	 *
	 * @param digits The digits to be formatted.
	 * @return A human readable formatted bg number (ex 444-5546)
	 */
	public static String formatBg(String digits) {
		String fmt = NotimaUtil.toDigitsOnly(digits);
		fmt = NotimaUtil.trimLeadingZeros(fmt);
		if (fmt.length() < 7 || fmt.length() > 8)
			return (digits);
		if (fmt.length() == 8) {
			return (fmt.substring(0, 4) + "-" + fmt.substring(4));
		} else {
			return (fmt.substring(0, 3) + "-" + fmt.substring(3));
		}
	}

	/**
	 * Format plusgiro Formats a sequence of digits to the general form of a
	 * postgiro account.
	 * 
	 * @param digits Digits to be formatted.
	 * @return A human readable plusgirot account.
	 */
	public static String formatPg(String digits) {
		String fmt = NotimaUtil.toDigitsOnly(digits);
		fmt = NotimaUtil.trimLeadingZeros(fmt);
		if (fmt.length() < 2) {
			return (fmt);
		} else {
			return (fmt.substring(0, fmt.length() - 1) + "-" + fmt.substring(fmt.length() - 1, fmt.length()));
		}
	}

	/**
	 * Formats taxId to BGMax format (10 digits)
	 * 
	 * @param taxId The tax id to be formatted.
	 * @return A tax id formated as required in the BGMax-format (10 digits, no
	 *         dashes)
	 * @throws ParseException if the tax ID can't be recognized.
	 */
	public static String formatTaxId(String taxId) throws ParseException {

		// Convert to digits only
		String taxDigits = NotimaUtil.toDigitsOnly(taxId);
		if (taxDigits.length() > 12) {
			throw new ParseException(taxId + " invalid.", 0);
		}
		if (taxDigits.length() == 12) {
			taxDigits = (String) taxDigits.subSequence(2, taxDigits.length() - 1);
		}

		return taxDigits;
	}

	/**
	 * Converts a double to 12 digits where the two last digits are "öre" or
	 * "cents". Negative amounts are returned as absolute (no negative indicator)
	 * 
	 * @param amount The amount to be formatted.
	 * @return A string representation of an amount in BG-format.
	 */
	public static String getAmountStr(double amount) {
		return (getAmountStr(amount, 12, true));
	}

	/**
	 * 
	 * @param amount   The amount to be converted.
	 * @param len      Let you specify the length of the string
	 * @param absolute If true no negative indicator. If false the last digit is
	 *                 replaced with a letter according to a specific pattern to
	 *                 indicate a negative value.
	 * @return The amount in string representation
	 */
	public static String getAmountStr(double amount, int len, boolean absolute) {
		StringBuffer buf = new StringBuffer();
		long newAmount = Math.round(Math.abs(amount) * 100.0);
		buf.append(Long.toString(newAmount));
		while (buf.length() < len) {
			buf.insert(0, "0");
		}
		// Replace if negative and not absolute
		if (amount < 0 && !absolute) {
			String minusStr;
			char lastDigit = buf.charAt(buf.length() - 1);
			switch (lastDigit) {
			case '0':
				minusStr = "-";
				break;
			case '1':
				minusStr = "J";
				break;
			case '2':
				minusStr = "K";
				break;
			case '3':
				minusStr = "L";
				break;
			case '4':
				minusStr = "M";
				break;
			case '5':
				minusStr = "N";
				break;
			case '6':
				minusStr = "O";
				break;
			case '7':
				minusStr = "P";
				break;
			case '8':
				minusStr = "Q";
				break;
			case '9':
				minusStr = "R";
				break;
			default:
				minusStr = "E";
				break; // Illegal character, shouldn't happen
			}
			// Replace last digit
			buf.replace(buf.length() - 1, buf.length(), minusStr);
		}
		return (buf.toString());
	}

	public static double parseAmountStr(String amountStr) {
		Double amount = Double.parseDouble(amountStr);
		amount = amount / 100.0;
		return (amount);
	}

	public static boolean validateBankAccount(String clearing, String accountNo) {
		if (clearing == null || accountNo == null)
			return false;
		String clr = NotimaUtil.toDigitsOnly(clearing);
		String no = NotimaUtil.toDigitsOnly(accountNo);
		if (clr.length() < 4 || clr.length() > 5) {
			return false;
		}
		if (no.length() < 5 || clr.length() > 10) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param clearing  Routing number (clearing)
	 * @param accountNo Account number
	 * @return Trims account from all non digits. Separates the clearing and account
	 *         no with a hyphen. '-'
	 */
	public static String getAccountString(String clearing, String accountNo) {
		String clr = NotimaUtil.toDigitsOnly(clearing);
		String no = NotimaUtil.toDigitsOnly(accountNo);
		return (clr + "-" + no);
	}

}
