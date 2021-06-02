package org.notima.test.bg;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.BgMaxFile;
import org.notima.bg.BgSet;
import org.notima.util.NotimaUtil;
import org.notima.bg.Transaction;
import org.notima.bg.bgmax.BgMaxReceipt;
import org.notima.bg.bgmax.BgMaxSet;



/*
 * JUnit test for reading BGMax file. Note, for this to work the sample files must be in the 
 * current class path.
 * The sample files are found in the directory test/samplefiles.
 */
public class TestBgMax extends TestCase {

	@Test
	public void testReadBgMax() {
		
		URL url = ClassLoader.getSystemResource("samplefiles/BGMAX_Sample1.txt");
		if (url==null) {
			Assert.fail("Can't find BGMAX_Sample1.txt in class path. The files are normally located in test/samplefiles");
		}
		File reportFile = new File(url.getFile());
        BgFile inFile = new BgMaxFile();
        Date fileDate;
        Charset cs = Charset.forName("ISO8859-1"); // Default (other options Cp850)
        try {
        	inFile.readFromFile(reportFile, cs);
        	fileDate = inFile.getFileDate();
        	// Test file date
        	Assert.assertEquals("100324", NotimaUtil.getDateString(fileDate));
        	
        	// Test recipient accounts
        	Assert.assertEquals(true, ((BgMaxFile)inFile).getBgRecipients().contains("2407617"));
        	
        	List<BgSet> bgSets = inFile.getRecords();
        	BgMaxSet bgMaxSet;
        	
        	// This file contains two sets
        	Assert.assertEquals(2, bgSets.size());
        	
        	// Iterate through the sets
        	int setCounter = 0;
        	Vector<Transaction> trans;
        	BgMaxReceipt receipt;
        	for (Iterator<BgSet> it = bgSets.iterator(); it.hasNext(); setCounter++) {
        		bgMaxSet = (BgMaxSet)it.next();
        		Assert.assertEquals("SEK", bgMaxSet.getCurrency()); // Check Currency
        		Assert.assertEquals("2407617", bgMaxSet.getRecipientBankAccount()); // Check recipient BG
        		
        		// Test each set individually, set 1
        		if (setCounter==0) {
        			trans = bgMaxSet.getRecords();
        			// This sample contains only one record
        			receipt = (BgMaxReceipt)trans.get(0);
        			Assert.assertEquals("51602332", receipt.getSenderBg());
        			Assert.assertEquals("100252            1000014", receipt.getReference()); // Invoice no and customer no, ref generated from SPCS
        			Assert.assertEquals(3, receipt.getReferenceType()); // String reference type
        			// Org id is supplied
        			Assert.assertEquals("5565554762", receipt.getTaxId());
        			// Test amount received
        			Assert.assertEquals(37087.0, receipt.getAmount());
        		}
        		
        		// Test set 2
        		if (setCounter==1) {
        			trans = bgMaxSet.getRecords();
        			// This sample contains only one record
        			receipt = (BgMaxReceipt)trans.get(0);
        			Assert.assertEquals("1002633", receipt.getReference()); // OCR reference
        			Assert.assertEquals(2, receipt.getReferenceType()); // OCR reference
        			Assert.assertEquals(14375.0, receipt.getAmount());
        		}
        		
        	}
        	
        } catch (Exception e) {
        	e.printStackTrace(System.err);
        }
		
	}
	
}
