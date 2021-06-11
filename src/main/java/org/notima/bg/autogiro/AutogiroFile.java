package org.notima.bg.autogiro;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.notima.bg.BgFile;
import org.notima.bg.BgFooter;
import org.notima.bg.BgParseException;
import org.notima.bg.BgSet;
import org.notima.bg.reference.BgCustomer;
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
		
		AutogiroFile af = new AutogiroFile();
		AgContentCode contentCode = null;
		try {
			contentCode = new AgContentCode(AgContentCode.CONSENTS);
		} catch (InvalidReferenceException ire) {
			// This shoulnd't happen.
		}
		AgTk01Header header = new AgTk01Header(contentCode, recipient);
		
		af.fileHeader = header;
		af.fileFooter = new AgTk09Footer();
		
		AgSet agSet = new AgSet();
		af.addBgSet(agSet);

		return af;
		
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
