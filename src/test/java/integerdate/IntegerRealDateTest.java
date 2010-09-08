package test.java.integerdate;

import java.util.GregorianCalendar;

import junit.framework.TestCase;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.component.date.IntegerRealDate;

import org.junit.Test;



public class IntegerRealDateTest extends TestCase {
	
	@Test
	public void testIntegerDateIntIntInt() {
		
		IntegerDate iDate = new IntegerRealDate(30,04,2009);
		
		assertEquals("20090430", iDate.getIntDate().toString());
		assertEquals("30/04/2009", iDate.getStrDate() );
		
		assertEquals(2009, iDate.getAno() );
		assertEquals(4, iDate.getMes() );
		assertEquals(30, iDate.getDia() );
		
	}
	

	@Test
	public void testGetDia() {
		
		IntegerDate iDate = new IntegerRealDate();
		iDate.setIntDate(20090313);
		
		assertEquals( 13 , iDate.getDia() );
		
	}

	
	@Test
	public void testGetMes() {

		IntegerDate iDate = new IntegerRealDate();
		iDate.setIntDate(20090313);
		
		assertEquals( 3 , iDate.getMes() );
		
	}

	
	@Test
	public void testGetAno() {

		IntegerDate iDate = new IntegerRealDate();
		iDate.setIntDate(20090313);
		
		assertEquals( 2009 , iDate.getAno() );

	}

	@Test
	public void testAsDate() {
		
		IntegerDate iDate = new IntegerRealDate(13,3,2009);
		
		assertEquals( new GregorianCalendar(2009,3-1,13).getTime(), iDate.asDate());
		
		
	}

	/*
	@Test
	public void testGetIntDate() {
		
		IntegerDate iDate = new IntegerRealDate(99,99,9999);	
		assertEquals( "99999999", iDate.getIntDate().toString() );
		
		iDate.setStrDate("88/88/8888");
		assertEquals( "88888888", iDate.getIntDate().toString() );
	}

	
	@Test
	public void testGetStrDate() {
		
		IntegerDate iDate = new IntegerRealDate(88,88,8888);
		assertEquals("88/88/8888", iDate.getStrDate() );
		
	}

	
	@Test
	public void testSetIntDate() {
	
		IntegerDate iDate = new IntegerRealDate();
		iDate.setIntDate(88888888);
		
		Integer i = 88888888;
		
		boolean result = i.equals(iDate.getIntDate());
		assertTrue( result );
		
	}

	@Test
	public void testSetStrDate() {
		
		IntegerDate iDate = new IntegerRealDate();
		iDate.setStrDate("99/99/9999");
		
		String s = "99/99/9999";
		
		boolean result =  s.equals( iDate.getStrDate() );
		assertTrue( result );
		
		result = s.equals( iDate.toString() );
		assertTrue( result );
		
		
	}

	@Test
	public void testToString() {
		
		IntegerDate iDate = new IntegerRealDate();
		iDate.setIntDate(88888888);
		
		String s = "88/88/8888";
		String sDate = iDate.toString();
		
		boolean result = s.equals( sDate );
		assertTrue( result );
				
		
	}

*/
	}
