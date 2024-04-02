package menu;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    public MainMenu() {
        setLayout(new BorderLayout());

        JPanel topPanel = new TopPanel();
        add(topPanel, BorderLayout.NORTH);

        JPanel sidePanel = new SidePanel();
        add(sidePanel, BorderLayout.WEST);
        
        FileScrollPane scrollPane = new FileScrollPane(new JPanel());
        add(scrollPane, BorderLayout.CENTER);
    }
}
