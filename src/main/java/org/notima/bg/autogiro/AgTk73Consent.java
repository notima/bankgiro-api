package org.notima.bg.autogiro;

import java.util.Date;

import org.notima.bg.BgParseException;
import org.notima.bg.BgRecord;
import org.notima.bg.Transaction;
import org.notima.bg.reference.BgBankAccount;
import org.notima.bg.reference.BgBankgiroAccount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgDate;
import org.notima.bg.reference.BgPayerNumber;
import org.notima.bg.reference.BgTaxId;
import org.notima.util.NotimaUtil;

/**
 * Describes a given consent.
 * 
 * @author Daniel Tamm
 *
 */
public class AgTk73Consent extends BgRecord implements Transaction {

	protected BgBankgiroAccount recipientBg;
	protected BgPayerNumber payerNumber;
	protected BgBankAccount payerBankAccountNo;
	protected BgTaxId payerTaxId;
	protected AgInfoCode infoCode;
	protected AgCommentCode commentCode;
	protected BgDate	 registrationDate;

	/**
	 * Creates a new consent record.
	 * 
	 * @param recipient			The recipient for the consent.
	 * @param payer				The payer
	 * @param infoCode			
	 * @param commentCode
	 */
	public AgTk73Consent(BgBankgiroAccount recipient, BgCustomer payer, AgInfoCode infoCode, AgCommentCode commentCode) {
		super("73");
		setRegistrationDate(new BgDate());
		recipientBg = recipient;
		payerNumber = payer.getPayerNumber();
		payerBankAccountNo = payer.getBankAccount();
		payerTaxId = payer.getTaxId();
		this.infoCode = infoCode;
		this.commentCode = commentCode;
	}
	
	@Override
	public BgRecord parse(String line) throws BgParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toRecordString() {

		StringBuffer line = new StringBuffer(getTransCode());
		line.append(recipientBg.toMachineFormat(10));
		line.append(payerNumber.toMachineFormat(16));
		line.append(payerBankAccountNo.toMachineFormat(16));
		line.append(payerTaxId.toMachineFormat(12));
		line.append(NotimaUtil.fillToLength("", false, ' ', 5));
		line.append(infoCode.toMachineFormat(2));
		line.append(commentCode.toMachineFormat(2));
		line.append(registrationDate.toMachineFormat(8));
		line.append(NotimaUtil.fillToLength("", true, ' ', 7));
		
		return line.toString();
	}
	
	public BgBankgiroAccount getRecipientBg() {
		return recipientBg;
	}

	public void setRecipientBg(BgBankgiroAccount recipientBg) {
		this.recipientBg = recipientBg;
	}
	
	public BgPayerNumber getPayerNumber() {
		return payerNumber;
	}

	public void setPayerNumber(BgPayerNumber payerNumber) {
		this.payerNumber = payerNumber;
	}

	public BgBankAccount getPayerBankAccountNo() {
		return payerBankAccountNo;
	}

	public void setPayerBankAccountNo(BgBankAccount payerBankAccountNo) {
		this.payerBankAccountNo = payerBankAccountNo;
	}

	public BgDate getRegistrationDate() {
		return registrationDate;
	}
	
	public void setRegistrationDate(BgDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}
