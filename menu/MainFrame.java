package menu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static MainFrame _instance;

    public static synchronized MainFrame getInstance() {
        if (_instance == null) {
            _instance = new MainFrame();
        }
        return _instance;
    }
    
    // make private to not allow other classes to create an instance
    private MainFrame() {
        setVisible(true);
        setTitle("Multi File Renamer");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(MainMenu.getInstance());
        // setFocusable(true);
        pack();
    }
}
