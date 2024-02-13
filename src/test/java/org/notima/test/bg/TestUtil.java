package org.notima.test.bg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.notima.bg.BgParseException;
import org.notima.bg.reference.BgBankAccount;
import org.notima.bg.reference.BgBankgiroAccount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgCustomerNumber;
import org.notima.bg.reference.BgPayerNumber;
import org.notima.bg.reference.BgTaxId;
import org.notima.bg.reference.InvalidReferenceException;
import org.notima.util.InvalidTaxIdFormatException;
import org.notima.util.UnknownTaxIdFormatException;

public class TestUtil {
	
	/**
	 * Return properties for 
	 * 
	 * @param idx
	 * @return
	 * @throws IOException 
	 * @throws UnknownTaxIdFormatException 
	 * @throws InvalidTaxIdFormatException 
	 * @throws InvalidReferenceException 
	 * @throws BgParseException 
	 */
	public static BgCustomer getBgCustomer(int idx) throws IOException, BgParseException, InvalidReferenceException, InvalidTaxIdFormatException, UnknownTaxIdFormatException {
		
		String fileName = "samplefiles/TestBgCustomer" + idx + ".properties";

		BgCustomer customer = BgCustomer.loadFromPropertyFile(fileName);
		
		return customer;
		
	}
	
	/**
	 * 
	 * @return	Payment request file if it exists.
	 */
	public static File getPaymentRequestFile() {
		
		String fileName = "samplefiles/PaymentRequest.csv";

		URL url = TestUtil.class.getClassLoader().getResource(fileName);
		if (url==null) {
			return null;
		}

		File result = new File(url.getFile());

		return result;
		
	}
	
	/**
	 * 
	 * @return		A BgCustomer structure for testing. 
	 */
	public static BgCustomer getTestBgCustomer() {
		
		BgCustomer customer = new BgCustomer();
		customer.setName("Notima System Integration AB");
		customer.setCustomerNo(new BgCustomerNumber("10000"));
		try {
			customer.setTaxId(new BgTaxId("559144-8740", "SE"));
		} catch (InvalidReferenceException | InvalidTaxIdFormatException | UnknownTaxIdFormatException e) {
			e.printStackTrace();
		}
		
		try {
			customer.setBgAccount(new BgBankgiroAccount("503-8880"));
		} catch (BgParseException e) {
			e.printStackTrace();
		}
		
		customer.setPayerNumber(new BgPayerNumber(""));
		
		try {
			customer.setBankAccount(new BgBankAccount("3300", "559144-8740"));
		} catch (BgParseException e) {
			e.printStackTrace();
		}
		
		return customer;
		
	}
	
}
