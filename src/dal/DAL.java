package dal;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import models.Game;
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
    } // adds user to the database

    public User getUser(String username, String password) { // gets single user from the database
        Document findDoc = new Document("username", username).append("password", password);
        for (User u : db.getCollection("users", User.class).find(findDoc)) {
            return u;
        }
        return null;
    }

    public ArrayList<User> getAllUsers() { // gets all users from the database
        ArrayList<User> arr = new ArrayList<User>();
        for (User u : db.getCollection("users", User.class).find()) {
            arr.add(u);
        }
        return arr;
    }

    public void setFunds(String username, double amount){
        //Document findDoc = new Document("username", u.getUsername());
        //db.getCollection("users", User.class).updateOne(findDoc, u);

        Document query = new Document();
        query.append("username",username);
        Document setData = new Document();
        setData.append("wallet", amount);
        Document update = new Document();
        update.append("$set", setData);
        //To update single Document
        db.getCollection("users").updateOne(query, update);
        //collection.updateOne(query, update);
    }

    public ArrayList<Game> getAllGames() { // gets all games from the database
        ArrayList<Game> arr = new ArrayList<Game>();
        for (Game g : db.getCollection("store", Game.class).find()) {
            arr.add(g);
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
