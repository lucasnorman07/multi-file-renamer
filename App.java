import menu.Frame;
import menu.Panel;

import java.io.File;

import file_renaming.*;

public class App {
    public static void main(String[] args) {
        Panel mainPanel = new Panel();
        Frame mainFrame = new Frame("Multi File Renamer", mainPanel);
        FileController fileController = FileController.getInstance();
        fileController.setFolderPath("folder");

        System.out.println(FileController.replaceIgnoreCase("banana", "a", "q"));

        // fileController.replaceInCurrentFolder("[1-2]", "5", true, false); 
        // fileController.printFileNamesInCurrentFolder();
    }
}