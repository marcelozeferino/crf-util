package test.java.auditor;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import test.java.auditor.objetosauditaveis.Cliente;

public class VariaveisDeReferenciaTest {

	public static void main(String[] args) {

		/*
		 * Testando objetos totalmente diferentes
		 * 
		 */
		System.out.println("=========== Início do Teste 1 ============");
		
		
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

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(cliente,
					cliente2);

			if (resultado.size() == 0) {

				System.out.println("SEM DIVERGÊNCIAS!");
			} else {

				System.out.println("COM DIVERGÊNCIAS!");

				for (Divergencia string : resultado) {

					System.out.println(string);

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

		System.out.println("==================== Fim do teste 1 ========================");

		System.out.println("=========== Início do Teste 2 ============");
		/*
		 * Testando objetos com apenas conjuges diferentes
		 * 
		 */
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

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(clienteTeste2,
					cliente2Teste2);

			if (resultado.size() == 0) {

				System.out.println("SEM DIVERGÊNCIAS!");
			} else {

				System.out.println("COM DIVERGÊNCIAS!");

				for (Divergencia string : resultado) {

					System.out.println(string);

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
