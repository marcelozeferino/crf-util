/**
 * <p> Classe p�blica que funciona como uma fachada para acesso
 * ao m�todo de auditoria de objetos.
 * 
 * O m�todo est�tico realizarAuditoria � respons�vel por executar as
 * chamadas necess�rias para realiza��o do processo de auditoria.
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
	 * M�todo de fachada respons�vel acessar o m�todo de auditoria autom�tica
	 * realizando a compara��o dos campos dos objetos passados como argumentos
	 * 
	 * <p>
	 * <b>Aten��o: </b> Os objetos passados como argumentos devem implementar o
	 * padr�o JavaBeans e conter apenas atributos encapsulados + m�todos
	 * configuradores.
	 * 
	 * M�todos que n�o dever�o ser considerados pela auditoria precisam
	 * ser marcados com a anota��o {@literal @NaoAuditavel }. 
	 * 
	 * @see NaoAuditavel  
	 * 
	 * @param objeto1
	 *            Objeto a ser comparado
	 * @param objeto2
	 *            Objeto a ser comparado com o objeto1
	 * @return Set<Divergencia> Set contendo a lista de diverg�ncias encontradas na
	 *         compara��o dos objetos
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
