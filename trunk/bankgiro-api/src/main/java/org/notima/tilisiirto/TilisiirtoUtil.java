package org.notima.tilisiirto;

/**
 * Utility class for working with Finnish banking reference numbers
 * 
 * @author Nils Kohlstrom
 *
 */
public class TilisiirtoUtil {

    /** returns formatted reference number (e.g. to use for printout) */     
    public static String getFormatedReferenceNo(long number) {
        
        String strRes = ""; int cntrlNr = 0;
        cntrlNr = getControlNumber(number);
        strRes = Long.toString(number) + Integer.toString(cntrlNr);
        strRes = formatControlNumbersFi( Long.parseLong(strRes));
        return strRes;
    }
    
    /** returns unformatted reference number (e.g. to use within machine reading) */     
    public static long getReferenceNo(long number) {
        
        String strRes = ""; int cntrlNr = 0;
        cntrlNr = getControlNumber(number);
        strRes = Long.toString(number) + Integer.toString(cntrlNr);
        return Long.parseLong(strRes);
    }
    /** returns unformatted reference number (e.g. to use within machine reading) */     
    public static String getReferenceNoStr(long number) {
        
        String strRes = ""; int cntrlNr = 0;
        cntrlNr = getControlNumber(number);
        strRes = Long.toString(number) + Integer.toString(cntrlNr);
        return (strRes);
    }
    
    public static int getControlNumber(long number) {
        int getControlNumberFi = 0;
        String strNr = ""; int strLen =0; int delSum =0; int cntrlNo = 0; int addNo = 0; String strDelSum = "";
        strNr = Long.toString(number);
        strLen = strNr.length();
        cntrlNo = 1;
        //7 3 1 7 3 1 7 3 1
        while (strLen > 0) {
            strLen = strLen - 1;
            if (cntrlNo == 7)
                cntrlNo = 3;
            else if (cntrlNo == 3)
                cntrlNo = 1;
            else if (cntrlNo == 1)
                cntrlNo = 7;            
            delSum +=  Integer.parseInt(strNr.substring(strLen, strLen + 1)) * cntrlNo ;
        }
        strDelSum = Integer.toString(delSum);
        addNo = 10 - Integer.parseInt( strDelSum.substring(strDelSum.length()-1, strDelSum.length()));
        getControlNumberFi = delSum + addNo - delSum;
        if (getControlNumberFi == 10) getControlNumberFi = 0;
        return getControlNumberFi;
    }
    
    public static String formatControlNumbersFi(long number) {
        //Dim strNr As String, strLen As Integer, retString As String
        
        String strNr = ""; int strLen = 0; String retString = "";
        strNr = Long.toString(number);
        strLen = strNr.length();
        
        retString = " " +  strNr.substring(strNr.length() - 5, strNr.length() - 0); //     Right(strNr, 5)
        if (strLen - 5 > 5) {
            while (strLen > 5) {
                retString = " " + strNr.substring(strLen - 5, strLen);
                strLen = strLen - 5;
            }
            strLen = Math.abs(strLen);
            retString = strNr.substring(0, strLen) + " " + retString;
        } else {
            retString = strNr.substring(0, strLen - 5) + "" + retString;        
        }
        
        return retString;
    }
	
}
