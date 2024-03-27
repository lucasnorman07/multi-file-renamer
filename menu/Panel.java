package menu;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public Panel() {
        JLabel label = new JLabel("TITLE");
        JButton button = new JButton("Add prefix");
        button.setSize(30, 20);
        add(label);
        add(button);
        
        FileScrollPane scrollPane = new FileScrollPane(new JPanel());
        add(scrollPane);
    }
}
