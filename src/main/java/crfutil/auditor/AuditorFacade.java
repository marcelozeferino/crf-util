/**
 * <p> Classe pública que funciona como uma fachada para acesso
 * ao método de auditoria de objetos.
 * 
 * O método estático realizarAuditoria é responsável por executar as
 * chamadas necessárias para realização do processo de auditoria.
 * 
 * @author Marcelo Zeferino
 * 
 */


package main.java.crfutil.auditor;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import main.java.crfutil.auditor.anotacao.NaoAuditavel;
import main.java.crfutil.auditor.exception.ConstrutorNaoEncontradoException;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;


public class AuditorFacade {

	/**
	 * Método de fachada responsável acessar o método de auditoria automática
	 * realizando a comparação dos campos dos objetos passados como argumentos
	 * 
	 * <p>
	 * <b>Atenção: </b> Os objetos passados como argumentos devem implementar o
	 * padrão JavaBeans e conter apenas atributos encapsulados + métodos
	 * configuradores.
	 * 
	 * Métodos que não deverão ser considerados pela auditoria precisam
	 * ser marcados com a anotação {@literal @NaoAuditavel }. 
	 * 
	 * @see NaoAuditavel  
	 * 
	 * @param objeto1
	 *            Objeto a ser comparado
	 * @param objeto2
	 *            Objeto a ser comparado com o objeto1
	 * @return Set<Divergencia> Set contendo a lista de divergências encontradas na
	 *         comparação dos objetos
	 * 
	 * @throws ObjetosDeClassesDiferentesException
	 * @throws ListaDeMetodosVaziaException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ConstrutorNaoEncontradoException 
	 */
	public static final  Set<Divergencia> realizarAuditoria(
			AuditavelBase objeto1, AuditavelBase objeto2)
			throws ObjetosDeClassesDiferentesException,
			ListaDeMetodosVaziaException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, ConstrutorNaoEncontradoException {

		return new Auditor().auditarObjetos(objeto1, objeto2, null, null);

	}

}
