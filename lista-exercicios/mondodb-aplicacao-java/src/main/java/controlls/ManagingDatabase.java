package controlls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
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
     * Obter a quantidade total de livros disponíveis em estoque com valor unitário abaixo de R$ 100,00.*/
    public void consulta1(){
        try {

            AggregateIterable<Document> output = database.getCollection("livros").aggregate(Arrays.asList(
                    new Document("$match", new Document("valor", new Document("$lt", 100))),
                    new Document("$group", new Document("_id", null).append("QuantidadeDisponivel", new Document("$sum", "$qtd_estoque")))
            ));

            output.forEach(new Block<Document>() {
                public void apply(Document document) {
                    System.out.println(document.toJson());
                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
