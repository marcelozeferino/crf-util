/**
 * 
 * <p> Anota��o obrigat�ria para m�todos que n�o devem ser
 * considerados no processo de auditoria dos objetos.
 * 
 * Todos os m�todos de getXXX ou isXXX (seguindo o padr�o JavaBeans)
 * s�o considerados na auditoria e �nica maneira de fazer com que 
 * sejam considerados � marcando-os como n�o audit�veis.
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
