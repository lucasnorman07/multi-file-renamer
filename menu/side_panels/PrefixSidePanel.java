package menu.side_panels;

import java.io.File;

import javax.swing.*;

import file_renaming.FileController;

public class PrefixSidePanel extends BaseSidePanel {
    private static PrefixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public PrefixSidePanel() {
        super(new JComponent[]{
            new JTextField("Type prefix"),
            new JButton("Apply prefix")
        });
    }

    public static synchronized PrefixSidePanel getInstance() {
        if (_instance == null) {
            _instance = new PrefixSidePanel();
        }
        return _instance;
    }
}
