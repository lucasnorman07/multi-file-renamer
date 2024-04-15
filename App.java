import menu.MainFrame;
import menu.MainMenu;

import java.io.File;
import java.util.Arrays;

import file_renaming.*;

public class App {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.getInstance();
        FileController fileController = FileController.getInstance();

        fileController.printCurrentFiles();
        File[] files = fileController.findInCurrentFiles("[1-2]|4", true, false);
        System.out.println("Found files:");
        for (File f : files) {
            System.out.println(f.getName());
        }
        
    }
}