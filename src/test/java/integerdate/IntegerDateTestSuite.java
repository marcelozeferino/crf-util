package test.java.integerdate;

import junit.framework.Test;
import junit.framework.TestSuite;

public class IntegerDateTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test.java.integerdate");
		//$JUnit-BEGIN$
		suite.addTestSuite(IntegerDateTest.class);
		suite.addTestSuite(IntegerRealDateTest.class);
		suite.addTestSuite(IntegerDateAlteracaoData.class);
		//$JUnit-END$
		return suite;
	}

}
