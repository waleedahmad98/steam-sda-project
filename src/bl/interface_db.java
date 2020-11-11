package bl;

import models.Game;
import models.GamePathMapping;
import models.User;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public interface interface_db {

    public void addUser(User u);

    public ArrayList<User> getAllUsers();

    public void setFunds(String username, double amount);

    public ArrayList<Game> getAllGames();

    public ArrayList<Game> getGamesByIds(ArrayList<GamePathMapping> mappings);

    public void addGameToLibrary(String username, GamePathMapping game);

    public void addGameToAccount(String username, ObjectId id);

    public void decreaseFunds(String username, double amount);

    public void removeGame(Game game, User user);
}
