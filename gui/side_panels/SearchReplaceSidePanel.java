package gui.side_panels;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import file_renaming.FileController;
import gui.MainMenu;

public class SearchReplaceSidePanel extends BaseSidePanel {
    private static SearchReplaceSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SearchReplaceSidePanel(JLabel textLabelTarget, JTextField textFieldTarget, JLabel textLabelReplacement, JTextField textFieldReplacement, JCheckBox useRegex, JCheckBox caseSensitive, JButton button) {
        super(
            textLabelTarget,
            textFieldTarget,
            textLabelReplacement,
            textFieldReplacement,
            useRegex,
            caseSensitive,
            button
        );
        button.addActionListener(e -> {
            // skip the operation if there are no files
            if (FileController.getInstance().getCurrentFiles() == null) return;
            FileController.getInstance().replaceInCurrentFiles(textFieldTarget.getText(), textFieldReplacement.getText(), useRegex.isSelected(), caseSensitive.isSelected());
            // update the scroll pane after the new files have been selected
            MainMenu.getInstance().reloadScrollPane();
        });
    }

    public static synchronized SearchReplaceSidePanel getInstance() {
        if (_instance == null) {
            _instance = new SearchReplaceSidePanel(
                new JLabel("Search for:"),
                new JTextField(),
                new JLabel("Replace with:"),
                new JTextField(),
                new JCheckBox("Use regex"),
                new JCheckBox("Match case"),
                new JButton("Search/Replace")
            );
        }
        return _instance;
    }
}
