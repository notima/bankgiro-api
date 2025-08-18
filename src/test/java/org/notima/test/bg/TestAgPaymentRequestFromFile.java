package org.notima.test.bg;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.autogiro.AgTk82Payment;
import org.notima.bg.autogiro.AutogiroFile;
import org.notima.bg.csv.CsvToAgTk82Payment;
import org.notima.bg.reference.BgCustomer;

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
			// payer = TestUtil.getBgCustomer(2);
			agPaymentRequestFile = AutogiroFile.createPaymentRequestFile(recipient);
			
			readRequestsFromFile();
			
			String result = agPaymentRequestFile.writeToString(BgFile.BGFILE_CHARSET);
			System.out.println(result);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd_HHmm");
			String timestamp = LocalDateTime.now().format(formatter);			
			
			File file = new File("output_" + timestamp +".txt");
			
			try (FileWriter writer = new FileWriter(file)) {
			    writer.write(result);
			}			
			
			
			System.out.println("Fil skapad: " + file.getAbsolutePath());
			
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
