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
        // for (int i = 0; i < 10001; i++) {
        //     buttonPanel.add(new JButton("Super cool text File" + i));
        // }
        updateFiles("folder/file1.txt", "folder/file2.txt", "folder/file3.txt", "folder/file4.txt");
        setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width + UIManager.getInt("ScrollBar.width"), 200));
        // set the scroll bar policy
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void updateFiles(String... files) {
        // clear the old file buttons
        for (JButton button : currentFileButtons) {
            buttonPanel.remove(button);
        }
        currentFileButtons.clear();
        // add the new file buttons to the currentFileButtons and buttonPanel
        for (String file : files) {
            JButton button = new JButton(file);
            currentFileButtons.add(button);
            buttonPanel.add(button);
        }
    }
}
