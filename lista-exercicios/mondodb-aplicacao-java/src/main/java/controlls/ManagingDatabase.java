package controlls;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
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
    public void consulta01(){
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

    /**
     * Obter os títulos dos livros e os nomes das suas respectivas editoras. Os resultados devem ser exibidos em ordem
     * crescente pelo título do livro. Os livros que não possuem editora também devem aparecer na listagem.*/

    public void consulta02(){
        try {
            AggregateIterable<Document> aggregateIterable = database.getCollection("livros").aggregate(Arrays.asList(
                    new Document( "$sort", new Document("titulo", 1)),
                    new Document("$lookup", new Document("from", "editoras")
                            .append("localField", "id_editora")
                            .append("foreignField", "id")
                            .append("as", "editora")),
                    new Document("$project", new Document("_id", 0)
                            .append("titulo", 1)
                            .append("valor", 1)
                            .append("editora.nome", 1))
            ));

            aggregateIterable.forEach(new Block<Document>() {
                public void apply(Document document) {
                    System.out.println(document.toJson());
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
