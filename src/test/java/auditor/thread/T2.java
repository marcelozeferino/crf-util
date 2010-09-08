package test.java.auditor.thread;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import test.java.auditor.objetosauditaveis.Cliente;

public class T2 extends Thread {

	@Override
	public void run() {

		Cliente conjuge = new Cliente(2, "Marcela", 10.1, true, null);
		Cliente cliente = new Cliente(1, "Marcelo", 99.9, false, conjuge);

		Cliente conjuge2 = new Cliente(2, "Maria", 59.5, true, null);
		Cliente cliente2 = new Cliente(1, "José", 22.2, true, conjuge2);

		System.out.println("T2 - Primeiro objeto ====== \nT2 - Cliente: "
				+ cliente.getNome() + " - Conjuge: "
				+ cliente.getConjuge().getNome());

		System.out.println("T2 - Segundo objeto ====== \nT2 - Cliente: "
				+ cliente2.getNome() + " - Conjuge: "
				+ cliente2.getConjuge().getNome());

		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					cliente, cliente2);

			if (resultado.size() == 0) {

				System.out.println("T2 - SEM DIVERGÊNCIAS!");
			} else {

				System.out.println("T2 - COM DIVERGÊNCIAS!");

				for (Divergencia string : resultado) {

					System.out.println("T2 - " + string);

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
