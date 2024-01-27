package org.notima.test.bg;

import org.junit.Before;
import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.autogiro.AutogiroFile;
import org.notima.bg.reference.BgCustomer;

public class TestAgPaymentRequest {
    
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAgConsentReport() {
		
		try {
		
			BgCustomer recipient = TestUtil.getBgCustomer(1);
			BgCustomer payer = TestUtil.getBgCustomer(2);
			AutogiroFile agPaymentRequestFile = AutogiroFile.createPaymentRequestFile(recipient);
            // TODO: create payment request
			
			String result = agPaymentRequestFile.writeToString(BgFile.BGFILE_CHARSET);
			System.out.println(result);
			
		} catch (Exception bpe) {
			bpe.printStackTrace();
		}
		
	}


}
