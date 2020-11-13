package gui;

import dal.DAL;
import models.Game;
import models.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

// This windows loads the library games.
public class LibraryWindow {

    private User user;
    private JFrame parent;
    private JPanel root;
    private JButton storeButton;
    private JLabel walletAmnt;
    private JButton addFundsButton;
    private JButton logOutButton;
    private JPanel container;
    private JButton refreshButton;
    private JLabel name;


    LibraryWindow(JFrame parent, User user){
//        ArrayList<User> users =  DAL.getInstance().getAllUsers();
//        for (int i=0; i<users.size();i++){
//            if (users.get(i).getUsername().equals(bl.Main.sessionUser))
        this.user = user;
        this.parent = parent;
        System.out.println(user.getUsername());
        name.setText(user.getUsername() + "'s Library");
        name.setForeground(Color.white);
        storeButton.addActionListener(e -> {
            this.parent.setContentPane(new StoreWindow(this.user, this.parent, this).getRoot());
            this.parent.setVisible(true);
        });
        addFundsButton.addActionListener(e -> {
            JFrame jf = new JFrame("Add Funds");
            jf.setSize(300, 200);
            jf.setContentPane(new FundsWindow(this.user, this.walletAmnt).getRoot());
            jf.setVisible(true);
        });
        logOutButton.addActionListener(e -> {
            this.parent.setSize(400, 600); // set window size - change later
            //this.parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.parent.setContentPane(new LoginWindow(this.parent).getRoot());
            this.parent.setVisible(true);

        });
        refreshButton.addActionListener(e -> {
            container.removeAll();
            this.loadLibrary();
        });

        this.loadLibrary();
    }

    private void loadLibrary() {
        user = DAL.getInstance().getUser(user.getUsername(), user.getPassword());
        walletAmnt.setText(String.valueOf(user.getWallet()));

        ArrayList<Game> games = DAL.getInstance().getGamesByIds(user.getGames_library());

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
                    JFrame jf = new JFrame();
                    jf.setSize(200, 200); // set window size - change later
                    //this.parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    jf.setContentPane(new GameLaunchWindow(game, user).getRoot());
                    jf.setVisible(true);

                });

                container.updateUI();

            } catch(Exception ex){
                System.out.println(ex);
            }
        }
    }

    public JPanel getRoot() {
        return root;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
