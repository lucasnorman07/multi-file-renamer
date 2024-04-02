package menu;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        super();
        setLayout(new FlowLayout());

        JButton[] elements = {
            new JButton("Add prefix"),
            new JButton("Add suffix"),
            new JButton("search/replace")
        };
        for (JButton element : elements) {
            add(element);
        }
    }
}
