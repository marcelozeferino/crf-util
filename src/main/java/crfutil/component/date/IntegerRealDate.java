package main.java.crfutil.component.date;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class IntegerRealDate extends IntegerDate implements Serializable{

	public IntegerRealDate() {

	}



	
	@Override
	public Integer getIntDate() {		
		return super.getIntDate();
	}



	public IntegerRealDate(int dia, int mes, int ano) {
		super(dia, mes, ano);		
	}

	@Override
	void validarData(Integer intDate) throws IllegalArgumentException {
		
		super.validarData(intDate);
		
		validarDataReal( String.valueOf(intDate) );
		
	}
	
	
	
	
	private void validarDataReal(String sDate){
		
		//Validação para verificar se a data é uma data válida
		String dia = sDate.substring(6, 8);
		String mes = sDate.substring(4, 6);
		String ano = sDate.substring(0, 4);
		
		try{
			
			SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
			sd.setLenient(false);
			sd.parse(dia + mes + ano);
			
		}catch(Exception e){
				
			throw new IllegalArgumentException("A data informada é inválida!");
			
		}
		
		
		
	}

}
