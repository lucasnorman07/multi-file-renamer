package gui.side_panels;
import javax.swing.*;

import file_renaming.FileController;
import gui.MainMenu;

public class SearchReplaceSidePanel extends BaseSidePanel {
    private static SearchReplaceSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SearchReplaceSidePanel(JTextField textFieldQuery, JTextField textFieldReplacement, JButton button) {
        super(
            textFieldQuery,
            textFieldReplacement,
            button
        );
        button.addActionListener(e -> {
            FileController.getInstance().replaceInCurrentFiles(textFieldQuery.getText(), textFieldReplacement.getText(), true, true);
            // update the scroll pane after the new files have been selected
            MainMenu.getInstance().reloadScrollPane();
        });
    }

    public static synchronized SearchReplaceSidePanel getInstance() {
        if (_instance == null) {
            _instance = new SearchReplaceSidePanel(
                new JTextField("Search for"),
                new JTextField("Replace with"),
                new JButton("Search/Replace")
            );
        }
        return _instance;
    }
}
