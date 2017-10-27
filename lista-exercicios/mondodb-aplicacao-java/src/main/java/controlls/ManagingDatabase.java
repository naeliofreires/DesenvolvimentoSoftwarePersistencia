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
        DBObject query = QueryBuilder
                .start("titulo")
                .is(titulo)
                .get();

        FindIterable<Document> iterable = database.getCollection("livros").find((Bson) query);

        iterable.forEach(new Block<Document>() {
            public void apply(Document document) {
                System.out.println(
                        "Titulo: " + document.getString("titulo") + "\n" +
                        "Publicação: " + document.getString("ano_publicacao") + "\n" +
                        "R$: " + document.getDouble("valor")
                );
            }
        });
    }
}
