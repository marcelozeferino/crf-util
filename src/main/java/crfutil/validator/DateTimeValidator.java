package main.java.crfutil.validator;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.component.date.IntegerRealDate;



/**
 * Classe utilitária para validação de datas (representadas 
 * por objetos do tipo IntegerDate e IntegerRealDate) e hora.
 * 
 * 
 * @author Marcelo Zeferino
 *
 */
public class DateTimeValidator {

	

	
	/**
	 * Método utilizado para fazer a validação dos campos que contém valores de
	 * horários
	 * 
	 * @param hora
	 *            String contendo a hora que será verificada. Combinações com 99
	 *            (para hora e/ou minuto) são aceitas como válidas
	 * @return retorna 'true' se o horário digitado é valido e 'false' se o
	 *         horário for inválido
	 */
	public static boolean isValidTime(String hora) {

		boolean resultado = false;
		
		SimpleDateFormat formatHora  = new SimpleDateFormat( "HH:mm" );

		//setLenient = false para que a hora seja limitada em 24h
		formatHora.setLenient( false ); 

		//Verificação para identificação de caracter coringa 99 e validação
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
			//Caso não tenha sido utilizado o caracter coringa a validação ocorre
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
	 * Método static para validação de objetos do tipo RealDate
	 * @param date IntegerDate que representa o objeto que terá seus dados testasdos
	 * @return boolean indicando true se a data é valida e false caso contrário
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
	 * Método static para validação de objetos do tipo IntegerDate (que permitem
	 * combinações com 99 e 88 para as partes da data.
	 * 
	 * Os valores 99 e 88 são válidos, porém datas inválidas não são permitidas.
	 * 
	 * @param date IntegerDate representado a data que ser verificada
	 * @return boolean indicando true se a data é valida ou false caso contrário
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
				
				//Utilizando ano 2008 por este ser bissexto e não causar problemas
				int ano = date.getAno();
				if (ano == 9999 || ano == 8888){
					ano = 2008;
				}
				
				//Validação para verificar se a data é uma data válida
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
	 * Método para validação de ordem cronológica das datas (IntegerDate)
	 * informadas no argumento.
	 * 
	 * Caso as datas informadas não estejam em ordem, o retorno do
	 * método será false.
	 * 
	 * @param IntegerDate[] Array (var-args) de IntegerDate
	 * representando as datas que serão verificadas na ordem 
	 * que serão validadas.
	 */
	public static boolean isValidOrdemCronologica(IntegerDate... datas){		
			
		Boolean result = true;
		
		List<IntegerDate> listDatas = new ArrayList<IntegerDate>();

		//Configurando para que o Array de datas não contenha objetos null
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
