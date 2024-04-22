package gui.side_panels;

import javax.swing.*;

import file_renaming.FileController;
import gui.MainMenu;

public class PrefixSidePanel extends BaseSidePanel {
    private static PrefixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public PrefixSidePanel(JLabel textLabel, JTextField textField, JButton button) {
        super(
            textLabel,
            textField,
            button
        );
        button.addActionListener(e -> {
            FileController.getInstance().addPrefixToCurrentFiles(textField.getText());
            // update the scroll pane after the new files have been selected
            MainMenu.getInstance().reloadScrollPane();
        });
    }

    public static synchronized PrefixSidePanel getInstance() {
        if (_instance == null) {
            _instance = new PrefixSidePanel(
                new JLabel("Type prefix:"), new JTextField(), new JButton("Apply prefix")
            );
        }
        return _instance;
    }
}
