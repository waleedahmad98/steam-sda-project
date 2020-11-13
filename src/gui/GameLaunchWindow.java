package gui;

import dal.DAL;
import models.Game;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// This window prompts the user to launch or remove the game.
public class GameLaunchWindow {
    private JButton launchButton;
    private JPanel root;
    private JButton removeButton;
    private JLabel name;

    public GameLaunchWindow(Game game, User user){
        name.setText(game.getName());
        name.setForeground(Color.white);
        launchButton.addActionListener(e -> {
            try {
                Process process = new ProcessBuilder(game.getExecPath()).start();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        removeButton.addActionListener(e -> {
            DAL.getInstance().removeGame(game, user);
            JOptionPane.showMessageDialog(this.root,game.getName()+" removed from Library!");
        });
    }

    public JPanel getRoot(){
        return root;
    }
}
