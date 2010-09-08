package test.java.auditor.junit.testcase;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Set;

import junit.framework.TestCase;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import org.junit.Test;

import test.java.auditor.objetosauditaveis.PessoaComAnnotation;

public class AnnotationTest extends TestCase {

	@SuppressWarnings("deprecation")
	@Test
	public void testRealizarAuditoriaObjetoNull() {

		PessoaComAnnotation pessoaTeste1 = new PessoaComAnnotation(1,
				"Marcelo", 25);
		PessoaComAnnotation pessoaTeste2 = new PessoaComAnnotation(1,
				"Marcelo", 25);

		/*
		 * Alterando valor que de campo o qual seu método get foi anotado como
		 * NaoAuditavel não devendo ser considerado na auditoria.
		 * 
		 * Como os dois objetos possuem o mesmo preenchimento (excluindo-se os
		 * campos que não devem ser considerados na auditoria) não devem ser
		 * apresentadas divergências
		 */
		pessoaTeste2.setAuditado(true);
		pessoaTeste2.setDigitacao(999);
		pessoaTeste2.setDataDigitacao(new Date(2009, 11, 5));

		try {
			Set<Divergencia> divs = AuditorFacade.realizarAuditoria(
					pessoaTeste1, pessoaTeste2);

			assertEquals(divs.size(), 0);

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
