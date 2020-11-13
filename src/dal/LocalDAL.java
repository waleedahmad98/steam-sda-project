package dal;

import bl.interface_db;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import models.Game;
import models.GamePathMapping;
import models.User;
import org.bson.types.ObjectId;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

// LOCAL DATA ACCESS LAYER.
// Implements all the functions of the interface and overrides them to work with a local text database (JSON files)
public class LocalDAL implements interface_db {

    @Override
    public void addUser(User u) throws IOException {
        try {
            Gson g = new GsonBuilder().create();

            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));
            userArray.add(u);
            FileWriter writer = new FileWriter("src/dal/json-db/users.json");
            g.toJson(userArray, writer);
            writer.close();
            System.out.println(userArray);

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public ArrayList<User> getAllUsers() {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));
            return userArray;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void setFunds(String username, double amount) {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));

            for (User u:userArray){
                if(u.getUsername().equals(username) )
                    u.setWallet(amount);
            }

            FileWriter writer = new FileWriter("src/dal/json-db/users.json");
            g.toJson(userArray, writer);
            writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<Game> getAllGames() {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/store.json"));
            Game[] gameFile = g.fromJson(content, Game[].class);
            ArrayList<Game> gameArray = new ArrayList<Game>(Arrays.asList(gameFile));

            JsonParser p = new JsonParser();
            JsonElement js = p.parse(content);

            for (int i=0; i<gameArray.size();i++) {
                JsonElement el = js.getAsJsonArray().get(i).getAsJsonObject().get("_id");
                gameArray.get(i).setId(new ObjectId(el.getAsString()));
                //System.out.println(gameArray.get(i).getExecPath());
            }
            return gameArray;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;

    }

    @Override
    public ArrayList<Game> getGamesByIds(ArrayList<GamePathMapping> mappings) {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/store.json"));
            Game[] gameFile = g.fromJson(content, Game[].class);
            ArrayList<Game> gameArray = new ArrayList<Game>(Arrays.asList(gameFile));
            ArrayList <Game> toReturn = new ArrayList<Game>();

            JsonParser p = new JsonParser();
            JsonElement js = p.parse(content);

            for (int i=0; i<gameArray.size();i++) {
                JsonElement el = js.getAsJsonArray().get(i).getAsJsonObject().get("_id");
                gameArray.get(i).setId(new ObjectId(el.getAsString()));

            }

            for (GamePathMapping map : mappings) {
                for (Game game: gameArray ){
                    if(game.getId().equals(map.getId())){
                        game.setExecPath(map.getPath());
                        toReturn.add(game);
                    }
                }
            }
            return toReturn;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void addGameToLibrary(String username, GamePathMapping game) {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));

            for (User u:userArray){
                if(u.getUsername().equals(username)){
                    ArrayList<GamePathMapping> newArr = u.getGames_library();
                    newArr.add(game);
                    u.setGames_library(newArr);

                }
            }
            FileWriter writer = new FileWriter("src/dal/json-db/users.json");
            g.toJson(userArray, writer);
            writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }


    }

    @Override
    public void addGameToAccount(String username, ObjectId id) {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));

            for (User u:userArray){
                if(u.getUsername().equals(username)) {
                    ArrayList<ObjectId> newArr = u.getGames_bought();
                    //System.out.println(id);
                    newArr.add(id);
                    u.setGames_bought(newArr);
                }
            }
            FileWriter writer = new FileWriter("src/dal/json-db/users.json");
            g.toJson(userArray, writer);
            writer.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void decreaseFunds(String username, double amount) {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));

            for (User u:userArray){
                if(u.getUsername().equals(username) )
                    u.setWallet(amount);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void removeGame(Game game, User user) {

    }

    @Override
    public User getUser(String username, String password) {
        try {
            Gson g = new GsonBuilder().create();
            String content = Files.readString(Path.of("src/dal/json-db/users.json"));
            User[] userFile = g.fromJson(content, User[].class);
            ArrayList<User> userArray = new ArrayList<User>(Arrays.asList(userFile));

            for (User u:userArray){
                if(u.getUsername().equals(username) && u.getPassword().equals(password))
                    return u;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;

    }
}
