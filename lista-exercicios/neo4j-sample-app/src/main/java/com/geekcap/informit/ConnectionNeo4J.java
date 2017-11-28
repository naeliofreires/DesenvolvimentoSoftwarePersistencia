package com.geekcap.informit;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

public class ConnectionNeo4J {


	private static Driver driver;
	private static String URL = "bolt://localhost:7687";
	private static String USER = "neo4j";
	private static String PASSWORD = "8856";
	
	public ConnectionNeo4J() {
		setDriver(GraphDatabase.driver(URL, AuthTokens.basic(USER, PASSWORD))); 
		System.out.println("Conexão: OK!");
	}

	public static Driver getDriver() {
		return driver;
	}

	private static void setDriver(Driver driver) {
		ConnectionNeo4J.driver = driver;
	}
	
	public void adicionarNode(Node node){

		Session session = getDriver().session();

		try {
			session.run("CREATE (a:Person { name: " + node.getTitulo() + "})");
			System.out.println("CREATE: OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void criarRelacioanamento(int node1, int node2, String relaciomento, float peso){
		
		Session session = getDriver().session();

		try {
			session.run("MATCH (a:Person { name: " + node1 +"}) "
					+ "MATCH (n:Person { name: " + node2 + "}) "
							+ " CREATE (a)-[:"+ relaciomento +" { peso: "+ peso +"}]->(n) ");
			System.out.println("criarRelacioanamento: OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//	MATCH (node1:Person { name: node1 }),(node2:Person { name: node2 }), p = shortestPath((node1)-[*..15]-(node2)) RETURN p");
	public void caminhoMinimo(int node1, int node2){
		
		Session session = getDriver().session();

		try {
			StatementResult result = 
					session.run("MATCH (node1:Person { name: "+ node1 +" }),(node2:Person { name: "+ node2 +" }), p = shortestPath((node1)-[*..15]-(node2)) RETURN p");
			
			System.out.println("\nO caminho minimo encontrado foi:");
			
			while(result.hasNext())
				System.out.println(result.next());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	MATCH (ms:Person { name: 1 }),(cs:Person { name: 2 }), p = shortestPath((ms)-[:KNOWS*]-(cs)) RETURN p;
	public void caminhosPossiveis(int node1, int node2){
		
		Session session = getDriver().session();

		try {
			StatementResult result = 
					session.run("MATCH (ms:Person { name: "+ node1 +" }),(cs:Person { name: "+ node2 +" }), p = shortestPath((ms)-[:KNOWS*]-(cs)) RETURN p;");
			
			System.out.println("\nOs caminhos possiveis encontrado foi:");
			
			while(result.hasNext())
				System.out.println(result.next());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
