/**
 * <p> Classe que concentra as regras de negócio utilizadas no processo de
 * auditoria dos objetos.
 * 
 * Esta classe deve ser utilizada através da classe de fachada AuditorFacade
 * 
 * @author Marcelo Zeferino 
 * 
 */

package main.java.crfutil.auditor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import main.java.crfutil.auditor.exception.ConstrutorNaoEncontradoException;
import main.java.crfutil.auditor.exception.ListaDeMetodosVaziaException;
import main.java.crfutil.auditor.exception.ObjetosDeClassesDiferentesException;


final class Auditor {

	/**
	 * Método responsável por realizar a comparação dos objetos - Processo de
	 * Auditoria - verificando se os dois objetos possuem preenchimento
	 * equivalente em cada um de seus atributos.
	 * 
	 * <p>
	 * <b>Atenção: </b> Os objetos passados como argumentos devem implementar o
	 * padrão JavaBeans e conter apenas atributos encapsulados + métodos
	 * configuradores
	 * 
	 * @param objeto1
	 *            Objeto a ser comparado
	 * @param objeto2
	 *            Objeto a ser comparado com o objeto1
	 * @param nomeObjReferenciado Nome do objeto onde o atributo divergente está contido
	 * @param tipoObjReferenciado Tipo (Class) do objeto que contém o atributo divergente
	 * @return Set<Divergencia> Lista de divergências encontradas na comparação
	 *         dos objetos
	 * 
	 * @throws ObjetosDeClassesDiferentesException
	 * @throws ListaDeMetodosVaziaException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ConstrutorNaoEncontradoException 
	 * @throws InstantiationException
	 */
	final Set<Divergencia> auditarObjetos(AuditavelBase objeto1,
			AuditavelBase objeto2, String nomeObjReferenciado, Class<?> tipoObjReferenciado)
			throws ObjetosDeClassesDiferentesException,
			ListaDeMetodosVaziaException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, ConstrutorNaoEncontradoException {

		// Verificação de segurança para eliminar referências nulas
		if (objeto1 == null || objeto2 == null) {
			throw new IllegalArgumentException(
					"Referência inválida (null) para um dos objetos.");
		}

		// Checagem para garantir que os dois objetos são da mesma classe
		if (!objeto1.getClass().toString()
				.equals(objeto2.getClass().toString())) {
			throw new ObjetosDeClassesDiferentesException();
		}

		// Obtenção da classe a qual o objeto pertence
		Class<?> classe = Class.forName(objeto1.getClass().getName());

		// Obtenção dos métodos get da classe
		Set<Method> metodos = Util.obterGetters(classe);

		if (metodos.isEmpty()) {
			throw new ListaDeMetodosVaziaException();
		}

		// Chamada ao método responsável por realizar a comparação dos campos
		Set<Divergencia> resultado = compararValoresDeCampos(metodos, objeto1,
				objeto2, nomeObjReferenciado, tipoObjReferenciado);

		return resultado;

	}

	private Set<Divergencia> compararValoresDeCampos(Set<Method> metodos,
			AuditavelBase objeto1, AuditavelBase objeto2,
			String nomeObjReferenciado, Class<?> tipoObjReferenciado) throws IllegalAccessException,
			InvocationTargetException, ObjetosDeClassesDiferentesException,
			ListaDeMetodosVaziaException, ClassNotFoundException, ConstrutorNaoEncontradoException, IllegalArgumentException {

		Object[] argList = null;
		String retornoMetodo1 = null;
		String retornoMetodo2 = null;

		Set<Divergencia> divergencias = new HashSet<Divergencia>();

		// Iterando o set de métodos e comparando os resultados
		for (Method method : metodos) {

			/*
			 * Caso o retorno do método seja outro objeto e seja subclasse de
			 * AuditavelBase, é realizada a análise para verificar a necessidade
			 * de chamada recursiva para auditoria do objeto interno (retornado
			 * pelo método em questão)
			 */
			if (AuditavelBase.class.isAssignableFrom(method.getReturnType())) {

				// COMPARAÇÃO RECURSIVA COM OUTRO OBJETO AUDITAVEL

				AuditavelBase objInterno1 = (AuditavelBase) method.invoke(
						objeto1, argList);

				AuditavelBase objInterno2 = (AuditavelBase) method.invoke(
						objeto2, argList);

				// Chamada ao método que realiza a comparação
				divergencias.addAll(realizarComparacaoRecursiva(method,
						objInterno1, objInterno2));

			} else {
				/*
				 * Caso o retorno do método não seja um objeto subclasse de
				 * AuditavelBase, ocorrerá a chamada simples ao método de
				 * auditoria
				 */

				retornoMetodo1 = String
						.valueOf(method.invoke(objeto1, argList));

				retornoMetodo2 = String
						.valueOf(method.invoke(objeto2, argList));

				
				/*Caso os dois valores sejam nulos, passa para o próximo método
				sem salvar divergência (sao iguais)*/
				if ( retornoMetodo1 == null && retornoMetodo2 == null ){
					continue;
				}
					
				//Caso exista divergência (s)
				if (((retornoMetodo1 == null && retornoMetodo2 != null) || (retornoMetodo2 == null && retornoMetodo1 == null))
						|| !retornoMetodo1.equals(retornoMetodo2)) {

					String nomeCampo = Util.obterNomeDoCampo(method);

					Divergencia div = new Divergencia();

					// Caso seja uma chamada recursiva ao método, informa o
					// nome do campo principal
					if (nomeObjReferenciado != null
							&& !nomeObjReferenciado.isEmpty()) {
						
						div.setNomeObjReferenciado(nomeObjReferenciado);
						
						if ( tipoObjReferenciado != null) {
							div.setTipoObjReferenciado(tipoObjReferenciado);
						}
						
					}
					
					// caso não seja uma chamada recursiva informa
					// apenas os dados normais e o nome do campo
					div.setNomeCampo(nomeCampo);
					div.setValorPrimEntrada(retornoMetodo1);
					div.setValorSegEntrada(retornoMetodo2);
					
					divergencias.add(div);

				}

			}

		}

		return divergencias;
	}

	private Set<Divergencia> realizarComparacaoRecursiva(Method method,
			AuditavelBase objInterno1, AuditavelBase objInterno2)
			throws ObjetosDeClassesDiferentesException,
			ListaDeMetodosVaziaException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException, ConstrutorNaoEncontradoException {

		Set<Divergencia> divsRecursivas = new HashSet<Divergencia>();

		/*
		 * Caso os dois objetos sejam diferentes de null, ocorre a chamada
		 * recursiva ao método auditarObjetos
		 */
		if (objInterno1 != null && objInterno2 != null) {

			Set<Divergencia> divsEmObjsReferenciados = this.auditarObjetos(
					objInterno1, objInterno2, Util.obterNomeDoCampo(method), objInterno1.getClass());

			if (divsEmObjsReferenciados != null
					&& !divsEmObjsReferenciados.isEmpty()) {
				divsRecursivas.addAll(divsEmObjsReferenciados);
			}

		} else if (objInterno1 != null) {
			/*
			 * Caso o objetoInterno1 não seja nulo e objetoInterno2 seja nulo,
			 * há divergência
			 */			
			
			//instanciando objeto para ser comparado com o objeto não nulo
			objInterno2 = (AuditavelBase) Util.instanciarObjeto(objInterno1.getClass());
			
			//auditoria dos objetos
			Set<Divergencia> divsEmObjsReferenciados = this.auditarObjetos(
					objInterno1, objInterno2, Util.obterNomeDoCampo(method), objInterno1.getClass());

			if (divsEmObjsReferenciados != null
					&& !divsEmObjsReferenciados.isEmpty()) {
				divsRecursivas.addAll(divsEmObjsReferenciados);
			}
		

		} else if (objInterno2 != null) {
			/*
			 * Caso o objetoInterno2 não seja nulo e objetoInterno1 seja nulo,
			 * há divergência
			 */

			//Insntanciando objeto para ser comparado com o objeto não nulo			
			objInterno1 = (AuditavelBase) Util.instanciarObjeto(objInterno2.getClass());
			
			//Realização da auditoria
			Set<Divergencia> divsEmObjsReferenciados = this.auditarObjetos(
					objInterno1, objInterno2, Util.obterNomeDoCampo(method), objInterno2.getClass());

			if (divsEmObjsReferenciados != null
					&& !divsEmObjsReferenciados.isEmpty()) {
				divsRecursivas.addAll(divsEmObjsReferenciados);
			}			
			
			/*
			 * } else { se os dois objetos são nulos nada precisa ser realizado
			 * não há divergência
			 */
			
		}

		return divsRecursivas;

	}

}
