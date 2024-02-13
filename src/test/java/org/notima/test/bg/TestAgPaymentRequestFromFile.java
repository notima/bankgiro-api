package org.notima.test.bg;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.autogiro.AgTk82Payment;
import org.notima.bg.autogiro.AutogiroFile;
import org.notima.bg.csv.CsvToAgTk82Payment;
import org.notima.bg.reference.BgAmount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgTextReference;

public class TestAgPaymentRequestFromFile {

	private BgCustomer recipient;
	private BgCustomer payer;
	private AutogiroFile agPaymentRequestFile;
	private File	   paymentRequestFile;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAgPaymentRequestFromFile() {
		
		try {
		
			recipient = TestUtil.getBgCustomer(1);
			payer = TestUtil.getBgCustomer(2);
			agPaymentRequestFile = AutogiroFile.createPaymentRequestFile(recipient);
			
			readRequestsFromFile();
			
			String result = agPaymentRequestFile.writeToString(BgFile.BGFILE_CHARSET);
			System.out.println(result);
			
		} catch (Exception bpe) {
			bpe.printStackTrace();
		}
		
	}

	
	private void readRequestsFromFile() throws Exception {
		
		paymentRequestFile = TestUtil.getPaymentRequestFile();
		if (paymentRequestFile==null) return;
		
		CsvToAgTk82Payment fromCsv = new CsvToAgTk82Payment(paymentRequestFile, recipient);
		
		List<AgTk82Payment> records = fromCsv.getRecords();
		
		for (AgTk82Payment payment : records) {
			agPaymentRequestFile.addTransactionToCurrentSet(payment);
		}
		
	}
	

}
