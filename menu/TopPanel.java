package menu;

import javax.swing.*;

import file_renaming.FileController;
import menu.side_panels.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {
    public TopPanel() {
        super();
        setLayout(new FlowLayout());

        JButton[] elements = {
            new JButton("Change folder"),
            new JButton("Add prefix"),
            new JButton("Add suffix"),
            new JButton("search/replace")
        };

        // add an action listener to each button
        addActionListeners(elements);

        for (JButton element : elements) {
            add(element);
        }
    }

    // method to simply abstract away the proccess of adding action listeners
    private void addActionListeners(JButton[] elements) {
        // add an action listener to each element
        elements[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // let the user select a new folder
                FileController.getInstance().browseFolders();
            }
        });
        elements[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.getInstance().changeSidePanel(PrefixSidePanel.getInstance());
            }
        });
        elements[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.getInstance().changeSidePanel(SuffixSidePanel.getInstance());
            }
        });
        elements[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.getInstance().changeSidePanel(SearchReplaceSidePanel.getInstance());
            }
        });
    }
}
