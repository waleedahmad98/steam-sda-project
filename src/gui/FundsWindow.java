package gui;

import dal.DAL;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class FundsWindow {
    private JPanel root;
    private JTextField textField1;
    private JButton addButton;

    public FundsWindow(User user, JLabel variable) {
        addButton.addActionListener(e -> {
            String input = textField1.getText();
            //TODO: Validation
            int parsed = parseInt(input);
            double newAmount = Double.parseDouble(variable.getText()) + parsed;
            variable.setText(String.valueOf(newAmount));
            System.out.println(user.getUsername());
            System.out.println(newAmount);
            DAL.getInstance().setFunds(user.getUsername(), newAmount);
            JOptionPane.showMessageDialog(this.root, String.valueOf(parsed));
        });
    }

    public JPanel getRoot(){ return root; }
}
