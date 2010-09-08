package main.java.crfutil.validator;

/**
 * Classe utilitária para validação de valores numéricos
 * 
 * @author Marcelo Zeferino
 * 
 */
public class NumericValidator {

	/**
	 * Método static utilizado para verificar se o valor está dentro da faixa
	 * informada
	 * 
	 * @param begin
	 *            int Indicando o início da faixa de valores válidos
	 * @param end
	 *            int indicando o fim da faixa de valores válidos
	 * @param number
	 *            int Número que será validado
	 * @return boolean indicando a validade ou não do valor informado.
	 */
	public static boolean isValidRange(int begin, int end, int number) {

		boolean retorno = true;

		if (number > end || number < begin) {
			retorno = false;
		}

		return retorno;
	}

	/**
	 * Método static utilizado para verificar se o valor está dentro da faixa
	 * informada
	 * 
	 * @param begin
	 *            int Indicando o início da faixa de valores válidos
	 * @param end
	 *            int indicando o fim da faixa de valores válidos
	 * @param number
	 *            String Número que será convertido para número e validado
	 * @return boolean Indicando a validade ou não do valor informado.
	 *         <p>
	 *         <b>Obs: </b> Caso seja passado um valor null ou string vazia o
	 *         retorno do método será sempre false e não serão lançadas
	 *         exceções.
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
					"Argumento inválido no método isValidRange(int, int, String)");
		}

		if (intNumber > end || intNumber < begin) {
			retorno = false;
		}

		return retorno;
	}

	/**
	 * Método static utilizado para verificar se o valor (Double) está dentro da
	 * faixa informada
	 * 
	 * @param begin
	 *            double Indicando o início da faixa de valores válidos
	 * @param end
	 *            double indicando o fim da faixa de valores válidos
	 * @param number
	 *            double Número que será validado
	 * @return boolean indicando a validade ou não do valor informado.
	 */
	public static boolean isValidRange(double begin, double end, double number) {

		boolean retorno = true;

		if (number > end || number < begin) {
			retorno = false;
		}

		return retorno;

	}

	/**
	 * Método static utilizado para verificar se o valor (Double) está dentro da
	 * faixa informada.
	 * 
	 * <p>
	 * <b>OBS</b> Em todos os argumentos o separador "," será convertido (caso
	 * exista) para "." para que a conversão para double possa ser realizada.
	 * 
	 * @param begin
	 *            String String que será transformado em double, indicando o
	 *            início da faixa de valores válidos
	 * @param end
	 *            String String que será transformado em double, indicando o fim
	 *            da faixa de valores válidos
	 * @param number
	 *            String String que será transformado em double e verificado
	 * @return boolean indicando a validade ou não do valor informado.
	 *         <p>
	 *         <b>Obs: </b> Caso seja passado um valor null ou string vazia o
	 *         retorno do método será sempre false e não serão lançadas
	 *         exceções.
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
						"Argumento inválido no método isValidDoubleRange(String, String, String)");
				
			}

		}

		return retorno;

	}

}
