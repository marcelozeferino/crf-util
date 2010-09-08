package main.java.crfutil.auditor.exception;


@SuppressWarnings("serial")
public class ObjetosDeClassesDiferentesException extends Exception {

	
	public ObjetosDeClassesDiferentesException() {
		super("Não são permitidas comparações com classe diferentes.");
	}

}
