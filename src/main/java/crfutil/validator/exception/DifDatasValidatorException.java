package main.java.crfutil.validator.exception;

@SuppressWarnings("serial")
public class DifDatasValidatorException extends IllegalArgumentException {
	
	public DifDatasValidatorException(){
		super("Erro na valida��o das datas passadas como " +
					"argumento para a fun��o de valida��o de datas.");
	}

}
