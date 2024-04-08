package menu.side_panels;

import javax.swing.*;

public class SuffixSidePanel extends BaseSidePanel {
    private static SuffixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public SuffixSidePanel() {
        super(new JComponent[]{
            new JTextField("Type suffix"),
            new JButton("Apply suffix")
        });
    }

    public static synchronized SuffixSidePanel getInstance() {
        if (_instance == null) {
            _instance = new SuffixSidePanel();
        }
        return _instance;
    }
}
