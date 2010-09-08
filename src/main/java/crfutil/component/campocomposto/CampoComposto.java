package main.java.crfutil.component.campocomposto;

import java.io.Serializable;

import javax.persistence.Embeddable;

import main.java.crfutil.auditor.AuditavelBase;


/**
 * Classe utilitária para ser utilizada com campos compostos pela resposta de uma pergunta e o grau
 * @author Pattrick Müller
 */
@SuppressWarnings("serial")
@Embeddable
public class CampoComposto extends AuditavelBase implements Serializable {
	
		
	/**
	 * Atributo do tipo Integer que armazena a resposta.
	 */
	private Integer resposta;
	
	/**
	 * Atributo do tipo Integer que armazena o grau.
	 */
	private Integer grau;
	

	
	public CampoComposto(){
		super();
	}
	
	public CampoComposto(Integer resposta, Integer grau){
		super();
		setResposta(resposta);
		setGrau(grau);
	}	
	
	public Integer getResposta() {
		
		if ( this.resposta == null ){
			return -1;
		}else{
			return resposta;	
		}
		
	}
	
	/**
	 * Setter para o atributo resposta. Este método atualiza também o atributo
	 * desabilitagrau com os valores "true" ou "false" de acordo com o valor da resposta.
	 */
	public void setResposta(Integer resposta) {

		if (resposta == null || resposta == -1){
			this.resposta = null;
		}else{
			this.resposta = resposta;	
		}	
		
	}
	
	public Integer getGrau() {
		
		if ( this.grau == null ){
			return -1;
		}else{
			return grau;	
		}
		
	
	}
	
	
	/**
	 * Setter para o atributo grau. Este método atualiza o atributo com o valor selecionado pelo usuário
	 * ou atualiza o atributo para o valor -1 (representa vazio ou nulo) caso a resposta do usuario seja
	 * diferente de "1 - Sim", neste caso o campo grau deve ser igual a "nulo"
	 */
	public void setGrau(Integer grau) {
		
		if ( grau == null || grau == -1 ){
			this.grau = null;
		}else{
			this.grau = grau;
		}
		
	}

	
	/**
	 * Método static para validação de objetos do tipo CampoComposto, de acordo com suas regras.
	 * 
	 * Caso o argumento "permiteVazio" seja falso, não será permitido valor "-1" no
	 * preenchimento do campo "resposta".
	 * 
	 * <p>Regras:
	 * <li>Resposta = 0, Grau deve ser -1 (vazio)
	 * <li>Resposta = 1, Grau deve ser 1,2,3 ou 4
	 * <li>Resposta = -1, Grau deve ser -1 (vazio, se, e somente se, o argumento "permiteVazio" for true)
	 * 
	 * @param campo Referência do objeto CampoComposto que será validado
	 * @param permiteVazio Boolean indicando que o campo pode ser vazio
	 * @throws IllegalArgumentException Indicando que ocorreu erro durante a validação
	 */
	public static void validar(CampoComposto campo, boolean permiteVazio){
		
		if ( campo == null ){
			throw new IllegalArgumentException("O campo não foi preenchido (NullPointerException)");
		}
		
		if ( !permiteVazio ){
			
			if (campo.getResposta() == null || campo.getResposta() == -1) {

	    		throw new IllegalArgumentException("O campo resposta não pode ficar vazio.");

	    	} else if (campo.getResposta() != 1) {
		
	    		// Caso a resposta seja diferente de 1-Sim o campo grau não deve ser preenchido
	    		if (campo.getGrau() != null && campo.getGrau().intValue() != -1) {

	    			throw new IllegalArgumentException("O campo grau da questão não deve ser preenchido quando o campo resposta for diferente de 1-Sim.");
	    		
	    		}

	    	} else if (campo.getResposta() == 1) {

	    		// Caso a resposta seja 1-Sim o campo grau deve ser preenchido
	    		if (campo.getGrau() == null || campo.getGrau().intValue() == -1 
	    				|| (campo.getGrau() < 0	|| campo.getGrau() > 4) ) {

	    			throw new IllegalArgumentException("O campo grau da questão deve ser preenchido (com valores entre 1 e 4 quando) o campo resposta for 1-Sim.");
	    		}

	    	}
			
		}else{
			
		
	    	if (campo.getResposta() != null && campo.getResposta() != 1) {
		
	    		// Caso a resposta seja diferente de 1-Sim o campo grau não deve ser preenchido
	    		if (campo.getGrau() != null && campo.getGrau().intValue() != -1) {

	    			throw new IllegalArgumentException("O campo grau da questão não deve ser preenchido quando o campo resposta for diferente de 1-Sim.");
	    		
	    		}

	    	} else if (campo.getResposta() != null && campo.getResposta() == 1) {

	    		// Caso a resposta seja 1-Sim o campo grau deve ser preenchido
	    		if (campo.getGrau() == null || campo.getGrau().intValue() == -1) {

	    			throw new IllegalArgumentException("O campo grau da questão deve ser preenchido (com valores entre 1 e 4 quando) quando o campo resposta for 1-Sim.");
	    		}

	    	}
		}
				
		
	}
	
	 
	/**
	 * Método para validação do objeto CampoComposto, de acordo com suas regras.
	 * 
	 * Caso o argumento "permiteVazio" seja falso, não será permitido valor "-1" no
	 * preenchimento do campo "resposta".
	 * 
	 * <p>Regras:
	 * <li>Resposta = 0, Grau deve ser -1 (vazio)
	 * <li>Resposta = 1, Grau deve ser 1,2,3 ou 4
	 * <li>Resposta = -1, Grau deve ser -1 (vazio, se, e somente se, o argumento "permiteVazio" for true)
	 * 
	 * @param campo Referência do objeto CampoComposto que será validado
	 * @param permiteVazio Boolean indicando que o campo pode ser vazio
	 * @throws IllegalArgumentException Indicando que ocorreu erro durante a validação
	 */
	public void validar(boolean permiteVazio){
		
		CampoComposto.validar(this, permiteVazio);
		
	}
	
	
	/**
	 * Método stático para verificação se o campo composto passado por referência
	 * não está com seus valores devidamente preenchidos (ou nulos).
	 * @param campo CampoComposto que será utilizado na verificação
	 * @return Boolean Sendo true para indicar que o campo não está preenchido e
	 * false para indicar que os atributos do CampoComposto estão preenchidos
	 *
	 */
	public static boolean naoPreechido(CampoComposto campo){
		
		if ( campo != null 
			 && ( (campo.getResposta() != null && campo.getResposta() != -1)
				   || (campo.getGrau() != null && campo.getGrau() != -1) ) ){
			
			return false;
			
		}else{
			
			return true;
			
		}
		
	}
	
	/**
	 * Método para verificação se o objeto não está com seus valores devidamente preenchidos (ou nulos).
	 * @return Boolean Sendo true para indicar que o campo não está preenchido e
	 * false para indicar que os atributos estão preenchidos
	 *
	 */
	public boolean naoPreechido(){
		
		return CampoComposto.naoPreechido(this);
		
	}
	
	@Override
	public String toString() {
		
		return "[RESPOSTA: " + getResposta() + " - GRAU: " + getGrau() + "]";
		
	}	
	
	
	
	

}
