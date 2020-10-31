package SteamClone;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JPanel panel1;


    public MainScreen() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = textField1.getText().toString();
                JOptionPane.showMessageDialog(null, userName);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainScreen"); //Just the window name.
        frame.setContentPane(new MainScreen().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
