package org.notima.tilisiirto;

/**
 * Utility class for working with Finnish banking reference numbers
 * 
 * @author nilskm (Nils Kohlström)
 * 
 */
public class TilisiirtoUtil {

	/** returns formatted reference number (e.g. to use for printout) */
	public static String getFormatedReferenceNo(long number, boolean zeroFill) {

		String strRes = "";
		int cntrlNr = 0;
		cntrlNr = getControlNumber(number);
		strRes = Long.toString(number) + Integer.toString(cntrlNr);
		strRes = formatControlNumbersFi(strRes, true);
		return strRes;
	}

	/**
	 * returns unformatted reference number (e.g. to use within machine reading)
	 */
	public static long getReferenceNo(long number) {

		String strRes = "";
		int cntrlNr = 0;
		cntrlNr = getControlNumber(number);
		strRes = Long.toString(number) + Integer.toString(cntrlNr);
		return Long.parseLong(strRes);
	}

	/**
	 * returns unformatted reference number (e.g. to use within machine reading)
	 */
	public static String getReferenceNoStr(long number) {

		String strRes = "";
		int cntrlNr = 0;
		cntrlNr = getControlNumber(number);
		strRes = Long.toString(number) + Integer.toString(cntrlNr);
		return (strRes);
	}

	public static int getControlNumber(long number) {
		int getControlNumberFi = 0;
		String strNr = "";
		int strLen = 0;
		int partSum = 0;
		int cntrlNo = 0;
		int addNo = 0;
		String strDelSum = "";
		strNr = Long.toString(number);
		strLen = strNr.length();
		cntrlNo = 1;
		// 7 3 1 7 3 1 7 3 1
		while (strLen > 0) {
			strLen = strLen - 1;
			if (cntrlNo == 7)
				cntrlNo = 3;
			else if (cntrlNo == 3)
				cntrlNo = 1;
			else if (cntrlNo == 1)
				cntrlNo = 7;
			partSum += Integer.parseInt(strNr.substring(strLen, strLen + 1))
					* cntrlNo;
		}
		strDelSum = Integer.toString(partSum);
		addNo = 10 - Integer.parseInt(strDelSum.substring(
				strDelSum.length() - 1, strDelSum.length()));
		getControlNumberFi = partSum + addNo - partSum;
		if (getControlNumberFi == 10)
			getControlNumberFi = 0;
		return getControlNumberFi;
	}

	public static String formatControlNumbersFi(String number, boolean zeroFill) {

		if (number==null)
			return null;
		int strLen = 0;
		String retString = "";

		strLen = number.length();

		retString = " "
				+ number.substring(number.length() - 5, number.length());
		strLen -= 5;
		if (strLen > 5) {
			while (strLen > 5) {
				String add = number.substring(strLen - 5, strLen);
				retString = " " + add + retString;
				strLen = strLen - 5;
			}
			retString = retString.trim();
			strLen = Math.abs(strLen);
			retString = number.substring(0, strLen) + " " + retString;
		} else {
			retString = number.substring(0, strLen) + "" + retString;
		}
		if (zeroFill) {
			int i = 0;
			while (i < (5 - strLen)) {
				retString = "0" + retString;
				i++;
			}
		}
		return retString.trim();
	}
}
