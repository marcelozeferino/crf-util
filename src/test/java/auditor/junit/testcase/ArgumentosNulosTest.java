package test.java.auditor.junit.testcase;


import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import junit.framework.TestCase;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import org.junit.Test;

import test.java.auditor.objetosauditaveis.Cliente;
import test.java.auditor.objetosauditaveis.Pessoa;

public class ArgumentosNulosTest extends TestCase{

	@Test
	public void testRealizarAuditoriaObjetoNull() {
		
		Pessoa p = new Pessoa();
		Pessoa p2 = new Pessoa(1,"Fulano",33);
				
			try {

				Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(p,p2);							
				
				assertEquals(resultado.size(), 3);
				for(Divergencia div : resultado){
					
					System.out.println(div);
					
				}
				
			} catch (ObjetosDeClassesDiferentesException e) {
				fail(e.getMessage());
				
			} catch (ListaDeMetodosVaziaException e) {
				
				fail(e.getMessage());
			} catch (ClassNotFoundException e) {
				
				fail(e.getMessage());
			} catch (IllegalAccessException e) {
				
				fail(e.getMessage());
			} catch (InvocationTargetException e) {
				
				fail(e.getMessage());
			}catch (Exception e) {			
				fail(e.getMessage());
			}
		
		
	}
	
	@Test
	public void testRealizarAuditoriaRefInternaNull(){
		
		
		Cliente cliente = new Cliente(1, "Marcelo", 10.2, false, null);

		Cliente conjuge2 = new Cliente(2, "Ana", 50.5, true, null);
		Cliente cliente2 = new Cliente(1, "Claudio", 10.2, true, conjuge2);

		System.out.println("Primeiro objeto ====== \nCliente: "
				+ cliente.getNome() + " - Conjuge: null" 
				/*+ cliente.getConjuge().getNome()*/);

		System.out.println("Segundo objeto ====== \nCliente: "
				+ cliente2.getNome() + " - Conjuge: " + cliente2.getConjuge().getNome());
				
			try {

				Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(cliente, cliente2);
				
				/*
				 * 6 divergencias: Nome e Ativo(cliente)
				 * 				   Id, Nome, Salario, Ativo (conjuge)	
				 */
				assertEquals(6, resultado.size());
				
				System.out.println("");
				
				for(Divergencia div : resultado){
					
					System.out.println(div);
					
				}
				
			} catch (ObjetosDeClassesDiferentesException e) {
				fail(e.getMessage());
				
			} catch (ListaDeMetodosVaziaException e) {
				
				fail(e.getMessage());
			} catch (ClassNotFoundException e) {
				
				fail(e.getMessage());
			} catch (IllegalAccessException e) {
				
				fail(e.getMessage());
			} catch (InvocationTargetException e) {
				
				fail(e.getMessage());
			}catch (Exception e) {			
				fail(e.getMessage());
			}
			
	}
	
	@Test
	public void testRealizarAuditoriaDoisObjVazios() {
		
		Pessoa pVazio = new Pessoa();
		Pessoa p2Vazio = new Pessoa();
				
			try {

				Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(pVazio,p2Vazio);											
				
				assertEquals(resultado.size(), 0);
				
				if ( resultado.size() > 0 ){

					for(Divergencia div : resultado){
						
						System.out.println(div);
						
					}							
					
				}else{
					System.out.println("Sem divergências...");
				}
				
				
			} catch (ObjetosDeClassesDiferentesException e) {
				fail(e.getMessage());
				
			} catch (ListaDeMetodosVaziaException e) {
				
				fail(e.getMessage());
			} catch (ClassNotFoundException e) {
				
				fail(e.getMessage());
			} catch (IllegalAccessException e) {
				
				fail(e.getMessage());
			} catch (InvocationTargetException e) {			
				fail(e.getMessage());
			}catch (Exception e) {			
				fail(e.getMessage());
			}
	}

			


}
