package models;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class User {

    public User() {}

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        setWallet(0.0);
        games_library = new ArrayList<GamePathMapping>();
        games_bought = new ArrayList<ObjectId>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getWallet(){ return wallet; }

    public void setWallet(double wallet){ this.wallet=wallet; }

    public void setGames_library(ArrayList<GamePathMapping> games_library) {
        this.games_library = games_library;
    }

    public ArrayList<GamePathMapping> getGames_library() {
        return games_library;
    }


    public ArrayList<ObjectId> getGames_bought() {
        return games_bought;
    }

    public void setGames_bought(ArrayList<ObjectId> games_bought) {
        this.games_bought = games_bought;
    }

    private String username;
    private String password;
    private double wallet;
    private ArrayList<GamePathMapping> games_library;
    private ArrayList<ObjectId> games_bought;

}
