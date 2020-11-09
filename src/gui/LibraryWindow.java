package gui;

import models.User;

import javax.swing.*;

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
//            if (users.get(i).getUsername().equals(bl.Main.sessionUser))
        this.user = user;
        this.parent = parent;
        System.out.println(user.getUsername());
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
        logOutButton.addActionListener(e -> {
            this.parent.setSize(400, 600); // set window size - change later
            this.parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.parent.setContentPane(new LoginWindow(this.parent).getRoot());
            this.parent.setVisible(true);

        });
    }

    public JPanel getRoot() {
        return root;
    }
}
