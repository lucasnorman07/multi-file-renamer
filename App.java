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

        fileController.printFileNamesInFolder();
        fileController.addPrefixToFilesInCurrentFolder("prefix");
        fileController.addSuffixToFilesInCurrentFolder("suffix");
        fileController.printFileNamesInFolder();

        // System.out.println(FileController.getBaseName("/mnt/home/myname/coolstuff/ctf/problems/forensics/flag.txt"));
        // boolean renameStatus = FileController.rename("file.txt", "renamed.txt");
        // if (renameStatus) System.out.println("RENAME SUCCESS!");
        // else System.out.println("RENAME FAILURE!");
        // PrefixController.addPrefix("file.txt", "prefix-");
    }
}