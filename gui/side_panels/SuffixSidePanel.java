package gui.side_panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import file_renaming.FileController;
import gui.MainMenu;

public class SuffixSidePanel extends BaseSidePanel {
    private static SuffixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SuffixSidePanel(JLabel textLabel, JTextField textField, JButton button) {
        super(
            textLabel,
            textField,
            button
        );
        button.addActionListener(e -> {
            // skip the operation if there are no files
            if (FileController.getInstance().getCurrentFiles() == null) return;
            FileController.getInstance().addSuffixToCurrentFiles(textField.getText());
            // update the scroll pane after the new files have been selected
            MainMenu.getInstance().reloadScrollPane();
        });
    }

    public static synchronized SuffixSidePanel getInstance() {
        if (_instance == null) {
            _instance = new SuffixSidePanel(
                new JLabel("Type prefix:"), new JTextField(), new JButton("Apply suffix")
            );
        }
        return _instance;
    }
}
