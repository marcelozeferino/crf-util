package main.java.crfutil.validator;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.component.date.IntegerRealDate;



/**
 * Classe utilit�ria para valida��o de datas (representadas 
 * por objetos do tipo IntegerDate e IntegerRealDate) e hora.
 * 
 * 
 * @author Marcelo Zeferino
 *
 */
public class DateTimeValidator {

	

	
	/**
	 * M�todo utilizado para fazer a valida��o dos campos que cont�m valores de
	 * hor�rios
	 * 
	 * @param hora
	 *            String contendo a hora que ser� verificada. Combina��es com 99
	 *            (para hora e/ou minuto) s�o aceitas como v�lidas
	 * @return retorna 'true' se o hor�rio digitado � valido e 'false' se o
	 *         hor�rio for inv�lido
	 */
	public static boolean isValidTime(String hora) {

		boolean resultado = false;
		
		SimpleDateFormat formatHora  = new SimpleDateFormat( "HH:mm" );

		//setLenient = false para que a hora seja limitada em 24h
		formatHora.setLenient( false ); 

		//Verifica��o para identifica��o de caracter coringa 99 e valida��o
		if ( hora.contains("99")){
			
			String h = hora.substring(0, 2);
			String m = hora.substring(3,5);
			
			if (h.equals("99") && m.equals("99") ){
				
				resultado = true;
				
			}else if (h.equals("99")){
				
				try{

					formatHora.parse( "01:" + m );
					resultado = true;

				}catch( ParseException e ){
								
					resultado = false; 

				}
				
			}else{
			//Caso n�o tenha sido utilizado o caracter coringa a valida��o ocorre
			//normalmente
				try{

					formatHora.parse( h + ":01" );
					resultado = true;

				}catch( ParseException e ){
								
					resultado = false; 

				}
				
			}
			
		}else{
		
			
			try{
	
				formatHora.parse( hora );
				resultado = true;
	
			}catch( ParseException e ){
							
				resultado = false; 
	
			}

		}
		return resultado;

	}
	
	
	/**
	 * M�todo static para valida��o de objetos do tipo RealDate
	 * @param date IntegerDate que representa o objeto que ter� seus dados testasdos
	 * @return boolean indicando true se a data � valida e false caso contr�rio
	 */
	public static boolean isValidRealDate(IntegerDate date){
		
		boolean retorno = true;
		
		if ( date == null || date.getStrDate() == null){

			retorno = false;

		} else {

			try {
				new IntegerRealDate().setStrDate(date.getStrDate());
			} catch (IllegalArgumentException e) {
				retorno = false;
			}

		}		
		
		return retorno;
		
	}
	
	
	/**
	 * M�todo static para valida��o de objetos do tipo IntegerDate (que permitem
	 * combina��es com 99 e 88 para as partes da data.
	 * 
	 * Os valores 99 e 88 s�o v�lidos, por�m datas inv�lidas n�o s�o permitidas.
	 * 
	 * @param date IntegerDate representado a data que ser verificada
	 * @return boolean indicando true se a data � valida ou false caso contr�rio
	 */
	public static boolean isValidDate(IntegerDate date){
		
		boolean retorno = true;
		
		if ( date == null || date.getStrDate() == null){

			return false;

		} else {

			try {
				
				new IntegerDate().setStrDate(date.getStrDate());
				
				int dia = date.getDia();
				if (dia == 99 || dia == 88){
					dia = 1;
				}
				
				int mes = date.getMes();
				if (mes == 99 || mes == 88){
					mes = 1;
				}
				
				//Utilizando ano 2008 por este ser bissexto e n�o causar problemas
				int ano = date.getAno();
				if (ano == 9999 || ano == 8888){
					ano = 2008;
				}
				
				//Valida��o para verificar se a data � uma data v�lida
				try{
					
					SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
					sd.setLenient(false);
					sd.parse(new DecimalFormat("00").format(dia) 
							 + new DecimalFormat("00").format(mes) + ano);
					
				}catch(Exception e){
						
					return false;
					
				}
				
			} catch (IllegalArgumentException e) {
				return false;
			}

		}		
		
		return retorno;
		
	}
	
	/**
	 * M�todo para valida��o de ordem cronol�gica das datas (IntegerDate)
	 * informadas no argumento.
	 * 
	 * Caso as datas informadas n�o estejam em ordem, o retorno do
	 * m�todo ser� false.
	 * 
	 * @param IntegerDate[] Array (var-args) de IntegerDate
	 * representando as datas que ser�o verificadas na ordem 
	 * que ser�o validadas.
	 */
	public static boolean isValidOrdemCronologica(IntegerDate... datas){		
			
		Boolean result = true;
		
		List<IntegerDate> listDatas = new ArrayList<IntegerDate>();

		//Configurando para que o Array de datas n�o contenha objetos null
		for (IntegerDate integerDate : datas) {
			
			if (integerDate != null && integerDate.getStrDate() != null
					&& !integerDate.getStrDate().isEmpty()){
				
				listDatas.add(integerDate);
				
			}
			
		}

		datas = listDatas.toArray(new IntegerDate[listDatas.size()]);		
		
		if (datas.length >= 2){
			
			for(int i = 0; i < datas.length; i++){
							
				IntegerDate i1 = datas[i];
				IntegerDate i2 = null;
				
				if ( i+1 < datas.length ){
					
					i2 = datas[i+1];
					
				}else{
					break;
				}
				
				try{
					i1.diasEntre(i2);
				}catch (Exception e) {
					result = false;
					break;
				}
								
			}
			
		}
		
		return result;
		
	}
	
	
	
}
