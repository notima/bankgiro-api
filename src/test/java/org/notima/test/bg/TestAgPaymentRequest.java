package org.notima.test.bg;

import org.junit.Before;
import org.junit.Test;
import org.notima.bg.BgFile;
import org.notima.bg.autogiro.AutogiroFile;
import org.notima.bg.reference.BgAmount;
import org.notima.bg.reference.BgCustomer;
import org.notima.bg.reference.BgReference;
import org.notima.bg.reference.BgTextReference;

public class TestAgPaymentRequest {
    
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAgPaymentRequest() {
		
		try {
		
			BgCustomer recipient = TestUtil.getBgCustomer(1);
			BgCustomer payer = TestUtil.getBgCustomer(2);
			AutogiroFile agPaymentRequestFile = AutogiroFile.createPaymentRequestFile(recipient);
			agPaymentRequestFile.addPaymentRequestRecord(payer, null, 
				new BgAmount(1000), null, new BgTextReference("INVOICE 234"));
			
			String result = agPaymentRequestFile.writeToString(BgFile.BGFILE_CHARSET);
			System.out.println(result);
			
		} catch (Exception bpe) {
			bpe.printStackTrace();
		}
		
	}


}
