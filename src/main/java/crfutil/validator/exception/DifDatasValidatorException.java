package main.java.crfutil.validator.exception;

@SuppressWarnings("serial")
public class DifDatasValidatorException extends IllegalArgumentException {
	
	public DifDatasValidatorException(){
		super("Erro na validação das datas passadas como " +
					"argumento para a função de validação de datas.");
	}

}
