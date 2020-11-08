package gui;

import dal.DAL;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LibraryWindow {

    private User user;

    private JFrame parent;
    private JPanel root;
    private JButton storeButton;
    private JButton friendsButton;
    private JLabel walletAmnt;
    private JButton addFundsButton;
    private JButton logOutButton;


    LibraryWindow(JFrame parent, User user){
//        ArrayList<User> users =  DAL.getInstance().getAllUsers();
//        for (int i=0; i<users.size();i++){
//            if (users.get(i).getUsername().equals(Main.sessionUser))
        this.user = user;
        this.parent = parent;

        walletAmnt.setText(String.valueOf(user.getWallet()));

        storeButton.addActionListener(e -> {
            this.parent.setContentPane(new StoreWindow(this.parent, this).getRoot());
            this.parent.setVisible(true);
        });
        addFundsButton.addActionListener(e -> {
            JFrame jf = new JFrame("Add Funds");
            jf.setSize(300, 200);
            jf.setContentPane(new FundsWindow(this.user, this.walletAmnt).getRoot());
            jf.setVisible(true);
        });
    }

    public JPanel getRoot() {
        return root;
    }
}
