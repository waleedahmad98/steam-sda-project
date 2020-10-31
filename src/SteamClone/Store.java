package SteamClone;

import java.util.ArrayList;

public class Store implements GetStore {
    private ArrayList<Game> games = new ArrayList<Game>();

    @Override
    public Game getAllFromDatabase() {
        Game wow = new Game();
        return wow;
    }
}
