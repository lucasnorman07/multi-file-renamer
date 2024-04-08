package file_renaming;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFileChooser;

import menu.Frame;
import menu.MainFrame;

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

    // let the user browse and select a new folder
    public void browseFolders() {
        JFileChooser chooser = new JFileChooser();
        int choice = chooser.showOpenDialog(MainFrame.getInstance());
        System.out.println(choice);
        // setFolderPath("text");
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

    public void printFileNamesInCurrentFolder() {
        File[] files = currentFolder.listFiles();
        if (files == null) {
            System.out.println("The current files in folder is null");
        } else {
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
    }

    private Pattern createRegexPattern(String regex, boolean caseSensitive) {
        try {
            if (caseSensitive) return Pattern.compile(regex);
            else return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException _e) {
           return null;
        }
    }

    public File[] findInCurrentFolder(String searchString, boolean useRegex, boolean caseSensitive) {
        if (currentFolder == null) return null;
        Pattern pattern = createRegexPattern(searchString, caseSensitive);
        // if an error was caught then return null
        if (pattern == null) return null;
        // filter by all of the file names that match the regex
        return currentFolder.listFiles((_dir, name) -> {
            if (useRegex) {
                // returns true if a match is found
                return pattern.matcher(name).find();
            }
            // if not case sensitive then uppercase the name and the search string to ignore case
            String newName = caseSensitive ? name : name.toUpperCase();
            String newSearchString = caseSensitive ? searchString : searchString.toUpperCase();
            return newName.contains(newSearchString);
        });
    }

    public void replaceInCurrentFolder(String target, String replacement, boolean useRegex, boolean caseSensitive) {
        if (currentFolder == null) return;
        // loop over each file and replace the any matches with the target string to the replacement string
        for (File file : currentFolder.listFiles()) {
            boolean status = rename(file, findReplaceInString(file.getName(), target, replacement, useRegex, caseSensitive));
            if (!status) {
                System.out.println("Two files can't have the same name!");
            }
        }
    }

    public static String findReplaceInString(String text, String target, String replacement, boolean useRegex, boolean caseSensitive) {
        if (useRegex) {
            if (caseSensitive)
                return text.replaceAll(target, replacement);   
            return text.replaceAll("(?i)" + target, replacement);   
        }
        // without regex
        if (caseSensitive)
            return text.replace(target, replacement);
        return replaceIgnoreCase(text, target, replacement);
    }
    
    public static String replaceIgnoreCase(String text, String target, String replacement) {
        int index = 0;
        while ((index = text.toLowerCase().indexOf(target.toLowerCase())) != -1) {
            text = text.substring(0, index) +
            replacement +
            text.substring(index + replacement.length(), text.length());
        };
        return text;
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
