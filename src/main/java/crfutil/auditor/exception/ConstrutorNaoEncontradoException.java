package main.java.crfutil.auditor.exception;

@SuppressWarnings("serial")
public class ConstrutorNaoEncontradoException extends Exception {

	public ConstrutorNaoEncontradoException() {
		super("N�o foi encontrado um construtor para o objeto sendo auditado.");
	}

}
