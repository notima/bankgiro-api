package org.notima.test.bg;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.notima.bg.BgParseException;
import org.notima.bg.BgUtil;


public class TestBgUtil extends TestCase {

	@Test
	public void testGetDateString() {
		
		// Create specific date
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2010);
		cal.set(Calendar.MONTH, 1); // February
		cal.set(Calendar.DATE, 19);
		String result = BgUtil.getDateString(cal.getTime());
		Assert.assertEquals("100219", result);
		
	}

	@Test
	public void testParseDateString() {
		
		try {
			
			java.util.Date date = BgUtil.parseDateString("091231");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2009);
			cal.set(Calendar.MONTH, 11); // December
			cal.set(Calendar.DATE, 31);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Assert.assertEquals(date, cal.getTime());
			
			// Test "Genast"
			date = BgUtil.parseDateString("GENAST");
			Assert.assertNull(date);
			// Test "000000"
			date = BgUtil.parseDateString("000000");
			Assert.assertNull(date);
			
		} catch (ParseException pe) {
			Assert.fail(pe.getMessage());
		}
		
	}

	@Test
	public void testToDigitsOnly() {
		
		String result = BgUtil.toDigitsOnly("1920-1928-12389BH");
		Assert.assertEquals("1920192812389", result);
		
	}

	@Test
	public void testHasDigitsOnly() {
		
		boolean result = BgUtil.hasDigitsOnly("1234-1293");
		Assert.assertEquals(false, result);
		
		result = BgUtil.hasDigitsOnly("12343920");
		Assert.assertEquals(true, result);
		
	}

	@Test
	public void testTrimLeadingZeros() {
		
		String result = BgUtil.trimLeadingZeros("000123890");
		Assert.assertEquals("123890", result);
		
	}

	@Test
	public void testValidateBankgiro() {
	}

	@Test
	public void testFormatBg() {
		
		String result = BgUtil.formatBg("2703029");
		Assert.assertEquals("270-3029", result);
		
	}

	@Test
	public void testFormatPg() {
		
		String actual = BgUtil.formatPg("2093280");
		Assert.assertEquals("209328-0", actual);
		
	}

	@Test
	public void testGetAmountStr() {
		String result = BgUtil.getAmountStr(-100.23, 12, false);
		Assert.assertEquals("00000001002L", result);
	}

	@Test
	public void testParseAmountStr() {
	}

	@Test
	public void testGetLuhnDigit() {
		int result = BgUtil.getLuhnDigit("2876821");
		Assert.assertEquals(6, result);
	}

	@Test
	public void testToOCRNumber() {
	}
	
	@Test
	public void testToOCRNumberWithLengthCheck() {
		String result = BgUtil.toOCRNumberWithLengthCheck("750210001012079");
		Assert.assertEquals("75021000101207972", result);
	}
	

	@Test
	public void testIsValidOCRNumber() {
	}

	@Test
	public void testOnlyUSASCII() {
		
		String result = BgUtil.onlyUSASCII("UMEÅ");
		Assert.assertEquals("UMEA", result);
		result = BgUtil.onlyUSASCII("Gräddvägen");
		Assert.assertEquals("GRADDVAGEN", result);
		
	}
	
	@Test
	public void testDaysFromNow() {
		
		Calendar nowCal = Calendar.getInstance();
		nowCal.add(Calendar.DATE, 60);
		int days = BgUtil.daysFromNow(nowCal.getTime());
		Assert.assertEquals(60, days);
		
	}
	
	
}
