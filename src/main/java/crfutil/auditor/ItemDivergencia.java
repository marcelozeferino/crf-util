package main.java.crfutil.auditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada para representar um item de divergência
 * entre digitações, sendo inserido em lista fonte de dados
 * para o Relatório de Divergências
 * 
 * @author Marcelo Zeferino
 *
 */
public class ItemDivergencia {
	
	/**
	 * Identificador do registro em auditoria, podendo ser PID ou Screening Number(caso seja
	 * um form de Falha de Inclusao).
	 * Esta campo foi declarado como String (apesar de ser um inteiro) 
	 * para que possa ser facilmente manipulado no relatório (inserção do
	 * índice).
	 * <p>Exemplo: {@code PID: 1234(Índice:1) } 
	 */
	private String identificador;
	
	/**
	 * Login do digitador que realizou a primeira digitação
	 */
	private String digitador1;
	
	/**
	 * Login do digitador que realizou a segunda digitação
	 */
	private String digitador2;	
	
	/**
	 * Lista de diverências obtidas pela biblioteca de auditoria
	 */
	private List<Divergencia> divergencias = new ArrayList<Divergencia>();
	
	/**
	 * Size da lista de divergências encontradas
	 */
	private Integer total;
	
	
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String pid) {
		this.identificador = pid;
	}
	public String getDigitador1() {
		return digitador1;
	}
	public void setDigitador1(String digitador1) {
		this.digitador1 = digitador1;
	}
	public String getDigitador2() {
		return digitador2;
	}
	public void setDigitador2(String digitador2) {
		this.digitador2 = digitador2;
	}
	public List<Divergencia> getDivergencias() {
		return divergencias;
	}
	public void setDivergencias(List<Divergencia> divergencias) {
		this.divergencias = divergencias;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer totalDiv) {
		this.total = totalDiv;
	}
	
	
	
}
