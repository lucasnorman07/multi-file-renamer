package menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
    public Panel() {
        JLabel label = new JLabel("TITLE");
        JButton button = new JButton("Add prefix");
        button.setSize(30, 20);
        add(label);
        add(button);
        
    }
}
