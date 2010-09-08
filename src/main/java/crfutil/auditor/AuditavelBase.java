

/**
 * <p>Superclasse utilizada pela biblioteca para referenciar 
 * objetos passíveis de auditoria.
 * 
 * Para que objetos possam ser utilizados pela biblioteca de auditoria, tais
 * devem ser subclasses de AuditavelBase
 *   
 *  @author Marcelo Zeferino
 * 
 */

package main.java.crfutil.auditor;


public class AuditavelBase implements Auditavel {

	public AuditavelBase(){
		
	}
	
	public boolean isNull(Object object){
		
		return (object == null);
		
	}
	
	public boolean isNotNull(Object object){
		
		return !(object == null);
		
	}
	
}
