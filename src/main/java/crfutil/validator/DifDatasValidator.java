package main.java.crfutil.validator;

import java.util.Date;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.validator.exception.DifDatasValidatorException;


/**
 * 
 * Classe utilit�ria para valida��o de tempo entre datas, utilizando
 * recursos de Fluent Interface para cria��o de uma inst�ncia que cont�m
 * os atributos necess�rios para valida��o.
 * 
 * @author Marcelo Zeferino
 *
 */
public class DifDatasValidator {

	/**
	 * Enum contendo os tipos de diferen�a (dia, mes e ano)
	 * que podem ser avaliados pelo validador
	 * 
	 * @author Marcelo Zeferino
	 *
	 */
	public static enum TipoDiferencaEnum {

		DIAS, MESES, ANOS;

	};

	/**
	 * Enum contendo os operadores suportados para 
	 * a compara��o do valor passado no argumento e a diferen�a entre 
	 * as datas
	 * 
	 * @author Marcelo Zeferino
	 *
	 */
	public static enum Operador {

		MENOR, MENOR_IGUAL, MAIOR, MAIOR_IGUAL;

	};

	
	private TipoDiferencaEnum tipoDiferenca;
	private Operador operador;
	private long diferenca;
	private IntegerDate dataInicial;
	private IntegerDate dataFinal;
	
	/**
	 * Vari�vel que receber� o valor (long) que representa
	 * a diferen�a entre as duas datas
	 * 
	 */
	private long dif = 0;

	/**
	 * M�todo que define o tipo de campo que ser� utilizado 
	 * na opera��o de diferen�a (DIA, MES e ANO)
	 * 
	 * @param tipoDif
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comTipoDiferenca(TipoDiferencaEnum tipoDif) {
		this.tipoDiferenca = tipoDif;
		return this;
	}

	/**
	 * M�todo para defini��o do operador que ser� utilizado na 
	 * opera��o de diferen�a (MENOR, MENOR ou IGUAL, MAIOR, MAIOR ou IGUAL).
	 * 
	 * @param operador
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comOperador(Operador operador) {
		this.operador = operador;
		return this;
	}

	/**
	 * M�todo que define o valor que ser� testado como diferen�a entre 
	 * as datas.
	 * 
	 * @param diferenca 
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comDiferenca(long diferenca) {

		this.diferenca = diferenca;
		return this;

	}

	/**
	 * M�todo que define a data inicial que ser� utilizada no
	 * processo de diferencia��o. 
	 * 
	 * A data informada deve ser anterior ou igual � data 
	 * informada no argumento dataFinal.
	 * 
	 * @param dataInicial
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comDataInicial(IntegerDate dataInicial) {
		this.dataInicial = dataInicial;
		return this;
	}

	/**
	 * M�todo que define a data final que ser� utilizada no
	 * processo de diferencia��o.
	 * 
	 * A data informada deve ser posterior ou igual � data
	 * informada no argumento dataInicial.
	 * 
	 * @param dataFinal
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comDataFinal(IntegerDate dataFinal) {
		this.dataFinal = dataFinal;
		return this;
	}

	
	/**
	 * M�todo que realiza a compara��o entre as duas datas e retorna um boolean
	 * caso a diferen�a entre elas esteja, de acordo com o operador utilizado, 
	 * compat�vel com o valor informado no argumento diferenca.
	 * 
	 * Este m�todo deve ser chamado apenas ap�s chamadas a todos os m�todos
	 * que definem as propriedades da classe:
	 * {@link comTipoDiferenca, comOperador, comDiferenca, comDataInicial, comDataFinal}.
	 * 
	 *
	 * <br><b>Exemplo:</b>
	 * <br>
	 * 	
	 * <p>	new DifDatasValidator()<br>
				.comOperador(Operador.MENOR)<br>
				.comTipoDiferenca(TipoDiferencaEnum.MESES)<br>
				.comDiferenca(3)<br>
				.comDataInicial(new IntegerDate(6,10,2009))<br>
				.comDataFinal(new IntegerDate(8,8,2009))<br>
				.diferencaValidaEntreDatas();<br>
	 *  
	 * </p>
	 * <br>
	 * Que teria retorno = true
	 *   
	 * @return boolean 
	 */
	@SuppressWarnings({ "deprecation" })
	public boolean diferencaValidaEntreDatas() {

		/*
		 * Valida��o das datas informadas para a compara��o
		 */
		if (!DateTimeValidator.isValidDate(this.dataInicial)
				|| !DateTimeValidator.isValidDate(this.dataFinal)) {

			throw new DifDatasValidatorException();

		}

		boolean resultado = true;
				
		// Objetos data para utiliza��o em compara��es
		Date d1 = null;
		Date d2 = null;
		
		//Obten��o de objetos Date a partir de IntegerDate
		d1 = dataInicial.asDate();
		d2 = dataFinal.asDate();


		/*
		 * Realizando a obten��o das diferen�as de acordo com
		 * o tipo de diferen�a informado (dia, mes ou ano)
		 */
		switch (tipoDiferenca) {
		case DIAS:{
			
			dif = (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
			
			break;
			
		}
		case MESES:{

			dif = (d2.getMonth() - d1.getMonth() + ((d2.getYear() - d1.getYear()) * 12));
			
			break;
			
		}
		case ANOS:

			dif = (d2.getMonth() - d1.getMonth() + ((d2.getYear() - d1.getYear()) * 12)) / 12;
			
			break;

		}

		resultado = validarDif();
		
		return resultado;

	}
	
	
	/**
	 * M�todo privado para valida��o da diferen�a entre as datas, considerando
	 * o operador informado
	 * 
	 * @return boolean
	 */
	private boolean validarDif(){
		
		boolean resultado = true;
		
/*		//Eliminando o sinal, caso exista
		if (dif < 0){
			dif *= -1;
		}*/
		
		switch (operador) {
		case MENOR:
		{
			if (!(dif < diferenca && dif >= 0)) {

				resultado = false;
			}
			
			break;

		}	
		
		case MENOR_IGUAL:
		{
			if (!(dif <= diferenca && dif >= 0)) {

				resultado = false;
			}
			
			break;

		}	
		
		case MAIOR:
		{
			if (!(dif > diferenca && dif >= 0)) {

				resultado = false;
			}
			
			break;

		}
		
		case MAIOR_IGUAL:
		{
			if (!(dif >= diferenca && dif >= 0)) {

				resultado = false;
			}
			
			break;

		}
		
		}
		
		return resultado;
		
	}
	
}
