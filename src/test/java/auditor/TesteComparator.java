package test.java.auditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import main.java.crfutil.auditor.AuditorFacade;
import main.java.crfutil.auditor.Divergencia;
import main.java.crfutil.auditor.comparator.AscIntNomeCampoComparator;

import test.java.auditor.objetosauditaveis.Form;

public class TesteComparator {
	
	
	public static void main (String [] args){
		
		Form form1 = new Form();
		Form form2 = new Form();
		
		form1.set_1a(1);
		form1.set_1b(1);
		form1.set_1c(1);
		form1.set_1(1);
		form1.set_2a(1);
		form1.set_11a(1);
		
		System.out.println("inicio do teste com comparator");
		
		try {

			Set<Divergencia> resultado = AuditorFacade.realizarAuditoria(form1,form2);
			
			List<Divergencia> divs = new ArrayList<Divergencia>(resultado);
			
			for (Divergencia divergencia : divs) {
				if ( divergencia.getNomeCampo().equals("_2a")) divergencia.setNomeCampo("2a");
				if ( divergencia.getNomeCampo().equals("_1c")) divergencia.setNomeCampo("1c");
				if ( divergencia.getNomeCampo().equals("_1a")) divergencia.setNomeCampo("1a");
				if ( divergencia.getNomeCampo().equals("_1")) divergencia.setNomeCampo("1");
				if ( divergencia.getNomeCampo().equals("_1b")) divergencia.setNomeCampo("1b");
				if ( divergencia.getNomeCampo().equals("_11a")) divergencia.setNomeCampo("11a");
			}

			System.out.println("antes ordenação");
			for (Divergencia divergencia : divs) {
				System.out.println(divergencia.getNomeCampo());
			}

			Collections.sort( divs , new AscIntNomeCampoComparator() );
			
			System.out.println("após ordenação");
			for (Divergencia divergencia : divs) {
				System.out.println(divergencia.getNomeCampo());
			}
			
			
		}catch (Exception e) {			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
