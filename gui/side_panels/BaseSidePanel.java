package gui.side_panels;

import javax.swing.*;
import java.awt.*;

public class BaseSidePanel extends JPanel {
    public BaseSidePanel(JComponent... elements) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (JComponent element : elements) {
            element.setPreferredSize(new Dimension(75, 30));
            add(element);
        }
    }
}
