package test.java.auditor;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import test.java.auditor.objetosauditaveis.Cliente;
import test.java.auditor.objetosauditaveis.Pessoa;

public class ArgumentosNulosTest {
	
	
	public static void main (String []args){

		System.out.println("=========== Início do Teste 1  ============ \n");
				
		Pessoa p = new Pessoa();
		Pessoa p2 = new Pessoa(1,"Fulano",33);
				
			try {

				Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(p,p2);							
				
				for(Divergencia div : resultado){
					
					System.out.println(div);
					
				}
				
			} catch (ObjetosDeClassesDiferentesException e) {
				System.err.println("Errp: " + e.getMessage());
				
			} catch (ListaDeMetodosVaziaException e) {
				
				System.err.println("Errp: " + e.getMessage());
			} catch (ClassNotFoundException e) {
				
				System.err.println("Errp: " + e.getMessage());
			} catch (IllegalAccessException e) {
				
				System.err.println("Errp: " + e.getMessage());
			} catch (InvocationTargetException e) {
				
				System.err.println("Errp: " + e.getMessage());
			}catch (Exception e) {			
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

			
			System.out.println("\n =========== Início do Teste 2  ============ \n");
			
			//Cliente conjuge = new Cliente(2, "Marcela", 10.1, true, null);
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
					
					System.out.println("");
					
					for(Divergencia div : resultado){
						
						System.out.println(div);
						
					}
					
				} catch (ObjetosDeClassesDiferentesException e) {
					System.err.println("Errp: " + e.getMessage());
					
				} catch (ListaDeMetodosVaziaException e) {
					
					System.err.println("Errp: " + e.getMessage());
				} catch (ClassNotFoundException e) {
					
					System.err.println("Errp: " + e.getMessage());
				} catch (IllegalAccessException e) {
					
					System.err.println("Errp: " + e.getMessage());
				} catch (InvocationTargetException e) {
					
					System.err.println("Errp: " + e.getMessage());
				}catch (Exception e) {			
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			
		
				

				System.out.println(" \n =========== Início do Teste 3  ============ \n");
						
				Pessoa pVazio = new Pessoa();
				Pessoa p2Vazio = new Pessoa();
						
					try {

						Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(pVazio,p2Vazio);											
						
						if ( resultado.size() > 0 ){

							for(Divergencia div : resultado){
								
								System.out.println(div);
								
							}							
							
						}else{
							System.out.println("Sem divergências...");
						}
						
						
					} catch (ObjetosDeClassesDiferentesException e) {
						System.err.println("Errp: " + e.getMessage());
						
					} catch (ListaDeMetodosVaziaException e) {
						
						System.err.println("Errp: " + e.getMessage());
					} catch (ClassNotFoundException e) {
						
						System.err.println("Errp: " + e.getMessage());
					} catch (IllegalAccessException e) {
						
						System.err.println("Errp: " + e.getMessage());
					} catch (InvocationTargetException e) {
						
						System.err.println("Errp: " + e.getMessage());
					}catch (Exception e) {			
						System.out.println(e.getMessage());
						e.printStackTrace();
					}

		
	}
	

}
