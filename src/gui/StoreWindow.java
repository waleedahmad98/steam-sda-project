package gui;

import dal.DAL;
import models.Game;
import models.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class StoreWindow {
    private JFrame parent;
    private JPanel root;
    private JButton libraryButton;
    private JPanel container;
    private User user;
    private void loadStore() {
        ArrayList<Game> games = DAL.getInstance().getAllGames();

        for (Game game : games) {
            try {
                JButton newButton = new JButton();
                newButton.setText(game.getName());
                newButton.setBackground(Color.BLUE);
                newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
                newButton.setHorizontalTextPosition(SwingConstants.CENTER);
                container.add(newButton);
                container.updateUI();
                Image img = ImageIO.read(new URL(game.getLink()))
                        .getScaledInstance(266, 150, Image.SCALE_SMOOTH);
                newButton.setIcon(new ImageIcon(img));

                newButton.addActionListener(e -> {
                    JFrame jf = new JFrame(game.getName());
                    jf.setSize(400, 600); // set window size - change later
                    jf.setContentPane(new GameInfoWindow(this.user, game, img).getRoot());
                    jf.setVisible(true);
                });
                container.updateUI();

            } catch(Exception ex){
                System.out.println(ex);
            }
        }
    }

    public StoreWindow(User user, JFrame parent, LibraryWindow referrer) {
        this.user = DAL.getInstance().getUser(user.getUsername(), user.getPassword());
        libraryButton.addActionListener(e -> {
            parent.setContentPane(referrer.getRoot());
        });

        new Thread(() -> loadStore()).start();
    }

    public JPanel getRoot() {
        return root;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}
