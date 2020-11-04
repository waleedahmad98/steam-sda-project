package models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class User {

    public User() {}

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
        setWallet(0.0);
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

    private String username;
    private String password;
    private double wallet;
}
