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
	 * Método para retorno do nome do campo ao qual o método em questão se
	 * refere
	 * 
	 * @param metodo
	 *            Método getter do campo
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
	 * Método para retorno de Set contendo o nome de todos
	 * os campos (não marcados com com a anotação NaoAuditavel)
	 * da classe passada no argumento do método
	 * 
	 * @param {@literal Class<? extends AuditavelBase>} Classe de AuditavelBase que será utilizado na 
	 * recuperação dos nomes de campos
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
						 * Se o objeto interno (AuditavelBase) não for do mesmo
						 * tipo da classe passada no argumento do método, realiza
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
	 * Método para obtenção do nome campo em divergência, observando-se
	 * a utilização de atributos de referência a objetos especiais (como CampoComposto)
	 * @param div Divergência entre digitações
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
	 * Método para obtenção de todos os métodos getters da classe. Os métodos
	 * são identificados de acordo com o padrão JavaBeans de nomeação. Ou seja,
	 * os prefixos permitidos para métodos getters são "get" e "is".
	 * 
	 * <p>
	 * Exemplo: <b>get</b>Nome(), ou <b>is</b>Masculino
	 * 
	 * @author Marcelo Zeferino
	 * @return HashSet cotendo os métodos getters
	 * 
	 */
	 public static Set<Method> obterGetters(Class<?> classe) {
	
		Set<Method> getters = new HashSet<Method>();
	
		Method[] metodosDaClasse = classe.getDeclaredMethods();
	
		for (Method method : metodosDaClasse) {
	
			String nomeDoMetodo = method.getName();
	
			if (nomeDoMetodo.startsWith("get") || nomeDoMetodo.startsWith("is")) {
	
				/*
				 * Teste para incluir apenas métodos que não tenham sido anotados com
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
	 * Método estático utilitário que retorna o valor de descrição encontrado, em um
	 * dos objetos ResourceBundle, para a chave passada no primeiro argumento.
	 * 
	 * @param nomeCampo String que representa a chave que deve ser procurada nos 
	 * 					arquivos de mensagens (ResourceBundle).
	 * @param resources Vararg contendo os ResoruceBundle que serão utilizados na pesquisa.
	 * @return String Contendo o valor encontrado para a chave passada no primeiro argumento. <br> 
	 * 				  Caso a chave não seja encontrada, o retorno terá o mesmo valor do String
	 * 				  do primeiro argumento do método.
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
	 * Método para instanciamento de um objeto por reflection
	 * através da classe passada por referência.
	 * 
	 * Para a criação da instância, é utilizado o construtor padrão
	 * da classe. Caso não exista um construtor padrão uma exceção 
	 * do tipo ConstrutorNaoEncontradoException será lançada.
	 * @param classe Classe do objeto que será instanciado
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
