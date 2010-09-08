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
			 
			 System.out.println("\nLista de M�todos ================= ");
			 
			 //Obtendo array com a lista de m�todos da classe
			 Method [] m = c.getDeclaredMethods();
			 
			 //Iterando o array de m�todos
			 for (Method method : m) { 
			
				 if ( method.getName().equals("getIdPessoa") ){
					 
					 Object [] argList = null;
					 
					 //Invoca��o do m�todo por reflection
					 Object objRetornado = method.invoke(pessoa, argList);
					 
					 //Transforma�ao do valor retornado pelo m�todo em um String
					 //visando facilitar as compara��es com a utliza��o do equals
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
