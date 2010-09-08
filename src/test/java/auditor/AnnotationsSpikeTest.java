package test.java.auditor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.anotacao.NaoAuditavel;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import test.java.auditor.objetosauditaveis.PessoaComAnnotation;

public class AnnotationsSpikeTest {
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		//Instanciando e obtendo valores da classe de maneira simples		
		PessoaComAnnotation pessoa = new PessoaComAnnotation();

		
		try {
			
			 // Instanciando a classe Pessoa
			
			
			 Class<?> c = Class.forName( pessoa.getClass().getName() );
			 
			 System.out.println("\nLista de Métodos ================= ");
			 
			 //Obtendo array com a lista de métodos da classe
			 Method [] m = c.getDeclaredMethods();
			 
			 if (m.length == 0){
				 System.out.println("Sem métodos");
			 }else{
				 for (Method method : m) {
			
					 NaoAuditavel naoAuditavel = method.getAnnotation(NaoAuditavel.class);
					 
					 if ( naoAuditavel != null ){
						 System.out.println(method.getName() + " - com anotação");
					 }else{
						 System.out.println(method.getName()+ " - sem anotação");	 
					 }
					 
				 }	 
			 }
			 

		} catch (Throwable t) {
			t.printStackTrace();
		}
		
	
		System.out.println("\nInício de teste com método de auditoria ================= ");
		
		//Teste com pessoas iguais
		PessoaComAnnotation pessoaTeste1 = new PessoaComAnnotation(1,"Marcelo",25);
		PessoaComAnnotation pessoaTeste2 = new PessoaComAnnotation(1,"Marcelo",25);
		
		/*
		 * Alterando valor que de campo o qual seu método get foi anotado como NaoAuditavel
		 * não devendo ser considerado na auditoria.
		 * 
		 * Como os dois objetos possuem o mesmo preenchimento (excluindo-se os campos que não
		 * devem ser considerados na auditoria) não devem ser apresentadas divergências
		 */ 
		pessoaTeste2.setAuditado(true);
		pessoaTeste2.setDigitacao(999);
		pessoaTeste2.setDataDigitacao(new Date(2009,11,5));

		try{
			Set<Divergencia> divs = AuditorFacade.realizarAuditoria(pessoaTeste1, pessoaTeste2);
			
			if ( divs.size() == 0 ){
				System.out.println("\nSem divergências");
			}else{
				System.out.println("\n");
				for (Divergencia divergencia : divs) {
					System.out.println(divergencia);
				}
			}
			
			
		} catch (ObjetosDeClassesDiferentesException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ListaDeMetodosVaziaException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	
	}
	
}
