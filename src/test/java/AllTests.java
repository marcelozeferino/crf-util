package test.java;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.java.auditor.junit.AuditorTestSuite;
import test.java.component.campocomposto.CampoCompostoTestSuite;
import test.java.integerdate.IntegerDateTestSuite;
import test.java.validator.ValidatorTestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test.java");
		//$JUnit-BEGIN$
		suite.addTest(AuditorTestSuite.suite());
		suite.addTest(CampoCompostoTestSuite.suite());
		suite.addTest(IntegerDateTestSuite.suite());
		suite.addTest(ValidatorTestSuite.suite());
		//$JUnit-END$
		return suite;
	}

}
