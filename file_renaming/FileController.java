package file_renaming;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class FileController {
    private static FileController _instance;

    private File currentFolder;

    // to disable the default constructor
    private FileController() {}

    public static synchronized FileController getInstance() {
        if (_instance == null) {
            _instance = new FileController();
        }
        return _instance;
    }

    public void setFolderPath(String folderPath) {
        currentFolder = new File(folderPath);
    }
    
    public File[] getFileNamesInCurrentFolder() {
        if (currentFolder == null) return null;
        // if the folder is not null, then return the file paths
        return currentFolder.listFiles();
    }

    public void addPrefixToFilesInCurrentFolder(String prefix) {
        for (File file : currentFolder.listFiles()) {
            rename(file, prefix + file.getName());
        }
    }

    public void addSuffixToFilesInCurrentFolder(String suffix) {
        for (File file : currentFolder.listFiles()) {
            // split with \\. to match with the literal dot character because split uses regex
            String[] sections = file.getName().split("\\.");
            // calculate the new file name by adding the suffix before the first extension
            String newFileName = sections[0] + suffix + Arrays.stream(sections, 1, sections.length).reduce("", (subtotal, element) -> subtotal + "." + element);
            rename(file, newFileName);
        }
    }

    public void printFileNamesInFolder() {
        File[] files = getInstance().getFileNamesInCurrentFolder();
        if (files == null) {
            System.out.println("The current files in folder is null");
        } else {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }

    // returns true for success and false for failure
    public static boolean rename(File file, String newFileName) {
        String newFilePath = file.getParentFile().toPath() + "/" + newFileName;
        return file.renameTo(new File(newFilePath));
    }

    public static String getBaseName(File file) {
        String[] pathSections = file.getName().split("/");
        return pathSections[pathSections.length - 1];
    }
}
