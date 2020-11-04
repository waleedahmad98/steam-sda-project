import gui.LoginWindow;

import javax.swing.*;

public class Main {
    public static JFrame jf;

    public static void main(String[] args) {
        jf = new JFrame("Steam - thora sastay main"); // initiate the frame
        jf.setSize(400, 600); // set window size - change later
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(new LoginWindow(jf).getRoot()); // initial content is the login page
        // so we create its object and send the frame to it so that it may use to change content as well
        jf.setVisible(true);
    }

}
