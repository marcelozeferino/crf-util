package test.java.validator;

import junit.framework.TestCase;
import main.java.crfutil.validator.NumericValidator;

import org.junit.Test;


public class NumericValidatorTest extends TestCase {

	@Test
	public void testIsValidRangeIntIntInt() {
		
		int n1 = 0, n2 = 10, n3 = 9;
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = 10;
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = 0;
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}

		n3 = 11;
		if (NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
	}

	@Test
	public void testIsValidRangeIntIntString() {
		
		int n1 = 0, n2 = 10;
		String n3 = "9";
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "10";
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "0";
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}

		n3 = "11";
		if (NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "";
		if (NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = null;
		if (NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
	}

	
	@Test
	public void testIsValidRangeDoubleDoubleDouble() {
	
		double n1 = 0.01, n2 = 10.00, n3 = 9.00; 
		
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = 10.00;
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = 0.01;
		if (!NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = 11.00;
		if (NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = 0.00;
		if (NumericValidator.isValidRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		
	}

	@Test
	public void testIsValidDoubleRange() {
		
		String n1 = "0.00", n2 = "100.00";
		String n3 = "99.00";
		
		if (!NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "10.00";
		if (!NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "0.00";
		if (!NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}

		n3 = "101.00";
		if (NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "100.01";
		if (NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "100.00";
		if (!NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = "";
		if (NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		n3 = null;
		if (NumericValidator.isValidDoubleRange(n1, n2, n3)){
			fail("Erro na validação");
		}
		
		
	}

}
