package test.java.auditor.junit;

import test.java.auditor.junit.testcase.AnnotationTest;
import test.java.auditor.junit.testcase.ArgumentosNulosTest;
import test.java.auditor.junit.testcase.AuditorFacadeSimpleTest;
import test.java.auditor.junit.testcase.ObterNomesCamposTest;
import test.java.auditor.junit.testcase.VariaveisDeRefTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AuditorTestSuite extends TestSuite{

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for test.java.auditor.junit.testcase");
		//$JUnit-BEGIN$
		suite.addTestSuite(AnnotationTest.class);
		suite.addTestSuite(AuditorFacadeSimpleTest.class);
		suite.addTestSuite(ObterNomesCamposTest.class);
		suite.addTestSuite(ArgumentosNulosTest.class);
		suite.addTestSuite(VariaveisDeRefTest.class);
		//$JUnit-END$
		return suite;
	}

}
