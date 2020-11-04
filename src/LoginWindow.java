import models.User;

import javax.swing.*;
import java.util.ArrayList;

public class LoginWindow {
    private JPanel root;
    private JLabel logo;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton noAccountSignUpButton;


    public LoginWindow() {
        loginButton.addActionListener(e -> validateLogin());
        noAccountSignUpButton.addActionListener(e -> goToSignUp());
    }
    public JPanel getRoot() {
        return root;
    }

    public void validateLogin(){
        String usernameText = username.getText();
        String passwordText = password.getText();
        if (usernameText.equals(""))
        {
            System.out.println("fields cannot be empty");
            //TODO: add this to label
            return;
        }

        ArrayList<User> users = DAL.getInstance().getAllUsers();
        for (int i=0; i< users.size();i++){
            if (users.get(i).getUsername().equals(usernameText) && users.get(i).getPassword().equals(passwordText)){
                // TODO: main menu
                Main.sessionUser = users.get(i).getUsername();
                System.out.println(Main.sessionUser);
                goToLibrary();
                return;
            }
        }
        // TODO: add this to label
        System.out.println("not found");

    }

    public void goToSignUp(){
        Main.jf.setContentPane(new SignUpWindow().getRoot());
        Main.jf.setVisible(true);
    }

    public void goToLibrary(){
        Main.jf.setSize(1280, 720);
        Main.jf.setContentPane(new LibraryWindow().getRoot());
        Main.jf.setVisible(true);
    }

}
