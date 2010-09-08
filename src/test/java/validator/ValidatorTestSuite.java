package test.java.validator;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.java.validator.difdata.DifDatasValidatorAnoTest;
import test.java.validator.difdata.DifDatasValidatorDiaTest;
import test.java.validator.difdata.DifDatasValidatorMesTest;

public class ValidatorTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test.java.validator.difdata");
		//$JUnit-BEGIN$
		suite.addTestSuite(DifDatasValidatorDiaTest.class);
		suite.addTestSuite(DifDatasValidatorMesTest.class);
		suite.addTestSuite(DifDatasValidatorAnoTest.class);
		suite.addTestSuite(DateTimeValidatorTest.class);
		suite.addTestSuite(NumericValidatorTest.class);
		//$JUnit-END$
		return suite;
	}

}
