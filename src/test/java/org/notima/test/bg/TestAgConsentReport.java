package org.notima.test.bg;

import org.junit.Before;
import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.autogiro.AutogiroFile;
import org.notima.bg.reference.BgCustomer;

/**
 * Tests the AG consent report (Medgivanden)
 * 
 * @author Daniel Tamm
 *
 */
public class TestAgConsentReport {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAgConsentReport() {
		
		try {
		
			BgCustomer recipient = TestUtil.getBgCustomer(1);
			BgCustomer payer = TestUtil.getBgCustomer(2);
			AutogiroFile agConsentFile = AutogiroFile.createConsentReport(recipient);
			agConsentFile.addApprovedConsentConfirmationRecord(payer);
			
			String result = agConsentFile.writeToString(BgFile.BGFILE_CHARSET);
			System.out.println(result);
			
		} catch (Exception bpe) {
			bpe.printStackTrace();
		}
		
	}

}
