package gui.side_panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class BaseSidePanel extends JPanel {
    public BaseSidePanel(JComponent... elements) {
        super();
        // set the size of the side panel (the height will affect the height of the main panel) 
        setPreferredSize(new Dimension(125, 216));
        // create an inner JPanel with a grid layout to be able to set the size of the outer panel
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1));
        add(innerPanel);

        // add all of the elements to the inner panel with a specific size
        for (JComponent element : elements) {
            element.setPreferredSize(new Dimension(125, 30));
            innerPanel.add(element);
        }
    }
}
