package controlls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ManagingDatabase {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public ManagingDatabase() {
        mongoClient = new MongoClient();
        this.database = mongoClient.getDatabase("lista08");
    }

    public void InserirDocumento(String name_collection, ArrayList<Object> objects){
        ObjectMapper mapper = new ObjectMapper();
        try {
            for ( Object l: objects ) {
                Document document = Document.parse(mapper.writeValueAsString(l));
                database.getCollection(name_collection).insertOne(document);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void showCollection(String coll){
        try {
            FindIterable<Document> iterable = database.getCollection(coll).find();
            iterable.forEach(new Block<Document>() {
                public void apply(Document document) {
                    System.out.println(document);
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void buscarLivrosPorTitulo(String titulo){
        try {
            DBObject query = QueryBuilder
                    .start("titulo")
                    .is(titulo)
                    .get();

            FindIterable<Document> iterable = database.getCollection("livros").find((Bson) query);

            iterable.forEach(new Block<Document>() {
                public void apply(Document document) {
                    System.out.println(
                            "Titulo: " + document.getString("titulo") + "\n" +
                                    "Publicação: " + document.getInteger("ano_publicacao") + "\n" +
                                    "R$: " + document.getDouble("valor")
                    );
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void buscarLivroQueCotenhaEsteNome(String nome){
        try {
            DBObject query = QueryBuilder
                    .start("titulo")
                    .regex(Pattern.compile(nome))
                    .get();

            FindIterable<Document> iterable =  database.getCollection("livros").find((Bson) query);

            iterable.forEach(new Block<Document>() {
                public void apply(Document document) {
                    System.out.println(
                            "Titulo: " + document.getString("titulo") + "\n" +
                            "Publicação: " + document.getInteger("ano_publicacao") + "\n" +
                            "R$: " + document.getDouble("valor") + "\n"
                    );
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Obter o nome da editora, a quantidade total de livros por editora e o valor total (qtd_estoque * valor)
     * dos livros para cada editora. Somente considerar os livros publicados a partir de 2010.*/
    public void consulta1(){
        try {
            DBObject query = QueryBuilder.start("ano_publicacao").greaterThanEquals(2010).get();
            FindIterable<Document> iterable =  database.getCollection("livros").find((Bson) query);
            iterable.forEach(new Block<Document>() {
                public void apply(Document document) {
                    System.out.println(
                            "Titulo: " + document.getString("titulo") + "\n" +
                            "Publicação: " + document.getInteger("ano_publicacao") + "\n" +
                            "Valor Unitario R$: " + document.getDouble("valor") + "\n" +
                            "Valor total R$: " + document.getDouble("valor") * document.getInteger("qtd_estoque") + "\n"
                    );
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
