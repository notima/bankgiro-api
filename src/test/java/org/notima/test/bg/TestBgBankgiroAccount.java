package org.notima.test.bg;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.notima.bg.BgParseException;
import org.notima.bg.reference.BgBankgiroAccount;

/**
 * Tests the BG Account formatting functions.
 * 
 * @author Daniel Tamm
 *
 */
public class TestBgBankgiroAccount {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBgAccount() {
		
		try {
		
			BgBankgiroAccount account = new BgBankgiroAccount("123-4567");
			assertEquals(account.toMachineFormat(8), "01234567");
			
			account = new BgBankgiroAccount("123-00123");
			assertEquals(account.toHumanReadable(), "1230-0123");
			
			account = new BgBankgiroAccount("34561234");
			assertEquals(account.toMachineFormat(10), "0034561234");
			
		} catch (BgParseException bpe) {
			
		}
		
	}

}
