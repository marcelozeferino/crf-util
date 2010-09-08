package test.java.validator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.validator.DateTimeValidator;

import org.junit.Test;



public class DateTimeValidatorTest extends TestCase{
	
	@Test
	public void testIsValidTime_sucesso1(){
		
		assertEquals(true,DateTimeValidator.isValidTime("10:99"));
		assertEquals(true,DateTimeValidator.isValidTime("99:99"));
		assertEquals(true,DateTimeValidator.isValidTime("99:99"));
		assertEquals(true,DateTimeValidator.isValidTime("00:00"));
		assertEquals(true,DateTimeValidator.isValidTime("10:59"));
		assertEquals(true,DateTimeValidator.isValidTime("00:01"));
		
		
	}
	
	@Test
	public void testIsValidDate_sucesso1(){
				
		/*try{
			new IntegerDate(31,2,2009);
		}catch (Exception e) {
			fail(e.getMessage());
		}*/
		assertEquals(DateTimeValidator.isValidDate(new IntegerDate(29,2,2008)),true);
		
	}
	
	@Test
	public void testIsValidOrdemCronologica_sucesso1(){
		
		List<IntegerDate> datas = new ArrayList<IntegerDate>();
		datas.add(new IntegerDate(31,10,1983));
		datas.add(new IntegerDate(1,11,1983));
		
		assertEquals(true, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		datas.add(new IntegerDate(2,12,2008));
		
		assertEquals(true, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		datas.add(new IntegerDate(10,2,2009));
		datas.add(new IntegerDate(10,2,2009));
		
		assertEquals(true, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		datas.clear();
		datas.add(new IntegerDate(10,10,1999));
		
		assertEquals(true, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
	}

	
	@Test
	public void testIsValidOrdemCronologica_falha1(){
		
		List<IntegerDate> datas = new ArrayList<IntegerDate>();
		
		datas.add(new IntegerDate(10,11,1983));
		datas.add(new IntegerDate(1,11,1983));
		
		assertEquals(false, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		datas.get(0).setStrDate("31/10/1983");
		
		datas.add(new IntegerDate(2,12,1981));
		
		assertEquals(false, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		datas.get(2).setStrDate("02/12/2008");
		
		datas.add(new IntegerDate(10,2,2009));
		datas.add(new IntegerDate(10,2,2009));
		
		assertEquals(true, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		
	}
	
	@Test
	public void testIsValidOrdemCronologica_falha2(){
		
		List<IntegerDate> datas = new ArrayList<IntegerDate>();
		
		datas.add(new IntegerDate(29,9,2009));
		datas.add(new IntegerDate());
		datas.add(new IntegerDate(1,10,2009));
			
				
		assertEquals(true, DateTimeValidator.isValidOrdemCronologica(datas.toArray(new IntegerDate[datas.size()])));
		
		
	}
	
}
