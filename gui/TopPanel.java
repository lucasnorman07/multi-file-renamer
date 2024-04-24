package gui;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import file_renaming.FileController;
import gui.side_panels.PrefixSidePanel;
import gui.side_panels.SearchReplaceSidePanel;
import gui.side_panels.SuffixSidePanel;

public class TopPanel extends JPanel {
    public TopPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // define all of the buttons in an array to be able to loop over them later
        JButton[] elements = {
            new JButton("Select files"),
            new JButton("Add prefix"),
            new JButton("Add suffix"),
            new JButton("search/replace")
        };

        // add an action listener to each button
        elements[0].addActionListener(e -> FileController.getInstance().browseFiles());
        elements[1].addActionListener(e -> MainMenu.getInstance().changeSidePanel(PrefixSidePanel.getInstance()));
        elements[2].addActionListener(e -> MainMenu.getInstance().changeSidePanel(SuffixSidePanel.getInstance()));
        elements[3].addActionListener(e -> MainMenu.getInstance().changeSidePanel(SearchReplaceSidePanel.getInstance()));

        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
            // add spacing between the buttons (therefore skip the last element)
            if (i < elements.length - 1) {
                add(Box.createHorizontalStrut(5));
            }
        }
    }
}
