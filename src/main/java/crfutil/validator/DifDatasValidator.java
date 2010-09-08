package main.java.crfutil.validator;

import java.util.Date;

import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.validator.exception.DifDatasValidatorException;


/**
 * 
 * Classe utilitária para validação de tempo entre datas, utilizando
 * recursos de Fluent Interface para criação de uma instância que contém
 * os atributos necessários para validação.
 * 
 * @author Marcelo Zeferino
 *
 */
public class DifDatasValidator {

	/**
	 * Enum contendo os tipos de diferença (dia, mes e ano)
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
	 * a comparação do valor passado no argumento e a diferença entre 
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
	 * Variável que receberá o valor (long) que representa
	 * a diferença entre as duas datas
	 * 
	 */
	private long dif = 0;

	/**
	 * Método que define o tipo de campo que será utilizado 
	 * na operação de diferença (DIA, MES e ANO)
	 * 
	 * @param tipoDif
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comTipoDiferenca(TipoDiferencaEnum tipoDif) {
		this.tipoDiferenca = tipoDif;
		return this;
	}

	/**
	 * Método para definição do operador que será utilizado na 
	 * operação de diferença (MENOR, MENOR ou IGUAL, MAIOR, MAIOR ou IGUAL).
	 * 
	 * @param operador
	 * @return DifDatasValidator
	 */
	public DifDatasValidator comOperador(Operador operador) {
		this.operador = operador;
		return this;
	}

	/**
	 * Método que define o valor que será testado como diferença entre 
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
	 * Método que define a data inicial que será utilizada no
	 * processo de diferenciação. 
	 * 
	 * A data informada deve ser anterior ou igual à data 
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
	 * Método que define a data final que será utilizada no
	 * processo de diferenciação.
	 * 
	 * A data informada deve ser posterior ou igual à data
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
	 * Método que realiza a comparação entre as duas datas e retorna um boolean
	 * caso a diferença entre elas esteja, de acordo com o operador utilizado, 
	 * compatível com o valor informado no argumento diferenca.
	 * 
	 * Este método deve ser chamado apenas após chamadas a todos os métodos
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
		 * Validação das datas informadas para a comparação
		 */
		if (!DateTimeValidator.isValidDate(this.dataInicial)
				|| !DateTimeValidator.isValidDate(this.dataFinal)) {

			throw new DifDatasValidatorException();

		}

		boolean resultado = true;
				
		// Objetos data para utilização em comparações
		Date d1 = null;
		Date d2 = null;
		
		//Obtenção de objetos Date a partir de IntegerDate
		d1 = dataInicial.asDate();
		d2 = dataFinal.asDate();


		/*
		 * Realizando a obtenção das diferenças de acordo com
		 * o tipo de diferença informado (dia, mes ou ano)
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
	 * Método privado para validação da diferença entre as datas, considerando
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
