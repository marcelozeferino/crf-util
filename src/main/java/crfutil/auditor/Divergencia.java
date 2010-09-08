/**
 * <p>Classe pública utilizada para o retorno das divergências
 * entre os campos dos objetos passados como argumentos do método 
 * de auditoria.
 * 
 * @author Marcelo Zeferino
 */

package main.java.crfutil.auditor;

public class Divergencia {
	
	/**
	 * Nome do campo que contém a divergência
	 */
	private String nomeCampo;
	
	/**
	 * Valor informado para o campo na primeira digitação
	 */
	private String valorPrimEntrada;
	
	/**
	 * Valor informado para o campo na segunda digitacão
	 */
	private String valorSegEntrada;
	
	/**
	 * Nome (String) do objeto onde o atributo que contém a divergência
	 * está contido. 
	 */
	private String nomeObjReferenciado;
	
	/**
	 * Tipo (Class) do objeto onde o atributo que contém a divergência
	 * está contido. 
	 */
	private Class<?> tipoObjReferenciado;
	
	
	public Divergencia(){
		super();
	}
	
	
	public Divergencia(String nomeCampo, String valorPrimEntrada,
			String valorSegEntrada, String nomeObjReferenciado, Class<?> tipoObjReferenciado) {
		super();
		this.nomeCampo = nomeCampo;
		this.valorPrimEntrada = valorPrimEntrada;
		this.valorSegEntrada = valorSegEntrada;
		this.nomeObjReferenciado = nomeObjReferenciado;
		this.tipoObjReferenciado = tipoObjReferenciado;
	}
	
	public String getNomeObjReferenciado() {
		return nomeObjReferenciado;
	}
	public void setNomeObjReferenciado(String nomeObjReferenciado) {
		this.nomeObjReferenciado = nomeObjReferenciado;
	}
	public String getNomeCampo() {
		return nomeCampo;
	}
	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}
	public String getValorPrimEntrada() {
		return valorPrimEntrada;
	}
	public void setValorPrimEntrada(String valorPrimEntrada) {
		this.valorPrimEntrada = valorPrimEntrada;
	}
	public String getValorSegEntrada() {
		return valorSegEntrada;
	}
	public void setValorSegEntrada(String valorSegEntrada) {
		this.valorSegEntrada = valorSegEntrada;
	}
	
	

	public Class<?> getTipoObjReferenciado() {
		return tipoObjReferenciado;
	}


	public void setTipoObjReferenciado(Class<?> tipoObjReferenciado) {
		this.tipoObjReferenciado = tipoObjReferenciado;
	}


	@Override
	public String toString(){				
		
		String resultado = null;
		
		if ( this.nomeObjReferenciado == null || this.nomeObjReferenciado.isEmpty() ){
			
			resultado = "Divergência no campo " + this.getNomeCampo()
			+ ". Valores informados -> #1: "
			+ this.valorPrimEntrada + " #2: " + this.getValorSegEntrada()
			+ ".";	
			
			
		}else{
			
			resultado =  "Divergência no campo " + this.getNomeCampo()
			+ " (em " + this.getNomeObjReferenciado() + "). Valores informados -> #1: "
			+ this.valorPrimEntrada + " #2: " + this.getValorSegEntrada()
			+ ".";	
			
		}
				
		return resultado;
		
	}
	

}
