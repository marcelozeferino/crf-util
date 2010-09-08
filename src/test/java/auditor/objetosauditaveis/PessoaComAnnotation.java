package test.java.auditor.objetosauditaveis;

import java.sql.Date;

import main.java.crfutil.auditor.AuditavelBase;
import main.java.crfutil.auditor.anotacao.NaoAuditavel;
import main.java.crfutil.component.date.IntegerDate;
import main.java.crfutil.component.date.IntegerRealDate;


@SuppressWarnings("deprecation")
public class PessoaComAnnotation extends AuditavelBase {

	public PessoaComAnnotation() {
	}

	public PessoaComAnnotation(int id, String nome, Integer idade) {
		this.idPessoa = id;
		this.nome = nome;
		this.idade = idade;
	}

	/*
	 * Bloco de inicialização para teste
	 */
	{

		setAuditado(false);
		setDigitacao(1234);
		setDataDigitacao(new Date(1983, 10, 31));

	}

	public IntegerDate getIntegerDate() {
		return aintegerDate;
	}

	public void setIntegerDate(IntegerDate integerDate) {
		this.aintegerDate = integerDate;
	}
	
	private IntegerDate aintegerDate;
	private IntegerRealDate integerRealDate;
	
	
	
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

	/*
	 * Atributos que não devem ser auditados, marcados com a annotation
	 * NaoAuditavel
	 */
	private Boolean auditado;
	private Integer digitacao;
	private Date dataDigitacao;

	@NaoAuditavel
	public Boolean getAuditado() {
		return auditado;
	}

	public void setAuditado(Boolean auditado) {
		this.auditado = auditado;
	}

	@NaoAuditavel
	public Integer getDigitacao() {
		return digitacao;
	}

	public void setDigitacao(Integer digitacao) {
		this.digitacao = digitacao;
	}

	@NaoAuditavel
	public Date getDataDigitacao() {
		return dataDigitacao;
	}

	public void setDataDigitacao(Date dataDigitacao) {
		this.dataDigitacao = dataDigitacao;
	}

	public IntegerDate getAintegerDate() {
		return aintegerDate;
	}

	public void setAintegerDate(IntegerDate aintegerDate) {
		this.aintegerDate = aintegerDate;
	}

	public IntegerRealDate getIntegerRealDate() {
		return integerRealDate;
	}

	public void setIntegerRealDate(IntegerRealDate integerRealDate) {
		this.integerRealDate = integerRealDate;
	}

}
