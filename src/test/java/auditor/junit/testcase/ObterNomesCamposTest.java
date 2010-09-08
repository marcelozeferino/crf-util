package test.java.auditor.junit.testcase;

import java.util.Set;

import junit.framework.TestCase;

import main.java.crfutil.auditor.Util;

import org.junit.Test;

import test.java.auditor.objetosauditaveis.Cliente;
import test.java.auditor.objetosauditaveis.PessoaComAnnotation;

public class ObterNomesCamposTest extends TestCase {

	@Test
	public void testObterNomesDeCampo() {
		
		int qtdeCampos = 5;
		
		Set<String> campos = Util.obterNomesDosCampos(Cliente.class);
		
		for (String string : campos) {
			System.out.println(string);
		}
		
		assertEquals(qtdeCampos, campos.size());
		
		
	}
	
	@Test
	public void testObterNomesDeCampo2() {
		
		int qtdeCampos = 6;
		
		Set<String> campos = Util.obterNomesDosCampos(PessoaComAnnotation.class);
		
		for (String string : campos) {
			System.out.println(string);
		}
		
		assertEquals(qtdeCampos, campos.size());
		
	}


}
