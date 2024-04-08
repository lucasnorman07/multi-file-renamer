import menu.MainFrame;
import menu.MainMenu;

import java.io.File;

import file_renaming.*;

public class App {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.getInstance();
        FileController fileController = FileController.getInstance();
        fileController.setFolderPath("folder");

        
    }
}