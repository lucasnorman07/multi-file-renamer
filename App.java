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

        File[] files = fileController.findInCurrentFolder(".txt", false, false, false);
        if (files == null) {
            System.out.println("An error occured!");
            return;
        }
        for (File f : files) {System.out.println(f.getName());}
    }
}