package test.java.validator.difdata;

import junit.framework.TestCase;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.validator.DifDatasValidator;
import main.java.crfutil.validator.DifDatasValidator.Operador;
import main.java.crfutil.validator.DifDatasValidator.TipoDiferencaEnum;

import org.junit.Test;



public class DifDatasValidatorAnoTest extends TestCase{
	
	@Test
	public void testDifDatasValida_menor(){
		
	 	assertEquals(true, 
	 		new DifDatasValidator()
			.comOperador(Operador.MENOR)
			.comTipoDiferenca(TipoDiferencaEnum.ANOS)
			.comDiferenca(2)
			.comDataInicial(new IntegerDate(8,8,2008))
			.comDataFinal(new IntegerDate(6,8,2009))
			.diferencaValidaEntreDatas());
		
	}
	
	@Test
	public void testDifDatasValida_menorIgual1(){
		
	 	assertEquals(true, 
	 		new DifDatasValidator()
			.comOperador(Operador.MENOR_IGUAL)
			.comTipoDiferenca(TipoDiferencaEnum.ANOS)
			.comDiferenca(0)
			.comDataInicial(new IntegerDate(6,8,2009))
			.comDataFinal(new IntegerDate(8,8,2009))
			.diferencaValidaEntreDatas());
		
	}
	
	@Test
	public void testDifDatasValida_menorIgual(){
		
	 	assertEquals(true, 
	 		new DifDatasValidator()
			.comOperador(Operador.MENOR_IGUAL)
			.comTipoDiferenca(TipoDiferencaEnum.ANOS)
			.comDiferenca(3)
			.comDataInicial(new IntegerDate(6,8,2006))
			.comDataFinal(new IntegerDate(9,8,2009))
			.diferencaValidaEntreDatas());
		
	}
	
	
	@Test
	public void testDifDatasValida_maior(){
		
	 	assertEquals(true, 
	 		new DifDatasValidator()
			.comOperador(Operador.MAIOR)
			.comTipoDiferenca(TipoDiferencaEnum.ANOS)
			.comDiferenca(3)
			.comDataInicial(new IntegerDate(10,8,1500))
			.comDataFinal(new IntegerDate(6,8,2009))
			.diferencaValidaEntreDatas());
		
	}
	
	@Test
	public void testDifDatasValida_maiorIgual(){
		
	 	assertEquals(true, 
	 		new DifDatasValidator()
			.comOperador(Operador.MAIOR_IGUAL)
			.comTipoDiferenca(TipoDiferencaEnum.ANOS)
			.comDiferenca(0)
			.comDataInicial(new IntegerDate(6,8,2009))
			.comDataFinal(new IntegerDate(11,8,2009))
			.diferencaValidaEntreDatas());
		
	}

	@Test
	public void testDifDatasValida_maiorIgual2(){
		
	 	assertEquals(true, 
	 		new DifDatasValidator()
			.comOperador(Operador.MAIOR_IGUAL)
			.comTipoDiferenca(TipoDiferencaEnum.ANOS)
			.comDiferenca(18)
			.comDataInicial(new IntegerDate(31,10,1983))
			.comDataFinal(new IntegerDate(19,01,2009))
			.diferencaValidaEntreDatas());
		
	}
	
	
}

