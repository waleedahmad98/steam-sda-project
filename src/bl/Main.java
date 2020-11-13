package bl;

import dal.DAL;
import dal.MongoDAL;
import gui.LoginWindow;

import javax.swing.*;

public class Main {
    public static JFrame jf;

    public static void main(String[] args) {
        try { // set UI to default OS theme
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        DAL.setInstance(new MongoDAL()); // set default DB instance to MongoDB.

        jf = new JFrame("The Steam she tells you not to worry about"); // initiate the frame
        jf.setSize(400, 600); // set window size
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(new LoginWindow(jf).getRoot()); // initial content is the login page
        // so we create its object and send the frame to it so that it may use to change content as well
        jf.setVisible(true);


    }


}
