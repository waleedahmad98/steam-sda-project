package dal;

import bl.interface_db;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import models.Game;
import models.GamePathMapping;
import models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import java.util.ArrayList;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

// MONGO DATA ACCESS LAYER
// Implements all the functions of the interface and overrides them to work with MongoDB Atlas
public class MongoDAL implements interface_db {

    private static final String MONGO_URI;

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

    public void addGameToLibrary(String username, GamePathMapping game){
        Document query = new Document();
        query.append("username",username);
        Document setData = new Document();
        setData.append("games_library",game);
        Document update = new Document();
        update.append("$push", setData);
        //To update single Document
        db.getCollection("users").updateOne(query, update);

    }

    public void addGameToAccount(String username, ObjectId id){
        Document query = new Document();
        query.append("username",username);
        Document setData = new Document();
        setData.append("games_bought",id);
        Document update = new Document();
        update.append("$push", setData);
        //To update single Document
        db.getCollection("users").updateOne(query, update);

    }


    public ArrayList<Game> getAllGames() { // gets all games from the database
        ArrayList<Game> arr = new ArrayList<Game>();
        for (Game g : db.getCollection("store", Game.class).find()) {
            arr.add(g);
        }
        return arr;
    }

    public ArrayList<Game> getGamesByIds(ArrayList<GamePathMapping> mappings){
        ArrayList<Game> games =  new ArrayList<>();
        // TODO: add functionality here
        for (GamePathMapping maps:mappings) {
            Document findDoc = new Document("_id", maps.getId());
            for (Game g : db.getCollection("store", Game.class).find(findDoc)) {
                System.out.println(g.getName());
                g.setExecPath(maps.getPath()); // this
                games.add(g);
            }
        }
        // TODO:
        return games;
    }

    public void decreaseFunds(String username,double amount){
        Document query = new Document();
        query.append("username",username);
        Document setData = new Document();
        setData.append("wallet", amount);
        Document update = new Document();
        update.append("$set", setData);
        //To update single Document
        db.getCollection("users").updateOne(query, update);
    }

    public void removeGame(Game game, User user){
        Document query = new Document();
        query.append("username",user.getUsername());
        Document setData = new Document();
        setData.append("games_library", new GamePathMapping(game.getId(), game.getExecPath()));
        Document update = new Document();
        update.append("$pull", setData);
        //To update single Document
        db.getCollection("users").updateOne(query, update);
    }

    // database structure methods
    private void createIndexes() {
        db.getCollection("users", User.class).createIndex(new Document("username", 1), new IndexOptions().unique(true));
    }

    public MongoDAL() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URI));
        db = mongoClient.getDatabase("steam").withCodecRegistry(pojoCodecRegistry);
        createIndexes();
    }

    private MongoDatabase db;


}
