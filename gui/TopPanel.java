package gui;

import javax.swing.*;

import file_renaming.FileController;
import gui.side_panels.*;

import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JButton[] elements = {
            new JButton("Select files"),
            new JButton("Add prefix"),
            new JButton("Add suffix"),
            new JButton("search/replace")
        };

        // add an action listener to each button
        addActionListeners(elements);

        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
            // add spacing between the buttons (therefor skip the last element)
            if (i < elements.length - 1) {
                add(Box.createHorizontalStrut(5));
            }
        }
    }

    // method to simply abstract away the proccess of adding action listeners
    private void addActionListeners(JButton[] elements) {
        // add an action listener to each element
        elements[0].addActionListener(e -> FileController.getInstance().browseFiles());
        elements[1].addActionListener(e -> MainMenu.getInstance().changeSidePanel(PrefixSidePanel.getInstance()));
        elements[2].addActionListener(e -> MainMenu.getInstance().changeSidePanel(SuffixSidePanel.getInstance()));
        elements[3].addActionListener(e -> MainMenu.getInstance().changeSidePanel(SearchReplaceSidePanel.getInstance()));
    }
}
