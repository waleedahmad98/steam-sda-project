package bl;

import javax.swing.*;

public interface interface_gui { // this interface is held between the business layer and the gui.
    // The first gui window, the login window, implements it and uses the getRoot function to send to the main
    // where the Jframe is made.
    public JPanel getRoot();
}
