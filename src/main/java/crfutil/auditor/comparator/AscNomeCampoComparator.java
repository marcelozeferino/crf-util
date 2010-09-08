package main.java.crfutil.auditor.comparator;

import java.util.Comparator;

import main.java.crfutil.auditor.Divergencia;


/**
 * Classe Comparator para ordenação de objetos Divergencia 
 * pelo campo NomeCampo (String)
 * 
 * @author Marcelo Zeferino
 *
 */
public class AscNomeCampoComparator implements Comparator<Divergencia> {


	@Override
	public int compare(Divergencia div1, Divergencia div2) {
		
		return div1.getNomeCampo().compareToIgnoreCase(div2.getNomeCampo());

	}  
	
}
