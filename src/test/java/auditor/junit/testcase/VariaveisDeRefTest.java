package test.java.auditor.junit.testcase;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;
import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.component.date.IntegerRealDate;

import junit.framework.TestCase;
import test.java.auditor.objetosauditaveis.Cliente;
import test.java.auditor.objetosauditaveis.PessoaComAnnotation;

public class VariaveisDeRefTest extends TestCase {
	
	public void testIntegerDate()  {
		
		IntegerDate i = new IntegerDate();
		IntegerDate i2 = new IntegerDate();
		
		IntegerRealDate id = new IntegerRealDate();
		IntegerRealDate id2 = new IntegerRealDate();
				
		/*String t = String.valueOf(i);
		String t2 = String.valueOf(i2);
		try{
			t.equals(t2);
		}catch (Exception e) {	
			System.out.println("Erro");
		}*/
		
		
		PessoaComAnnotation p = new PessoaComAnnotation();
		p.setIdade(10);
		p.setIntegerDate(i);
		p.setIntegerRealDate(id);
		
		
		
		PessoaComAnnotation p2 = new PessoaComAnnotation();
		p2.setIdade(10);
		p2.setIntegerDate(i2);
		p2.setIntegerRealDate(id2);
		
		System.out.println(i);
		System.out.println(i2);
		
		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					p, p2);

			//assertEquals(4, resultado.size());
			
			if (resultado.size() == 0) {

				System.out.println("SEM DIVERGÊNCIAS!");
			} else {

				System.out.println("COM DIVERGÊNCIAS!");

				for (Divergencia string : resultado) {

					System.out.println(string);

				}

			}

		} catch (ObjetosDeClassesDiferentesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ListaDeMetodosVaziaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {			
			fail(e.getMessage());
		}
		
			
		
	}
	
	public void testRealizarAuditoriaObjDif() {

		Cliente conjuge = new Cliente(2, "Marcela", 10.1, true, null);
		Cliente cliente = new Cliente(1, "Marcelo", 10.2, false, conjuge);

		Cliente conjuge2 = new Cliente(2, "Ana", 50.5, true, null);
		Cliente cliente2 = new Cliente(1, "Claudio", 10.2, true, conjuge2);

		System.out.println("Primeiro objeto ====== \nCliente: "
				+ cliente.getNome() + " - Conjuge: "
				+ cliente.getConjuge().getNome());

		System.out.println("Segundo objeto ====== \nCliente: "
				+ cliente2.getNome() + " - Conjuge: "
				+ cliente2.getConjuge().getNome());

		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					cliente, cliente2);

			assertEquals(4, resultado.size());
			
			if (resultado.size() == 0) {

				System.out.println("SEM DIVERGÊNCIAS!");
			} else {

				System.out.println("COM DIVERGÊNCIAS!");

				for (Divergencia string : resultado) {

					System.out.println(string);

				}

			}

		} catch (ObjetosDeClassesDiferentesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ListaDeMetodosVaziaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {			
			fail(e.getMessage());
		}

	}

	public void testRealizarAuditoriaConjugesDif() {

		Cliente conjugeTeste2 = new Cliente(2, "Marcela", 10.1, true, null);
		Cliente clienteTeste2 = new Cliente(1, "Marcelo", 10.2, true,
				conjugeTeste2);

		Cliente conjuge2Teste2 = new Cliente(2, "Ana", 10.1, true, null);
		Cliente cliente2Teste2 = new Cliente(1, "Marcelo", 10.2, true,
				conjuge2Teste2);

		System.out.println("Primeiro objeto ====== \nCliente: "
				+ clienteTeste2.getNome() + " - Conjuge: "
				+ clienteTeste2.getConjuge().getNome());

		System.out.println("Segundo objeto ====== \nCliente: "
				+ cliente2Teste2.getNome() + " - Conjuge: "
				+ cliente2Teste2.getConjuge().getNome());

		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					clienteTeste2, cliente2Teste2);

			assertEquals(1, resultado.size());
			
			if (resultado.size() == 0) {

				System.out.println("SEM DIVERGÊNCIAS!");
			} else {

				System.out.println("COM DIVERGÊNCIAS!");

				for (Divergencia string : resultado) {

					System.out.println(string);

				}

			}

		} catch (ObjetosDeClassesDiferentesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ListaDeMetodosVaziaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {			
			fail(e.getMessage());
		}

	}
}
