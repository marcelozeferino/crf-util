package test.java.integerdate;

import junit.framework.TestCase;
import main.java.crfutil.component.date.CampoData;
import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.component.date.IntegerRealDate;

import org.junit.Test;


public class IntegerDateAlteracaoData extends TestCase{
	
	@Test
	public void testIntegerDateDtAlteradaDia() {
		
		IntegerRealDate iDate = new IntegerRealDate();
		
		iDate.setStrDate("15/01/2009");
		
		assertEquals("30/01/2009", iDate.obterDataAlterada(15, CampoData.DIA).getStrDate());
		
	}
	
	
	@Test
	public void testIntegerDateDtAlteradaDia2() {
		
		IntegerRealDate iDate = new IntegerRealDate();
		
		iDate.setStrDate("10/01/2009");
		
		assertEquals("31/12/2008", iDate.obterDataAlterada(-10, CampoData.DIA).getStrDate());
		
	}
	
	@Test
	public void testIntegerDateDtAlteradaDia3() {
		
		IntegerRealDate iDate = new IntegerRealDate();
		
		iDate.setStrDate("10/01/2009");
		
		assertEquals("30/12/2008", iDate.obterDataAlterada(-11, CampoData.DIA).getStrDate());
		
	}
	
	@Test
	public void testIntegerDateDtAlteradaMes2() {
		
		IntegerRealDate iDate = new IntegerRealDate();
		
		iDate.setStrDate("30/01/2009");
		
		assertEquals("28/02/2009", iDate.obterDataAlterada(1, CampoData.MES).getStrDate());
		
	}
	
	@Test
	public void testIntegerDateDtAlteradaMes() {
		
		IntegerRealDate iDate = new IntegerRealDate();
		
		iDate.setStrDate("10/01/2009");
		
		assertEquals("10/04/2009", iDate.obterDataAlterada(3, CampoData.MES).getStrDate());
		
	}

	@Test
	public void testIntegerDateDtAlteradaAno() {
		
		IntegerDate iDate = new IntegerDate();
		
		iDate.setStrDate("10/01/2009");
		
		assertEquals("10/01/2011", iDate.obterDataAlterada(2, CampoData.ANO).getStrDate());
		
	}
	
}
