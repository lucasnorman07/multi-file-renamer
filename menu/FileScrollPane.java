package menu;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class FileScrollPane extends JScrollPane {
    private JPanel buttonPanel;

    // to keep track of the file buttons that are currently displayed
    private ArrayList<JButton> currentFileButtons = new ArrayList<>();

    public FileScrollPane(JPanel buttonPanel) {
        super(buttonPanel);
        this.buttonPanel = buttonPanel;
        // add a grid layout so that the width of each button is adjusted to fit the buttonPanel (0 means any number of rows)
        buttonPanel.setLayout(new GridLayout(0, 1));

        // add a default message when the program opens
        JLabel placeholder = new JLabel("Select files to start...");
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(placeholder);

        setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width + UIManager.getInt("ScrollBar.width"), 200));
        // set the scroll bar policy
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void updateFiles(String... files) {
        // clear the old file buttons
        buttonPanel.removeAll();
        currentFileButtons.clear();
        // add the new file buttons to the currentFileButtons and buttonPanel
        for (String file : files) {
            JButton button = new JButton(file);
            currentFileButtons.add(button);
            buttonPanel.add(button);
        }

        // fill the available space to make the buttons not stretch
        for (int i = 0; i < 8 - files.length; i++) {
            buttonPanel.add(new JLabel());
        }
    }
}
