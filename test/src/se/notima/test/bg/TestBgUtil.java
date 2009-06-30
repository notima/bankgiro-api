package se.notima.test.bg;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import se.notima.bg.BgParseException;
import se.notima.bg.BgUtil;

public class TestBgUtil {

	@Test
	public void testGetDateString() {
	}

	@Test
	public void testParseDateString() {
	}

	@Test
	public void testToDigitsOnly() {
	}

	@Test
	public void testHasDigitsOnly() {
	}

	@Test
	public void testTrimLeadingZeros() {
	}

	@Test
	public void testValidateBankgiro() {
	}

	@Test
	public void testFormatBg() {
		
		try {
			String result = BgUtil.formatBg("2703029");
			Assert.assertEquals("270-3029", result);
		} catch (BgParseException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testFormatPg() {
		
		try {
			String actual = BgUtil.formatPg("2093280");
			Assert.assertEquals("209328-0", actual);
		} catch (BgParseException e) {
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testGetAmountStr() {
	}

	@Test
	public void testParseAmountStr() {
	}

	@Test
	public void testGetLuhnDigit() {
	}

	@Test
	public void testToOCRNumber() {
	}

	@Test
	public void testIsValidOCRNumber() {
	}

}
