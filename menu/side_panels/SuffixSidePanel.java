package menu.side_panels;

import javax.swing.*;

public class SuffixSidePanel extends BaseSidePanel {
    private static SuffixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SuffixSidePanel(JTextField textField, JButton button) {
        super(
            textField,
            button
        );
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
