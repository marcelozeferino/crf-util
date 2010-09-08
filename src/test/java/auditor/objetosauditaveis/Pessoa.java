package test.java.auditor.objetosauditaveis;



import main.java.crfutil.auditor.AuditavelBase;

public class Pessoa extends AuditavelBase{

	public Pessoa(){		
	}
	
	public Pessoa(int id, String nome, Integer idade){
		this.idPessoa = id;
		this.nome = nome;
		this.idade = idade;
	}
	
//	{
//		idPessoa = 1;
//		idade = Integer.valueOf(10);
//		nome = "Pedro Torres Zeferino";	
//	}
	
	private int idPessoa;
	private Integer idade;
	private String nome;
	
		
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
