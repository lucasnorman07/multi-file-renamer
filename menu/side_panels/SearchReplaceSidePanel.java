package menu.side_panels;

import javax.swing.*;

public class SearchReplaceSidePanel extends BaseSidePanel {
    private static SearchReplaceSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SearchReplaceSidePanel() {
        super(new JComponent[]{
            new JTextField("Search for"),
            new JTextField("Replace with"),
            new JButton("Search/Replace")
        });
    }

    public static synchronized SearchReplaceSidePanel getInstance() {
        if (_instance == null) {
            _instance = new SearchReplaceSidePanel();
        }
        return _instance;
    }
}
