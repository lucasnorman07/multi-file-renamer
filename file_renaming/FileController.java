package file_renaming;

import java.io.File;

public class FileController {
    public static String currentFolderPath;

    // returns true for success and false for failure
    public static boolean rename(String filePath, String newFilePath) {
        File file = new File(filePath);
        return file.renameTo(new File(newFilePath));
    }

    public static String getBaseName(String filePath) {
        String[] pathSections = filePath.split("/");
        return pathSections[pathSections.length - 1];
    }
}
