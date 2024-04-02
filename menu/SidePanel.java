package menu;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    public SidePanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JComponent[] elements = {
            new JTextField("Type prefix"),
            new JButton("Apply prefix")
        };
        for (JComponent element : elements) {
            element.setPreferredSize(new Dimension(60, 30));
            add(element);
        }
    }
}
