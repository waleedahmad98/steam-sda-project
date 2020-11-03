import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptionDefaults;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

// DATA ACCESS LAYER
// we're going to be using mongoCloud database, which is set up in this file. This class only links the Data Layer with the Business Layer. It serves only that purpose.
public class DAL {

    private static final String MONGO_URI = "mongodb+srv://waleed:G5yweyucZtCca23@cluster0.0o9s1.mongodb.net/steam?retryWrites=true&w=majority";

    public void addUser(User u) {
        db.getCollection("users", User.class).insertOne(u);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> arr = new ArrayList<User>();
        for (User u : db.getCollection("users", User.class).find()) {
            arr.add(u);
        }
        return arr;
    }

    // database structure methods
    private void createIndexes() {
        db.getCollection("users", User.class).createIndex(new Document("username", 1), new IndexOptions().unique(true));
    }

    // singleton methods and privates

    private static DAL instance = null;

    static {
        instance = new DAL();
    }

    public static DAL getInstance() {
        return instance;
    }

    private DAL() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URI));
        db = mongoClient.getDatabase("steam").withCodecRegistry(pojoCodecRegistry);
        createIndexes();
    }

    private MongoDatabase db;
}
