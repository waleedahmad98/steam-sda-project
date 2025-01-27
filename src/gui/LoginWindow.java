package gui;

import bl.interface_gui;
import dal.DAL;
import dal.LocalDAL;
import dal.MongoDAL;
import models.User;

import javax.swing.*;


// This window shows the login panel
public class LoginWindow implements interface_gui {
    private JFrame parent;
    private JPanel root;
    private JLabel logo;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton noAccountSignUpButton;
    private JCheckBox dbCheckBox;


    public LoginWindow(JFrame parent) {
        this.parent = parent;

        loginButton.addActionListener(e -> validateLogin());
        noAccountSignUpButton.addActionListener(e -> goToSignUp());
        dbCheckBox.addActionListener(e -> {

            if(dbCheckBox.isSelected()) // sets DB instance
                DAL.setInstance(new LocalDAL());
            else
                DAL.setInstance(new MongoDAL());

        });
    }

    public JPanel getRoot() {
        return root;
    }

    public void validateLogin(){
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (usernameText ==  null || passwordText == null)
        {
            System.out.println("fields cannot be empty");
            //TODO: add this to label
            return;
        }
        User u = DAL.getInstance().getUser(usernameText, passwordText);

        if (u == null) {
            System.out.println("not found");
        } else {
            goToLibrary(u);
        }

    }

    public void goToSignUp(){
        this.parent.setContentPane(new SignUpWindow(this.parent, this).getRoot());
        this.parent.setVisible(true);
    }

    public void goToLibrary(User u){
        this.parent.setSize(1280, 720);
        this.parent.setContentPane(new LibraryWindow(this.parent, u).getRoot());
        this.parent.setVisible(true);
    }

}
