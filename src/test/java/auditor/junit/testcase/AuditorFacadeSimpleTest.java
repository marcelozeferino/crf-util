package test.java.auditor.junit.testcase;



import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import junit.framework.TestCase;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import org.junit.Test;

import test.java.auditor.objetosauditaveis.Pessoa;

public class AuditorFacadeSimpleTest extends TestCase{

	@Test
	// Teste de auditoria automática com objetos contendo divergências
	public void testRealizarAuditoriaAutomaticaComDiv(){
				

		Pessoa pessoa1 = new Pessoa(1, "Pedro", 1);
		Pessoa pessoa2 = new Pessoa(2, "Marcelo", 25);

		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					pessoa1, pessoa2);

			boolean resultadoBoolean = false;

			if (resultado.size() == 0) {
				resultadoBoolean = true;
			} else {

				resultadoBoolean = false;

				for (Divergencia string : resultado) {

					System.out.println(string);

				}

			}

			assertFalse(resultadoBoolean);

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

	@Test
	// Teste de auditoria automática com objetos sem divergências
	public void testRealizarAuditoriaAutomaticaSemDiv()  {

		Pessoa pessoa1 = new Pessoa(1, "Pedro", 1);
		Pessoa pessoa2 = new Pessoa(1, "Pedro", 1);

		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					pessoa1, pessoa2);

			boolean resultadoBoolean = false;

			if (resultado.size() == 0) {
				resultadoBoolean = true;
			} else {

				resultadoBoolean = false;

				for (Divergencia string : resultado) {

					System.out.println(string);

				}

			}

			assertTrue(resultadoBoolean);

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
