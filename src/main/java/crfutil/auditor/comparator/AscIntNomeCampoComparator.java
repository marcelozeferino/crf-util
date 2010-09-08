package main.java.crfutil.auditor.comparator;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.crfutil.auditor.Divergencia;


/**
 * <p>Classe Comparator para ordena��o de objetos Divergencia 
 * pelo campo NomeCampo (transformando-o em Integer).
 * 
 * <p>Quando nome do campo n�o puder ser convertido para Integer,
 * o compara��o ser� realizada utilizando String, sendo
 * equivalente ao comparator AscNomeCampoComparator.
 * 
 * <p>Para valores como 11a ou 12b, ser� utilizada ordena��o
 * pelo n�mero inicial e (caso os n�meros sejam iguais) pelas letras ap�s o n�mero. 
 * 
 * <p>Exemplo: para nomes de campos 1, 10, 1a, 1b, 1c, 2 a ordena��o retornada
 * ser� 1, 1a, 1b, 1c, 2 e 10.
 *
 * 
 * @author Marcelo Zeferino
 *
 */
public class AscIntNomeCampoComparator implements Comparator<Divergencia> {

	@Override
	public int compare(Divergencia div1, Divergencia div2) {
		
		/*
		 * Indica a os locais onde ser�o capturados
		 * valores n�mericos (2 -> os 3 primeiros d�gitos)
		 */
		int maxIndice = 2;
		
		//Nomes dos campos
		String campo1 = div1.getNomeCampo();
		String campo2 = div2.getNomeCampo();
		
		//String que contem o n�mero da quest�o (parte n�merica do nome do campo)
		String strNumCampo1 = "";
		String strNumCampo2 = "";
		
		//String que cont�m a letra (quando houver) ap�s o n�mero da quest�o	
		String strLetraCampo1 = "";
		String strLetraCampo2 = "";
		
		//Boolean que verifica se foi encontrado algum d�gito no nome do campo
		boolean nomeCampo1HasDigit = false;
		boolean nomeCampo2HasDigit = false;	
		
		//Procurando por d�gito
		Pattern p = Pattern.compile("\\d");				

		Matcher m = p.matcher(campo1);
		while (m.find()){
			
			nomeCampo1HasDigit = true;
			
			if ( m.start() <= maxIndice ){
				strNumCampo1 = strNumCampo1 + campo1.charAt(m.start());
			}
			
		}

		m = p.matcher(campo2);			
		while ( m.find() ){			
			
			nomeCampo2HasDigit = true;
			
			if ( m.start() <= maxIndice ){
				strNumCampo2 = strNumCampo2 + campo2.charAt(m.start());
			}
			
		}		
		
		/*
		 * Caso exista d�gito no nome do campo, � capturado apenas os 
		 * caracteres ap�s o d�gito (geralmente letras, como "a" em "1a") 
		 */
		if ( nomeCampo1HasDigit ){
			strLetraCampo1 = campo1.substring(strNumCampo1.length());
		}	
		
		if ( nomeCampo2HasDigit ){
			strLetraCampo2 = campo2.substring(strNumCampo2.length());
		}
		
		
		//Caso algum dos nomes do campo seja nulo, realiza a compara��o de Strings
		if ( strNumCampo1.isEmpty() || strNumCampo2.isEmpty() ){
			
			return campo1.compareToIgnoreCase(campo2);
			
		}else{
			
			//Obtendo resultado da compara��o entre os campos 1 e 2 
			int result = Integer.valueOf(strNumCampo1).compareTo( Integer.valueOf(strNumCampo2) ); 
			
			/*
			 * Se os dois valores forem iguais, realiza a compara��o utilizando
			 * os caracteres ap�s o digito, capturados anteriormente
			 */
			if ( result == 0 ){
				
				if ( !strLetraCampo1.isEmpty() || !strLetraCampo2.isEmpty() ){
					result = strLetraCampo1.compareToIgnoreCase(strLetraCampo2); 
				}
					
			}
			
			return result; 		
	
		}
	
	
	}  
	
}
