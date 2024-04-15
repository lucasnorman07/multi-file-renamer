package menu.side_panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import file_renaming.FileController;

public class PrefixSidePanel extends BaseSidePanel {
    private static PrefixSidePanel _instance;

    // make private to not allow other classes to create an instance
    public PrefixSidePanel(JTextField textField, JButton button) {
        super(
            textField,
            button
        );
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileController.getInstance().addPrefixToCurrentFiles(textField.getText());
            }
        });
    }

    public static synchronized PrefixSidePanel getInstance() {
        if (_instance == null) {
            _instance = new PrefixSidePanel(
                new JTextField("Type prefix"), new JButton("Apply prefix")
            );
        }
        return _instance;
    }
}
