package main.java.crfutil.auditor.exception;


@SuppressWarnings("serial")
public class ListaDeMetodosVaziaException extends Exception {

	public ListaDeMetodosVaziaException() {
		super("A lista de métodos getter está vazia.");
		
	}

	
}
