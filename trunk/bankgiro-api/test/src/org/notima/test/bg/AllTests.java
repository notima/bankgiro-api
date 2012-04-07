package org.notima.test.bg;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for se.notima.test.bg");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestBgUtil.class);
		suite.addTestSuite(TestBgMax.class);
		//$JUnit-END$
		return suite;
	}

}
