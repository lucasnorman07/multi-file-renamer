package gui;

import javax.swing.*;

import file_renaming.FileController;
import gui.side_panels.*;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class MainMenu extends JPanel {
    private static MainMenu _instance;

    private JPanel sidePanel;
    private FileScrollPane scrollPane;

    public static synchronized MainMenu getInstance() {
        if (_instance == null) {
            _instance = new MainMenu();
        }
        return _instance;
    }

    // make private to not allow other classes to create an instance
    private MainMenu() {
        setLayout(new BorderLayout());

        JPanel topPanel = new TopPanel();
        add(topPanel, BorderLayout.NORTH);

        sidePanel = PrefixSidePanel.getInstance();
        add(sidePanel, BorderLayout.WEST);
        
        scrollPane = new FileScrollPane(new JPanel());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void changeSidePanel(JPanel newSidePanel) {
        // remove the old sidePanel and add the new one, then update the sidePanel
        remove(sidePanel);
        add(newSidePanel, BorderLayout.WEST);
        sidePanel = newSidePanel;
        // to revalidate the layout and repaint the panel after the change
        revalidate();
        repaint();
    }

    public void reloadScrollPane() {
        // convert the File[] to String[] by using streams
        String[] files = Arrays.stream(FileController.getInstance().getCurrentFiles()).map(File::getName).toArray(String[]::new);
        scrollPane.updateFiles(files);
        // to revalidate the layout and repaint the panel after the change
        revalidate();
        repaint();
    }
}
