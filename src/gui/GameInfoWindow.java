package gui;

import dal.DAL;
import models.Game;
import models.GamePathMapping;
import models.User;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;

public class GameInfoWindow {
    private JPanel root;
    private JButton buyButton;
    private JButton downloadButton;
    private JLabel priceLabel;
    private JLabel display;
    private User user;
    public GameInfoWindow(User user, Game game, Image img){
        this.user = user;
        img.getScaledInstance(532, 300, Image.SCALE_SMOOTH);
        display.setIcon(new ImageIcon(img));
        priceLabel.setForeground(Color.white);
        priceLabel.setText(String.valueOf(game.getPrice()));

        if (this.user.getWallet() >= game.getPrice()) {
            buyButton.addActionListener(e -> {
                DAL.getInstance().addGameToAccount(this.user.getUsername(), game.getId());
                JOptionPane.showMessageDialog(this.root, game.getName() + " has been added to your account. You may download this game now!");
            });

            DAL.getInstance().decreaseFunds(user.getUsername(), this.user.getWallet()-game.getPrice());

            // TODO: decrease wallet funds
        } else {
            JOptionPane.showMessageDialog(this.root, "You do not have enough funds to buy this game!");
        }

        downloadButton.addActionListener(e -> {
            // TODO: get path here without hard coding it
            int found = 0;
            this.user = DAL.getInstance().getUser(user.getUsername(), user.getPassword());

            for (ObjectId id : this.user.getGames_bought()) {
                System.out.println(id);
                System.out.println(game.getId());
                if (id.equals(game.getId())){
                    String path = "D:\\Games\\Among Us\\Among Us.exe";
                    DAL.getInstance().addGameToLibrary(user.getUsername(), new GamePathMapping(game.getId(), path));
                    JOptionPane.showMessageDialog(this.root, game.getName() + " added to Library!");
                    found = 1;
                }
            }
            if (found == 0) {JOptionPane.showMessageDialog(this.root, "You haven't purchased this game yet!");}
        });

    }

    public JPanel getRoot(){
        return root;
    }

}
