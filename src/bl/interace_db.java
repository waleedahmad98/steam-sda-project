package bl;

import models.Game;
import models.User;

import java.util.ArrayList;

public interface interace_db {

    public void addUser(User u);

    public ArrayList<User> getAllUsers();

    public void setFunds(String username, double amount);

    public ArrayList<Game> getAllGames();

}
