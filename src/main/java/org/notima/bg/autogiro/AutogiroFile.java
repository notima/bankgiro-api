package org.notima.bg.autogiro;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Date;
import org.notima.bg.BgFile;
import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.reference.BgAmount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgDate;
import org.notima.bg.reference.BgReference;
import org.notima.bg.reference.InvalidReferenceException;

/**
 * 
 * 
 * @author Daniel Tamm
 *
 */
public class AutogiroFile extends BgFile {
	
	/**
	 * Create a consent report file (skeleton). This file is normally generated
	 * by the bank.
	 * 
	 * @return		A consent report.
	 */
	public static AutogiroFile createConsentReport(BgCustomer recipient) {
		
		AutogiroFile af = new AutogiroFile(AgContentCode.CONSENTS, recipient);

		af.fileFooter = new AgTk09Footer();

		return af;
		
	}

	/**
	 * Create a payment request report.
	 * 
	 * @param recipient
	 * @return		A payment request report.
	 */
	public static AutogiroFile createPaymentRequestFile(BgCustomer recipient) {

		AutogiroFile af = new AutogiroFile(AgContentCode.PAYMENTS, recipient);
		return af;

	}

	public AutogiroFile(String contentCodeStr, BgCustomer recipient) {
		AgContentCode contentCode = null;
		try {
			contentCode = new AgContentCode(AgContentCode.PAYMENTS);
		} catch (InvalidReferenceException ire) {
			// This shoulnd't happen.
		}
		fileHeader = new AgTk01Header(contentCode, recipient);

		AgSet agSet = new AgSet();
		addBgSet(agSet);

	}

	public void addPaymentRequestRecord(BgCustomer payer, LocalDate dueDate, BgAmount amount, AgPaymentInterval pi, BgReference ref) throws Exception {
		
		AgTk82Payment paymentRequest = new AgTk82Payment();
		paymentRequest.setRecipientBgAccount(fileHeader.getRecordOwner().getBgAccount());
		paymentRequest.setAmount(amount);
		paymentRequest.setPaymentInterval(pi);
		paymentRequest.setReference(ref);
		if (dueDate!=null) {
			paymentRequest.setPayDate(new BgDate(dueDate));
		}
		paymentRequest.setPayerNumber(payer.getPayerNumber());

		this.addTransactionToCurrentSet(paymentRequest);

	}
	
	public void addApprovedConsentConfirmationRecord(BgCustomer payer) throws Exception {
		
		AgTk73Consent consent = new AgTk73Consent(
				fileHeader.getRecordOwner().getBgAccount(),
				payer,
				new AgInfoCode(AgInfoCode.INITED_BY_RECIPIENT),
				new AgCommentCode(AgCommentCode.NEW_CONSENT)
				);
		
		addTransactionToCurrentSet(consent);
		
	}
	
	@Override
	public void writeToFile(File file, Charset cs) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void readFromFile(File file, Charset cs) throws IOException, BgParseException {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getFileDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BgFooter generateFileFooter() {
		// TODO Count records and adjust footer accordingly
		return fileFooter;
	}

}
