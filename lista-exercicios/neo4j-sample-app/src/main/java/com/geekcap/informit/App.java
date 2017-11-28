package com.geekcap.informit;

public class App {
    public static void main( String[] args ){
    	
    	ConnectionNeo4J connectionNeo4J = new ConnectionNeo4J();
    	
    	for(int i = 0; i <= 5; i++){
    		connectionNeo4J.adicionarNode(new Node(i));
    	}
    	
    	connectionNeo4J.criarRelacioanamento(1, 5, "KNOWS", 1.1f);
    	connectionNeo4J.criarRelacioanamento(5, 1, "KNOWS", 1.1f);
    	
    	connectionNeo4J.criarRelacioanamento(5, 4, "KNOWS", 7.0f);
    	connectionNeo4J.criarRelacioanamento(5, 0, "KNOWS", 2.0f);
    	
    	connectionNeo4J.criarRelacioanamento(0, 3, "KNOWS", 3.0f);
    	connectionNeo4J.criarRelacioanamento(3, 0, "KNOWS", 3.0f);
    	
    	connectionNeo4J.criarRelacioanamento(3, 2, "KNOWS", 4.0f);
    	connectionNeo4J.criarRelacioanamento(2, 3, "KNOWS", 5.0f);
    	
    	connectionNeo4J.criarRelacioanamento(4, 2, "KNOWS", 6.0f);
    	connectionNeo4J.criarRelacioanamento(2, 4, "KNOWS", 6.0f);
    	
    	connectionNeo4J.caminhoMinimo(1, 4);
    	
    	connectionNeo4J.caminhosPossiveis(1, 2);
    }
}
