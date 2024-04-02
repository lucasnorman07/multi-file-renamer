package menu;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileScrollPane extends JScrollPane {
    private JPanel buttonPanel;

    public FileScrollPane(JPanel buttonPanel) {
        super(buttonPanel);
        this.buttonPanel = buttonPanel;
        // add a grid layout so that the width of each button is adjusted to fit the buttonPanel (0 means any number of rows)
        buttonPanel.setLayout(new GridLayout(0, 1));
        // for (int i = 0; i < 10001; i++) {
        //     buttonPanel.add(new JButton("Super cool text File" + i));
        // }
        setFolder(new File("folder"));
        setPreferredSize(new Dimension(buttonPanel.getPreferredSize().width + UIManager.getInt("ScrollBar.width"), 200));
        // set the scroll bar policy
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void setFolder(File folder) {
        for (File file : folder.listFiles()) {
            buttonPanel.add(new JButton(file.getName()));
        }
    }
}
