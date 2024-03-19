package org.notima.bg.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.notima.bg.autogiro.AgPaymentInterval;
import org.notima.bg.autogiro.AgPaymentIntervalEnum;
import org.notima.bg.autogiro.AgTk82Payment;
import org.notima.bg.reference.BgAmount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgDate;
import org.notima.bg.reference.BgPayerNumber;
import org.notima.bg.reference.BgReference;
import org.notima.bg.reference.BgTextReference;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvToAgTk82Payment {

	private FileReader reader;
	private CSVParser csvParser;
	private BgCustomer recipient;
	
	public CsvToAgTk82Payment(File csvFile, BgCustomer recipient) throws FileNotFoundException {

		reader = new FileReader(csvFile.getAbsolutePath());
		this.recipient = recipient;
	}
	
	
	public List<AgTk82Payment> getRecords() throws IOException {
		
		List<AgTk82Payment> result = new ArrayList<AgTk82Payment>();
		
		csvParser = new CSVParser(reader, CSVFormat.newFormat(';').withHeader());
		
		AgTk82Payment payment;
		BgDate bgDate;
		BgPayerNumber pnumber;
		BgAmount amount;
		BgReference ref;
		
		for (CSVRecord rec : csvParser) {
			
			 bgDate = toBgDate(rec.get("PayDate"));
			 if (bgDate!=null) {
				 pnumber = new BgPayerNumber(rec.get("PayerNumber"));
				 amount = new BgAmount(Double.parseDouble(rec.get("Amount SEK")));
				 ref = new BgTextReference(rec.get("Reference"));
				 payment = new AgTk82Payment();
				 payment.setPaymentInterval(getPaymentIntervalFromString(rec.get("Interval")));
				 payment.setRecipientBgAccount(recipient.getBgAccount());
				 payment.setPayDate(bgDate);
				 payment.setPayerNumber(pnumber);
				 payment.setAmount(amount);
				 payment.setReference(ref);
				 result.add(payment);
			 }
			
		}
		
		return result;
	}
	
	private AgPaymentInterval getPaymentIntervalFromString(String pi) {
		
		if (pi==null || pi.trim().length()==0) return new AgPaymentInterval(AgPaymentIntervalEnum.ONCE);

		int interval = Integer.parseInt(pi);
		
		return new AgPaymentInterval(interval);
		
	}
	
	private BgDate toBgDate(String dateStr) {
		
		if (dateStr==null) return null;
		
		BgDate result = new BgDate(dateStr);
		
		return result;
		
	}
	
}
