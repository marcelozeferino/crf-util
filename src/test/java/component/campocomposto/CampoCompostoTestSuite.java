package test.java.component.campocomposto;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CampoCompostoTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for test.java.component.campocomposto");
		//$JUnit-BEGIN$
		suite.addTestSuite(CampoCompostoTest.class);
		//$JUnit-END$
		return suite;
	}

}
