package gui;

import com.mongodb.MongoWriteException;
import dal.DAL;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SignUpWindow { // the class associated with the bind window

    private LoginWindow referrer;

    private JFrame parent;
    private JButton signUpButton;
    private JPanel root;
    private JLabel logo;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel prompt;
    private JButton showAllUsersButton;
    private JButton alreadyHaveAnAccountButton;


    public SignUpWindow(JFrame parent, LoginWindow referrer) {
        this.referrer = referrer;
        this.parent = parent;
        signUpButton.addActionListener(e -> getFields());
        showAllUsersButton.addActionListener(e -> showAllUsers());
        alreadyHaveAnAccountButton.addActionListener(e -> goToLogin());
    }

    private void showAllUsers() {
        ArrayList<User> users = DAL.getInstance().getAllUsers();
        StringBuilder builder = new StringBuilder();
        for (User u : users) {
            builder.append(u.getUsername());
            builder.append(", ");
        }

        JOptionPane.showMessageDialog(this.root,
                builder.toString());
    }

    public void getFields(){
        try {
            User u = new User(usernameField.getText(), passwordField.getText());
            DAL.getInstance().addUser(u);
            this.prompt.setForeground(Color.GREEN);
            this.prompt.setText("User registered!");
        } catch (MongoWriteException e) {
            this.prompt.setForeground(Color.RED);
            this.prompt.setText("User already exists!");
        }
    }

    public JPanel getRoot() {
        return root;
    }

    public void goToLogin(){
        this.parent.setContentPane(referrer.getRoot());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
