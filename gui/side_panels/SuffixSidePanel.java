package gui.side_panels;

import javax.swing.*;

import file_renaming.FileController;
import gui.MainMenu;

public class SuffixSidePanel extends BaseSidePanel {
    private static SuffixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SuffixSidePanel(JTextField textField, JButton button) {
        super(
            textField,
            button
        );
        button.addActionListener(e -> {
            FileController.getInstance().addSuffixToCurrentFiles(textField.getText());
            // update the scroll pane after the new files have been selected
            MainMenu.getInstance().reloadScrollPane();
        });
    }

    public static synchronized SuffixSidePanel getInstance() {
        if (_instance == null) {
            _instance = new SuffixSidePanel(
                new JTextField("Type suffix"), new JButton("Apply suffix")
            );
        }
        return _instance;
    }
}
