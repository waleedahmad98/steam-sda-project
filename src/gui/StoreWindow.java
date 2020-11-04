package gui;

import dal.DAL;
import models.Game;

import javax.swing.*;
import java.util.ArrayList;

public class StoreWindow {
    private JFrame parent;
    private JPanel root;
    private JButton libraryButton;

    public StoreWindow(JFrame parent, LibraryWindow referrer) {
        libraryButton.addActionListener(e -> {
            parent.setContentPane(referrer.getRoot());
        });
        ArrayList<Game> games = DAL.getInstance().getAllGames();
        System.out.println(games.get(0).getName());
        //TODO: Display

    }

    public JPanel getRoot() {
        return root;
    }

}
