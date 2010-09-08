package main.java.crfutil.component.date;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * 
 * Classe utilitária que pode ser utilizada para facilitar o trabalho com campos
 * data que possam receber valores como 88/88/8888 e 99/99/9999.
 * 
 * Possui uma representação da data no formato dd/mm/yyyy e outro no formato
 * yyyymmdd (strDate e intDate respectivamente)
 * 
 * @author Marcelo Zeferino
 * 
 */
@SuppressWarnings("serial")
@Embeddable
public class IntegerDate implements Serializable {

	/**
	 * Atributo do tipo Integer que armazena a data no formato yyyymmdd. Este
	 * atributo pode ser persistido no banco de dados.
	 */
	private Integer intDate;

	/**
	 * Atributo que representa a data contida no atributo intDate como String,
	 * no formato dd/mm/yyyy. Este atributo não pode ser persistido no banco
	 * (contém a anotação {@literal @Transient}
	 */
	@Transient
	private String strDate;

	public IntegerDate() {
		super();
	}

	/**
	 * Construtor completo para a classe. Os atributos intDate e strDate são
	 * atualizados através deste construtor.
	 * 
	 * @param dia
	 * @param mes
	 * @param ano
	 */
	public IntegerDate(int dia, int mes, int ano) {
		super();

		StringBuilder strData = new StringBuilder();

		strData.append(new DecimalFormat("00").format(dia) + "/");
		strData.append(new DecimalFormat("00").format(mes) + "/");
		strData.append(ano);

		setStrDate(strData.toString());

	}

	/**
	 * Retorna a parte da data que representa o dia.
	 * 
	 * @return int (Sem 0 a esquerda)
	 */
	public int getDia() {

		int resultado = 99;

		if (this.strDate == null && this.intDate != null) {

			String sDate = String.valueOf(this.intDate);

			// Checagem do tamanho do campo
			if (sDate == null || sDate.isEmpty() || sDate.length() != 8) {

				throw new IllegalArgumentException(
						"O tamanho do campo Data deve ser igual a 8 (aaaammdd).");
			}

			// Checagem do dia
			resultado = Integer.parseInt(sDate.substring(6, 8));

			/*
			 * // Checagem do mes resultado =
			 * Integer.parseInt(sDate.substring(4, 6));
			 *  // Checagem do ano resultado =
			 * Integer.parseInt(sDate.substring(0, 4));
			 */

		} else if (this.strDate != null) {

			resultado = Integer.parseInt(getIntDate().toString()
					.substring(6, 8));

		}

		return resultado;

	}

	/**
	 * Retorna a parte da data que representa o mês.
	 * 
	 * @return int (Sem 0 a esquerda)
	 */
	public int getMes() {

		int resultado = 99;

		if (this.strDate == null && this.intDate != null) {

			String sDate = String.valueOf(this.intDate);

			// Checagem do tamanho do campo
			if (sDate == null || sDate.isEmpty() || sDate.length() != 8) {

				throw new IllegalArgumentException(
						"O tamanho do campo Data deve ser igual a 8 (aaaammdd).");
			}

			// Checagem do mes
			resultado = Integer.parseInt(sDate.substring(4, 6));

			/*
			 * // Checagem do ano resultado =
			 * Integer.parseInt(sDate.substring(0, 4));
			 */

		} else if (this.strDate != null) {

			resultado = Integer.parseInt(getIntDate().toString()
					.substring(4, 6));

		}

		return resultado;

	}

	/**
	 * Retorna a parte da data que representa o ano.
	 * 
	 * @return int com 4 caracteres
	 */
	public int getAno() {

		int resultado = 9999;

		if (this.strDate == null && this.intDate != null) {

			String sDate = String.valueOf(this.intDate);

			// Checagem do tamanho do campo
			if (sDate == null || sDate.isEmpty() || sDate.length() != 8) {

				throw new IllegalArgumentException(
						"O tamanho do campo Data deve ser igual a 8 (aaaammdd).");
			}

			resultado = Integer.parseInt(sDate.substring(0, 4));

		} else if (this.strDate != null) {

			resultado = Integer.parseInt(getIntDate().toString()
					.substring(0, 4));

		}

		return resultado;

	}

	/**
	 * Retorna a data no formato yyyymmdd.
	 * 
	 * @return Integer
	 */
	public Integer getIntDate() {

		/*
		 * if ( !(this.strDate == null || this.strDate.isEmpty()) ||
		 * this.intDate != null ){
		 */
		return intDate;
		/*
		 * }else{ return 99999999; }
		 */

	}

	/**
	 * Retorna a data no formato dd/mm/yyyy, ideal para apresentação ao usuário.
	 * 
	 * @return String
	 */
	public String getStrDate() {

		/*
		 * if ( this.strDate == null ){ return null; }
		 */

		if (this.strDate == null || this.strDate.isEmpty()) {

			if (this.getIntDate() != null) {

				setStrDate(new IntegerDate(getDia(), getMes(), getAno())
						.getStrDate());
			} else {
				return null;
			}

		}

		return this.strDate;
	}

	/**
	 * Setter para o atributo intDate. Este método atualiza também o atributo
	 * strDate com a representação da data no formato dd/mm/yyyy.
	 * 
	 * @param intDate
	 *            Deve conter uma data no formato yyyymmdd (com tamanho igual a
	 *            8).
	 * @throws IllegalArgumentException
	 */
	public void setIntDate(Integer intDate) {

		try {
			validarData(intDate);
		} catch (IllegalArgumentException e) {
			throw e;
		}

		this.intDate = intDate;

		if (getStrDate() == null || getStrDate().isEmpty()) {

			setStrDate(new IntegerDate(getDia(), getMes(), getAno())
					.getStrDate());

		}

	}

	/**
	 * Setter para o atributo strDate. Este método atualiza também o atributo
	 * intDate com a representação da data no formato yyyymmdd.
	 * 
	 * @param strDate
	 *            Deve conter uma data no formato dd/mm/yyyy (incluindo o
	 *            caracter {@literal /} ).
	 */
	public void setStrDate(String strDate) {

		if ((strDate == null) || (strDate.isEmpty())) {
			this.intDate = null;
			this.strDate = null;
			return;
		}

		try {
			validarData(strDate);
		} catch (IllegalArgumentException e) {
			throw e;
		}

		this.strDate = strDate;

		String[] vetorData = strDate.split("/");

		NumberFormat nf = new DecimalFormat("00");

		String dia = nf.format(Integer.parseInt(vetorData[0]));
		String mes = nf.format(Integer.parseInt(vetorData[1]));
		String ano = vetorData[2];

		Integer iDate = Integer.valueOf(ano + mes + dia);

		if (getIntDate() == null || getIntDate() == 0
				|| !(iDate.equals(getIntDate()))) {

			setIntDate(Integer.valueOf(ano + mes + dia));

		}

	}

	
	void validarData(Integer intDate) throws IllegalArgumentException {

		String sDate = String.valueOf(intDate);

		// Checagem do tamanho do campo
		if (sDate == null || sDate.isEmpty() || sDate.length() != 8) {

			throw new IllegalArgumentException(
					"O tamanho do campo Data deve ser igual a 8 (aaaammdd).");
		}

		// Checagem do dia
		int dia = Integer.parseInt(sDate.substring(6, 8));
		validarDia(dia);

		// Checagem do mes
		int mes = Integer.parseInt(sDate.substring(4, 6));
		validarMes(mes);

		// Checagem do ano
		int ano = Integer.parseInt(sDate.substring(0, 4));
		validarAno(ano);

	}

	private void validarData(String sDate) throws IllegalArgumentException {

		if (sDate == null) {
			return;
		}

		if ((sDate != null) && (sDate.isEmpty() || sDate.length() != 10)) {

			throw new IllegalArgumentException(
					"O tamanho do campo Data deve ser igual a 10 (dd/mm/aaaa).");

		}

		String[] vetorData = sDate.split("/");

		if (vetorData.length == 0 || vetorData.length != 3) {

			throw new IllegalArgumentException(
					"O campo Data deve ser informado utilizando barras (dd/mm/aaaa).");
		}

		NumberFormat nf = new DecimalFormat("00");

		String dia = nf.format(Integer.parseInt(vetorData[0]));
		String mes = nf.format(Integer.parseInt(vetorData[1]));
		String ano = vetorData[2];

		Integer iDate = Integer.valueOf(ano + mes + dia);

		// Reutilização do método validador da data utilizando o valor como
		// Integer
		validarData(iDate);

	}

	private void validarDia(int dia) {

		if ((dia < 1 || dia > 31) && (dia != 99 && dia != 88)) {

			throw new IllegalArgumentException(
					"O Dia da Data deve estar entre 1 e 31 ou deve ser igual a 88 ou 99.");

		}

	}

	private void validarMes(int mes) {

		if ((mes < 1 || mes > 12) && (mes != 99 && mes != 88)) {

			throw new IllegalArgumentException(
					"O Mês da Data deve estar entre 1 e 12 ou deve ser igual a 88 ou 99.");

		}

	}

	private void validarAno(int ano) {

		//int anoAtual = 0;

		// Obtenação do ano atual
		Date date = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		//anoAtual = cal.get(GregorianCalendar.YEAR);

/*		if ((ano < 1500 || ano > anoAtual) && (ano != 9999 && ano != 8888)) {
			throw new IllegalArgumentException(
					"O Ano da data deve estar entre 1500 e o ano atual. Alternativamente, o ano pode ser igual a 8888 ou 9999");
		}*/
		
		if (ano < 1500) {
			throw new IllegalArgumentException(
					"O Ano da data deve ser menor ou igual a 1500.");
		}

	}

	/**
	 * Retorna a data no formato dd/mm/yyyy, ideal para apresentação ao usuário.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {

		return getStrDate();

	}

	/**
	 * Retorna um objeto do tipo Date representando a data contida no atributo
	 * intDate.
	 * 
	 * @return Date
	 */
	public Date asDate() {

		Date resultado = new Date();

		if (!(this.strDate == null || this.strDate.isEmpty())) {

			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

			sd.setLenient(false);

			StringBuilder strData = new StringBuilder();

			strData.append(new DecimalFormat("00").format(getDia()) + "/");
			strData.append(new DecimalFormat("00").format(getMes()) + "/");
			strData.append(getAno());

			Date data = null;
			try {
				data = sd.parse(strData.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			sd = null;
			strData = null;

			resultado = data;

		}

		return resultado;

	}

	/**
	 * Método utilitário para obtenção de nova instância de IntegerDate,
	 * preenchido com a data resultante da operação de adição do valor informado
	 * no primeiro argumento ao campo informado no segundo argumento.
	 * 
	 * <p>
	 * <b>ATENÇÃO: A instância de IntegerDate utilizada para a chamada ao método
	 * não será alterada, sendo imutável. Deve-se utilizar o retorno do método
	 * para as operações desejadas. </b>
	 * </p>
	 * 
	 * @param valor
	 *            int representando o valor que será adicionado (ou
	 *            decrementado, caso seja usado o sinal "-") a data, de acordo
	 *            com o campo escolhido.
	 * 
	 * @param campo
	 *            Enum de campos que disponível na classe Calendar, devendo ser
	 *            Calendar.DAY_OF_MONTH, Calendar.MONTH ou Calendar.YEAR
	 * 
	 * @return IntegerDate.campoData Item de enum representando o campo da data
	 *         que será utilizado na operação.
	 * 
	 */
	public IntegerDate obterDataAlterada(int valor, CampoData campo) {

		if (this.getStrDate() == null || this.getStrDate().isEmpty()
				|| this.getIntDate().intValue() == 88888888
				|| this.getIntDate().intValue() == 99999999) {

			return this;

		}

		IntegerDate retorno = new IntegerDate();

		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		try {

			Date dtAtual = df.parse(this.getStrDate());

			calendar.setTime(dtAtual);
			calendar.add(campo.obterCalendarField(), valor);

			Date dtAlterada = calendar.getTime();

			retorno.setStrDate(df.format(dtAlterada));

		} catch (ParseException e1) {

			throw new IllegalArgumentException(e1.getMessage());

		} catch (Exception e) {

			throw new IllegalArgumentException("Erro na alteração da data: "
					+ e.getMessage());

		}

		return retorno;

	}

	
	/**
	 * Método utilitário que retorna a quantidade de dias de diferença
	 * entre a data configurada no IntegerDate chamador e a data
	 * do IntegerDate passado por referência no argumento do método
	 * 
	 * @param dataFinal Data Final utilizada na comparação. Deve ser igual ou posterior
	 * à data do IntegerDate chamador do método.
	 * @return long Representa a quantidade dias entre as datas.
	 */
	public long diasEntre(IntegerDate dataFinal) {

		if (dataFinal.asDate().compareTo(this.asDate()) < 0) {
			throw new IllegalArgumentException("A data final deve ser maior "
					+ "ou igual a data inicial");
		}

		long milisecInicial = this.asDate().getTime();
		long milisecFinal = dataFinal.asDate().getTime();
		long dif = milisecFinal - milisecInicial;

		return (((dif / 1000) / 60) / 60) / 24;
		
	}
	
}
