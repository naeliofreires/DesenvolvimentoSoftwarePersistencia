package controlls;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.List;

public class ManagingDatabase {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public ManagingDatabase() {
        mongoClient = new MongoClient();
        this.database = mongoClient.getDatabase("lista08");
    }

    public void InserirDocumento(String name_collection, Document document){
        try {
            database.getCollection(name_collection).insertOne(document);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
