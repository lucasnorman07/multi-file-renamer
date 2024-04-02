import menu.Frame;
import menu.MainMenu;

import java.io.File;

import file_renaming.*;

public class App {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Frame mainFrame = new Frame("Multi File Renamer", mainMenu);
        FileController fileController = FileController.getInstance();
        fileController.setFolderPath("folder");

        
    }
}