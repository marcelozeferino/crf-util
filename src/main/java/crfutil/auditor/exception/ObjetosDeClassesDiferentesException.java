package main.java.crfutil.auditor.exception;


@SuppressWarnings("serial")
public class ObjetosDeClassesDiferentesException extends Exception {

	
	public ObjetosDeClassesDiferentesException() {
		super("N�o s�o permitidas compara��es com classe diferentes.");
	}

}
