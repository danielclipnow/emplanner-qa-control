import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoDB {
    public static String databaseName = "seleniumTest";
    public static String collectionName = "user";

    public static String username = ConfProperties.getProperty("login");
    public static String password = ConfProperties.getProperty("password");

    public static void mongoDBSetup(){


        // Setup Mongo DB Connection

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document doc = new Document("username", username)
                .append("password",password)
                .append("name", 1111);
        // Create Document
        collection.insertOne(doc);


        password = doc.get("password").toString();
        username = doc.get("username").toString();
        System.out.println(password);
        System.out.println(username);
    }
}
