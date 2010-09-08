package test.java.component.campocomposto;

import junit.framework.TestCase;

import main.java.crfutil.component.campocomposto.CampoComposto;

import org.junit.Test;



public class CampoCompostoTest extends TestCase {
	
	@Test
	public void testValidar_sucesso1(){
		
		CampoComposto c = new CampoComposto();
		CampoComposto c2 = new CampoComposto();
		CampoComposto c3 = new CampoComposto();
		CampoComposto c4 = new CampoComposto();
		
		c.setResposta(1);
		c.setGrau(2);
		c2.setResposta(0);
		c2.setGrau(-1);
		c3.setResposta(-1);
		c3.setGrau(-1);
				
		try{
			c.validar(false);
			c2.validar(false);
			c3.validar(true);
			c4.validar(true);
		}catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
	}

	
	@Test
	public void testValidar_falha1(){
		
		CampoComposto c = new CampoComposto();
		
		c.setResposta(0);
		c.setGrau(2);
			
		try{
			
			c.validar(false);

		}catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	

	@Test
	public void testValidar_falha2(){
		
		CampoComposto c = new CampoComposto();
		
		c.setResposta(-1);
		c.setGrau(2);
			
		try{
			
			c.validar(false);

		}catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testValidar_falha3(){
		
		CampoComposto c = new CampoComposto();
		
		c.setResposta(-1);
		c.setGrau(2);
			
		try{
			
			c.validar(true);

		}catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testValidar_falha4(){
		
		CampoComposto c = new CampoComposto();
		
		c.setResposta(1);
		c.setGrau(6);
			
		try{
			
			c.validar(false);

		}catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testValidar_falha5(){
		
		CampoComposto c = new CampoComposto();
			
		try{
			
			c.validar(false);

		}catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
	
	@Test
	public void testNaoPreenchido(){
		
		CampoComposto c = new CampoComposto();
		
		try{
			
			boolean test = c.naoPreechido();
			
			assertTrue(test);
			
		}catch (IllegalArgumentException e) {
			
			System.out.println(e.getMessage());
			
		}catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
}
