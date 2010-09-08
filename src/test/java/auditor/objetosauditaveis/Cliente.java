package test.java.auditor.objetosauditaveis;

import main.java.crfutil.auditor.AuditavelBase;

public class Cliente extends AuditavelBase {

	public Cliente(){};

	public Cliente(int idCliente, String nome, double salario, boolean ativo,
			Cliente conjuge) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.salario = salario;
		this.ativo = ativo;
		this.conjuge = conjuge;
	}

	private int idCliente;
	private String nome;
	private double salario;
	private boolean ativo;
	private Cliente conjuge;

	public Cliente getConjuge() {
		return conjuge;
	}

	public void setConjuge(Cliente conjuge) {
		this.conjuge = conjuge;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		
		return "[IdCliente: " + getIdCliente() + " - Nome: " + getNome() + "]"; 
		
				
		
	}

}
