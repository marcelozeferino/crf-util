package main.java.crfutil.auditor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import main.java.crfutil.auditor.anotacao.NaoAuditavel;
import main.java.crfutil.auditor.exception.ConstrutorNaoEncontradoException;
import main.java.crfutil.component.campocomposto.CampoComposto;


public class Util {

	/**
	 * M�todo para retorno do nome do campo ao qual o m�todo em quest�o se
	 * refere
	 * 
	 * @param metodo
	 *            M�todo getter do campo
	 * @return String contendo o nome do campo
	 */
	public static String obterNomeDoCampo(Method metodo) {
	
		String resultado = "";
		
		if (metodo.getName().startsWith("get")) {
	
			resultado = metodo.getName().substring(3);
	
		} else if (metodo.getName().startsWith("is")) {
	
			resultado = metodo.getName().substring(2);
	
		} 
			
		return resultado;
		
	}
	
	/**
	 * M�todo para retorno de Set contendo o nome de todos
	 * os campos (n�o marcados com com a anota��o NaoAuditavel)
	 * da classe passada no argumento do m�todo
	 * 
	 * @param {@literal Class<? extends AuditavelBase>} Classe de AuditavelBase que ser� utilizado na 
	 * recupera��o dos nomes de campos
	 * @return Set<String> Set contendo os nomes dos campos do objeto
	 */
	@SuppressWarnings("unchecked")
	public static Set<String> obterNomesDosCampos(Class<? extends AuditavelBase> classe) {
	
		Set<String> campos = new TreeSet<String>();
		
		Set<Method> metodos = obterGetters(classe);
		
		if (metodos.size() > 0){
		
			for (Method method : metodos) {
				
				
				if (method.getReturnType() != null){
					
					//Caso seja CampoComposto
					if (method.getReturnType().isAssignableFrom(CampoComposto.class)){
										
						campos.add(obterNomeDoCampo(method) + "_" + "Resposta");
						campos.add(obterNomeDoCampo(method) + "_" + "Grau");
						
					}else if (AuditavelBase.class.isAssignableFrom(method.getReturnType())){
					//Chamada recursiva caso seja do tipo AuditavelBase
						
						Class<? extends AuditavelBase> innerClass = (Class<? extends AuditavelBase>) method.getReturnType();
						
						/*
						 * Se o objeto interno (AuditavelBase) n�o for do mesmo
						 * tipo da classe passada no argumento do m�todo, realiza
						 * a chamada recursiva
						 */
						if (!innerClass.equals(classe)){
							campos.addAll(obterNomesDosCampos(innerClass));
						}else{
							campos.add(obterNomeDoCampo(method));
						}
						
					}else{
						
						campos.add(obterNomeDoCampo(method));
						
					}
					
					
				}
				
			}
			
		}
		
		return campos;
		
	}

	/**
	 * M�todo para obten��o do nome campo em diverg�ncia, observando-se
	 * a utiliza��o de atributos de refer�ncia a objetos especiais (como CampoComposto)
	 * @param div Diverg�ncia entre digita��es
	 * @return String Nome do campo
	 */
	public String obterNomeDoCampo(Divergencia div){

		String campo = null;

		try{

			if ( CampoComposto.class.isAssignableFrom(div.getTipoObjReferenciado()) ){

				campo = div.getNomeObjReferenciado().concat("_" + div.getNomeCampo());

			}else{

				campo = div.getNomeCampo();

			}

		}catch (Exception e) {

			e.printStackTrace();

		}

		return campo;

	}
	
	/**
	 * 
	 * M�todo para obten��o de todos os m�todos getters da classe. Os m�todos
	 * s�o identificados de acordo com o padr�o JavaBeans de nomea��o. Ou seja,
	 * os prefixos permitidos para m�todos getters s�o "get" e "is".
	 * 
	 * <p>
	 * Exemplo: <b>get</b>Nome(), ou <b>is</b>Masculino
	 * 
	 * @author Marcelo Zeferino
	 * @return HashSet cotendo os m�todos getters
	 * 
	 */
	 public static Set<Method> obterGetters(Class<?> classe) {
	
		Set<Method> getters = new HashSet<Method>();
	
		Method[] metodosDaClasse = classe.getDeclaredMethods();
	
		for (Method method : metodosDaClasse) {
	
			String nomeDoMetodo = method.getName();
	
			if (nomeDoMetodo.startsWith("get") || nomeDoMetodo.startsWith("is")) {
	
				/*
				 * Teste para incluir apenas m�todos que n�o tenham sido anotados com
				 * a annotation NaoAuditavel
				 */ 				
				if ( method.getAnnotation(NaoAuditavel.class ) == null) {
					getters.add(method);
				}
	
			}
	
		}
	
		return getters;
	
	}
	 
	
	/**
	 * M�todo est�tico utilit�rio que retorna o valor de descri��o encontrado, em um
	 * dos objetos ResourceBundle, para a chave passada no primeiro argumento.
	 * 
	 * @param nomeCampo String que representa a chave que deve ser procurada nos 
	 * 					arquivos de mensagens (ResourceBundle).
	 * @param resources Vararg contendo os ResoruceBundle que ser�o utilizados na pesquisa.
	 * @return String Contendo o valor encontrado para a chave passada no primeiro argumento. <br> 
	 * 				  Caso a chave n�o seja encontrada, o retorno ter� o mesmo valor do String
	 * 				  do primeiro argumento do m�todo.
	 */
	public static final String obterDescricaoCampo(String nomeCampo, ResourceBundle... resources){
			
		String result = nomeCampo;
		int size = resources.length;
		
		if ( size != 0 ){
			
			for(int i=0; i < resources.length; i++) {
				
				try{
					
					result = resources[i].getString(nomeCampo);
					break;
					
				}catch (MissingResourceException e) {
					
					if ( i == size-1 ){
						return result;
					}else{
						continue;
					}						
						
				}catch (ClassCastException   e) {
					return result;
				}
				
			}
			
		}
		
		
		return result;	
			
	}
	
	
	/**
	 * M�todo para instanciamento de um objeto por reflection
	 * atrav�s da classe passada por refer�ncia.
	 * 
	 * Para a cria��o da inst�ncia, � utilizado o construtor padr�o
	 * da classe. Caso n�o exista um construtor padr�o uma exce��o 
	 * do tipo ConstrutorNaoEncontradoException ser� lan�ada.
	 * @param classe Classe do objeto que ser� instanciado
	 * @return Objeto instanciado por reflection
	 * @throws ConstrutorNaoEncontradoException
	 */	
	@SuppressWarnings("unchecked")
	public static Object instanciarObjeto(Class<?> classe) throws ConstrutorNaoEncontradoException {
		
		Object retObj = null;
		
		try{
		
			Class clsObj = classe;
			Class partypes[] = new Class[0];
			Constructor ct = clsObj.getConstructor(partypes);
			
			Object arglist[] = new Object[0];
			
			retObj = ct.newInstance(arglist);
			
		}catch (Exception e) {
			throw new ConstrutorNaoEncontradoException();
		}
		
		return retObj;
		
	}

}
