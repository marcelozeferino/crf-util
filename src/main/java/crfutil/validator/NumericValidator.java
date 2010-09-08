package main.java.crfutil.validator;

/**
 * Classe utilit�ria para valida��o de valores num�ricos
 * 
 * @author Marcelo Zeferino
 * 
 */
public class NumericValidator {

	/**
	 * M�todo static utilizado para verificar se o valor est� dentro da faixa
	 * informada
	 * 
	 * @param begin
	 *            int Indicando o in�cio da faixa de valores v�lidos
	 * @param end
	 *            int indicando o fim da faixa de valores v�lidos
	 * @param number
	 *            int N�mero que ser� validado
	 * @return boolean indicando a validade ou n�o do valor informado.
	 */
	public static boolean isValidRange(int begin, int end, int number) {

		boolean retorno = true;

		if (number > end || number < begin) {
			retorno = false;
		}

		return retorno;
	}

	/**
	 * M�todo static utilizado para verificar se o valor est� dentro da faixa
	 * informada
	 * 
	 * @param begin
	 *            int Indicando o in�cio da faixa de valores v�lidos
	 * @param end
	 *            int indicando o fim da faixa de valores v�lidos
	 * @param number
	 *            String N�mero que ser� convertido para n�mero e validado
	 * @return boolean Indicando a validade ou n�o do valor informado.
	 *         <p>
	 *         <b>Obs: </b> Caso seja passado um valor null ou string vazia o
	 *         retorno do m�todo ser� sempre false e n�o ser�o lan�adas
	 *         exce��es.
	 */
	public static boolean isValidRange(int begin, int end, String number) {

		boolean retorno = true;

		int intNumber = 0;
		try {
			
			intNumber = Integer.parseInt(number);
			
		} catch (NumberFormatException e) {
			
			return false;
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException(
					"Argumento inv�lido no m�todo isValidRange(int, int, String)");
		}

		if (intNumber > end || intNumber < begin) {
			retorno = false;
		}

		return retorno;
	}

	/**
	 * M�todo static utilizado para verificar se o valor (Double) est� dentro da
	 * faixa informada
	 * 
	 * @param begin
	 *            double Indicando o in�cio da faixa de valores v�lidos
	 * @param end
	 *            double indicando o fim da faixa de valores v�lidos
	 * @param number
	 *            double N�mero que ser� validado
	 * @return boolean indicando a validade ou n�o do valor informado.
	 */
	public static boolean isValidRange(double begin, double end, double number) {

		boolean retorno = true;

		if (number > end || number < begin) {
			retorno = false;
		}

		return retorno;

	}

	/**
	 * M�todo static utilizado para verificar se o valor (Double) est� dentro da
	 * faixa informada.
	 * 
	 * <p>
	 * <b>OBS</b> Em todos os argumentos o separador "," ser� convertido (caso
	 * exista) para "." para que a convers�o para double possa ser realizada.
	 * 
	 * @param begin
	 *            String String que ser� transformado em double, indicando o
	 *            in�cio da faixa de valores v�lidos
	 * @param end
	 *            String String que ser� transformado em double, indicando o fim
	 *            da faixa de valores v�lidos
	 * @param number
	 *            String String que ser� transformado em double e verificado
	 * @return boolean indicando a validade ou n�o do valor informado.
	 *         <p>
	 *         <b>Obs: </b> Caso seja passado um valor null ou string vazia o
	 *         retorno do m�todo ser� sempre false e n�o ser�o lan�adas
	 *         exce��es.
	 * 
	 */
	public static boolean isValidDoubleRange(String begin, String end,
			String number) {

		boolean retorno = true;

		if (number == null || number.isEmpty() || begin == null
				|| begin.isEmpty() || end == null || end.isEmpty()) {

			return false;

		} else {

			begin = begin.replace(",", ".");
			end = end.replace(",", ".");
			number = number.replace(",", ".");

			try {
				if (Double.parseDouble(number) > Double.parseDouble(end)
						|| Double.parseDouble(number) < Double
								.parseDouble(begin)) {

					retorno = false;
				}
			} catch (NumberFormatException e) {
				
				return false;
				
			} catch (Exception e) {
				
				throw new IllegalArgumentException(
						"Argumento inv�lido no m�todo isValidDoubleRange(String, String, String)");
				
			}

		}

		return retorno;

	}

}
