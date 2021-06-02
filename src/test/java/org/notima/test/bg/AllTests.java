package org.notima.test.bg;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.notima.test.bg");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestBgMax.class);
		//$JUnit-END$
		return suite;
	}

}
