package test.java.auditor;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.comparator.AscIntNomeCampoComparator;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;

import test.java.auditor.objetosauditaveis.Pessoa;

public class AuditorSimpleTest {
	
	/**
	 * @param args
	 */
	public static void main (String [] args){
		
		Pessoa p1 = new Pessoa(1,"Marcelo", 10);
		Pessoa p2 = new Pessoa(2,"Marcela", 11);
		
		try {
			
			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(
					p1, p2);
			List<Divergencia> r = new ArrayList<Divergencia>(resultado);
			
			if (r.size() == 0) {
				
				System.out.println("SEM DIVERGÊNCIAS!");
			} else {

				
				System.out.println("COM DIVERGÊNCIAS!");
				Collections.sort(r, new AscIntNomeCampoComparator());
				for (Divergencia string : r) {

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
