package gui;

import javax.swing.JFrame;

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
        setResizable(false);
        setTitle("Multi File Renamer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(MainMenu.getInstance());
        pack();
    }
}
