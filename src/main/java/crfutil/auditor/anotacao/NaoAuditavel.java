/**
 * 
 * <p> Anotação obrigatória para métodos que não devem ser
 * considerados no processo de auditoria dos objetos.
 * 
 * Todos os métodos de getXXX ou isXXX (seguindo o padrão JavaBeans)
 * são considerados na auditoria e única maneira de fazer com que 
 * sejam considerados é marcando-os como não auditáveis.
 * 
 * <p><b>Exemplo: </b>
 * {@code
 * 	 private int digitacao;
 *  
 *   {@literal @NaoAuditavel}
 *   public int getDigitacao(){ return digitacao };
 * }
 * 
 * @author Marcelo Zeferino
 * 
 */
package main.java.crfutil.auditor.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NaoAuditavel {

}
