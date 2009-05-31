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

package se.notima.bg;

import java.text.*;
import java.util.Calendar;


/**
 *
 * @author Daniel Tamm
 */
public class BgUtil {

	private static SimpleDateFormat	dateFormat = new SimpleDateFormat("yyMMdd");


	/**
	 * Method for converting date to standard date format for BG-files
	 * If the date is null it equals to immediate payment.
	 *
	 * @param d
	 * @return
	 */
	public static String getDateString(java.util.Date d) {
		if (d==null) return("GENAST");
		return(dateFormat.format(d));
	}

	/**
	 * Return the date. If the date string is empty or "GENAST", null is returned.
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static java.util.Date parseDateString(String dateStr) throws ParseException {
		if (dateStr==null || dateStr.trim().length()==0 
			|| "GENAST".equalsIgnoreCase(dateStr)
			|| "000000".equals(dateStr)) {
			return(null);
		}
		return(dateFormat.parse(dateStr));
	}
	
	/**
	 * Remove all non digit characters
	 *
	 * @param cleanUp
	 * @return
	 */
	public static String toDigitsOnly(String cleanUp) {
		if (cleanUp==null) return("");
		StringBuffer buf = new StringBuffer();
		char c;
		for (int i=0; i<cleanUp.length();i++) {
			c = cleanUp.charAt(i);
			if (c>='0' && c<='9') {
				buf.append(c);
			}
		}
		return(buf.toString());
	}

    public static boolean hasDigitsOnly(String ref) {
        if (ref==null || ref.trim().length()==0) return(false);
        char c;
        for (int i=0; i<ref.length(); i++) {
            c = ref.charAt(i);
            if (!(c>='0' && c<='9')) {
                return(false);
            }
        }
        return true;
    }

	public static String trimLeadingZeros(String cleanUp) {
		if (cleanUp==null) return("");
		cleanUp = cleanUp.trim();
		if (cleanUp.length()==0) return("");
		char c;
		int i=0;
		while(i<cleanUp.length() && (c=cleanUp.charAt(i))=='0') {
			i++;
		}
		return(cleanUp.substring(i));
	}
	
	/**
	 *  Validate Bankgiro
	 *  @param Bankgiro
	 *  @return "" or Error AD_Message
	 */
	public static boolean validateBankgiro (String Bankgiro)
	{
		int length = BgUtil.toDigitsOnly(Bankgiro).length();
		if (length > 0 && length < 10)
			return true;
		return false;
	}   //  validateBankgiro

	/**
	 * Format bankgiro
	 * Formats a sequence of digits to the general form of a bankgiro account.
	 * If it's not a bg account no formatting occurs, the string is returned as is. 
	 * (ie \d{3,4}-\d{4})
	 */
	public static String formatBg(String digits) throws BgParseException {
		String fmt = toDigitsOnly(digits);
		fmt = trimLeadingZeros(fmt);
		if (fmt.length()<7 || fmt.length()>8) return(digits);
		if (fmt.length()==8) {
			return(fmt.substring(0,4) + "-" + fmt.substring(4));
		} else {
			return(fmt.substring(0,3) + "-" + fmt.substring(3));
		}
	}
	
	/**
	 * Converts a double to 12 digits where the two last digits
	 * are "öre" or "cents"
	 *
	 * @return
	 */
	public static String getAmountStr(double amount) {
		StringBuffer buf = new StringBuffer();
		long newAmount = Math.round(amount*100.0);
		buf.append(new Long(newAmount).toString());
		while(buf.length()<12) {
			buf.insert(0, "0");
		}
		return(buf.toString());
	}
	
	public static double parseAmountStr(String amountStr) {
		Double amount = new Double(amountStr);
		amount = amount / 100.0;
		return(amount);
	}

	/**
	 * Luhn algoritm.
	 * @param indata
	 * @return
	 */
	public static int getLuhnDigit(String indata) {
		int a = 2;
		int sum = 0;
		int term;
		for (int i = indata.length() - 1; i >= 0; i--) {
		  term = Character.digit(indata.charAt(i),10) * a;
		  if ( term > 9) term -= 9;
		  sum += term;
		  a = 3 - a;
		}
		int tens = sum/10*10;
		int subtractfrom = tens+10;
		return((subtractfrom - sum)%10);
	}

	/**
	 * Converts a string of digits to the equivalent OCR-number
	 * @param indata
	 * @return
	 */
	public static String toOCRNumber(String indata) {
		indata = toDigitsOnly(indata);
		return(indata + getLuhnDigit(indata));
    }
	
	/**
	 *  Validate OCR number.
	 *  - Based on LUHN formula (Modulus10)
	 *  @param OCRNumber
	 *  @return True if OCR is correct
	 */
	public static boolean isValidOCRNumber (String OCR)
	{
		if (OCR == null || OCR.length() == 0)
			return false;

		/**
		 *  1:  Double the value of alternate digits beginning with
		 *      the	first right-hand digit (low order).
		 *  2:  Add the individual digits comprising the products
		 *      obtained in step 1 to each of the unaffected digits
		 *      in the original number.
		 *  3:  Subtract the total obtained in step 2 from the next higher
		 *      number ending in 0 [this in the equivalent of calculating
		 *      the "tens complement" of the low order digit (unit digit)
		 *      of the total].
		 *      If the total obtained in step 2 is a number ending in zero
		 *      (30, 40 etc.), the check digit is 0.
		 *  Example:
		 *  Account number: 4992 73 9871 6
		 *
		 *  4  9  9  2  7  3  9  8  7  1  6
		 *    x2    x2    x2    x2    x2
		 *  -------------------------------
		 *  4 18  9  4  7  6  9 16  7  2  6
		 *
		 *  4 + 1 + 8 + 9 + 4 + 7 + 6 + 9 + 1 + 6 + 7 + 2 + 6 = 70
		 *  70 % 10 = 0
		 */

		//  Clean up number
		String ccNumber1 = toDigitsOnly(OCR);
		int ccLength = ccNumber1.length();
		//  Reverse string
		StringBuffer buf = new StringBuffer();
		for (int i = ccLength; i != 0; i--)
			buf.append(ccNumber1.charAt(i-1));
		String ccNumber = buf.toString();

		int sum = 0;
		for (int i = 0; i < ccLength; i++)
		{
			int digit = Character.getNumericValue(ccNumber.charAt(i));
			if (i % 2 == 1)
			{
				digit *= 2;
				if (digit > 9)
					digit -= 9;
			}
			sum += digit;
		}

		if (sum % 10 == 0)
			return true;

		return false;
	}   //  validateOCRNumber


}
