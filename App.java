import java.io.File;

import file_renaming.*;
import gui.MainFrame;

public class App {
    public static void main(String[] args) {
        // create the first instance
        MainFrame.getInstance();
        FileController fileController = FileController.getInstance();

        fileController.printCurrentFiles();
        File[] files = fileController.findInCurrentFiles("[1-2]|4", true, false);
        System.out.println("Found files:");
        for (File f : files) {
            System.out.println(f.getName());
        }
        
    }
}