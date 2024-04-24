package gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class FileScrollPane extends JScrollPane {
    private JPanel buttonPanel;

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
    }

    public void updateFiles(String... files) {
        // clear the old file buttons
        buttonPanel.removeAll();
        // add the new file buttons to the buttonPanel
        for (String file : files) {
            buttonPanel.add(new JButton(file));
        }

        // fill the available space to make the buttons not stretch when there are to few
        // 8 is the number of buttons that fit in the scroll pane without triggering the scrollbar
        for (int i = 0; i < 8 - files.length; i++) {
            buttonPanel.add(new JLabel());
        }
    }
}
