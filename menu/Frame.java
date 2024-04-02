package menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {
    private MainMenu panel; 

    public Frame(String title, MainMenu panel) {
        setVisible(true);
        setTitle(title);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = panel;
        add(panel);
        // setFocusable(true);
        pack();
    }
}
