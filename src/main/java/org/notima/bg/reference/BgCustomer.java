package org.notima.bg.reference;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.notima.bg.BgParseException;
import org.notima.util.InvalidTaxIdFormatException;
import org.notima.util.UnknownTaxIdFormatException;

/**
 * Represents a customer at Bankgirot and the attributes of that customer.
 * 
 * @author Daniel Tamm
 *
 */
public class BgCustomer {

	private BgCustomerNumber customerNo;
	private BgTaxId			 taxId;
	private BgBankgiroAccount	bgAccount;
	private BgBankAccount		bankAccount;
	private BgPayerNumber		payerNumber;
	
	private String name;

	public static BgCustomer loadFromPropertyFile(String fileName) throws IOException, BgParseException, InvalidReferenceException, InvalidTaxIdFormatException, UnknownTaxIdFormatException {

		URL url = BgCustomer.class.getClassLoader().getResource(fileName);
		if (url==null) {
			throw new FileNotFoundException("Can't find in classpath: " + fileName);
		}

		Properties customerProperties = new Properties();
		customerProperties.load(new FileInputStream(url.getFile()));
		
		BgCustomer customer = new BgCustomer();
		String name = customerProperties.getProperty("Name");
		String payerClearing = customerProperties.getProperty("BgPayerBankClearing");
		String payerBankAccount = customerProperties.getProperty("BgPayerBankAccount");
		String payerNumber = customerProperties.getProperty("BgPayerNumber");
		String bgCustomerNo = customerProperties.getProperty("BgCustomerNo");
		String bgBankgiro = customerProperties.getProperty("BgBankgiro");
		String bgTaxId = customerProperties.getProperty("BgTaxId");
		
		if (name!=null)
			customer.setName(name);
		
		if (payerClearing!=null && payerBankAccount!=null) {
			customer.setBankAccount(new BgBankAccount(payerClearing, payerBankAccount));
		}

		if (payerNumber!=null) {
			customer.setPayerNumber(new BgPayerNumber(payerNumber));
		}
		if (bgTaxId!=null) {
			customer.setTaxId(new BgTaxId(bgTaxId, "SE"));
		}

		if (bgCustomerNo!=null) {
			customer.setCustomerNo(new BgCustomerNumber(bgCustomerNo));
		}
		if (bgBankgiro!=null) {
			customer.setBgAccount(new BgBankgiroAccount(bgBankgiro));
		}
		
		return customer;
	}
	
	public BgCustomerNumber getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(BgCustomerNumber customerNo) {
		this.customerNo = customerNo;
	}

	public BgTaxId getTaxId() {
		return taxId;
	}

	public void setTaxId(BgTaxId taxId) {
		this.taxId = taxId;
	}

	public BgBankgiroAccount getBgAccount() {
		return bgAccount;
	}

	public void setBgAccount(BgBankgiroAccount bgAccount) {
		this.bgAccount = bgAccount;
	}

	public BgBankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BgBankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BgPayerNumber getPayerNumber() {
		return payerNumber;
	}

	public void setPayerNumber(BgPayerNumber payerNumber) {
		this.payerNumber = payerNumber;
	}

	
	
}
