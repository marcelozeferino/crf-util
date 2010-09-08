package test.java.auditor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import test.java.auditor.objetosauditaveis.Pessoa;



public class AuditorSpikeTest {

	public static void main(String[] args) {

		//Instanciando e obtendo valores da classe de maneira simples		
		Pessoa pessoa = new Pessoa();

		System.out.println( pessoa.getClass().toString() );
		System.out.println( pessoa.getClass().getName() );
		
		System.out.println(pessoa.getIdPessoa() + " - " + pessoa.getNome()
				+ " - " + pessoa.getIdade());

		try {
			
			 // Instanciando a classe Pessoa
			
			
			 Class<?> c = Class.forName( pessoa.getClass().getName() );
			 
			 System.out.println("\nLista de Métodos ================= ");
			 
			 //Obtendo array com a lista de métodos da classe
			 Method [] m = c.getDeclaredMethods();
			 
			 //Iterando o array de métodos
			 for (Method method : m) { 
			
				 if ( method.getName().equals("getIdPessoa") ){
					 
					 Object [] argList = null;
					 
					 //Invocação do método por reflection
					 Object objRetornado = method.invoke(pessoa, argList);
					 
					 //Transformaçao do valor retornado pelo método em um String
					 //visando facilitar as comparações com a utlização do equals
					 String valorRetorno = String.valueOf(objRetornado);
					 
					 System.out.println("");
					 System.out.println("Valor de IDPESSOA por getIdPessoa: " + valorRetorno );
					 System.out.println("");
					 
				 }else if ( method.getName().equals("getNome") ){
					
					 Object [] argList = null;
					 
					 Object objRetornado = method.invoke(pessoa, argList);
					 String valorRetorno = String.valueOf(objRetornado);
					 
					 System.out.println("");
					 System.out.println("Valor de NOME por getNome: " + valorRetorno );
					 System.out.println("");
					 
				 }else if ( method.getName().equals("getIdade") ){
					 
					 Object [] argList = null;
					 
					 Object objRetornado = method.invoke(pessoa, argList);
					 String valorRetorno = String.valueOf(objRetornado);
					 
					 System.out.println("");
					 System.out.println("Valor de IDADE por getIdade: " + valorRetorno );
					 System.out.println("");
					 
				 }
					 
			 
				 System.out.println( method.toString() ); 
				 
			 }
			 

			System.out.println("Lista de atributos ==================");

			Field[] f = c.getDeclaredFields();
			
			for (Field field : f) {			
							
				System.out.println("Nome do campo: " + field.getName()
						+ " - Tipo: " + field.getType() );
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
}
