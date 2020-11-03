import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame("Steam - thora sastay main"); // initiate the frame
        jf.setSize(400, 600); // set window size - change later
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(new SignUpWindow().getRoot()); // initial content pane to show
        jf.setVisible(true);
    }
}
