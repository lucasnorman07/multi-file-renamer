package file_renaming;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collector;

import javax.swing.JFileChooser;

import menu.MainFrame;
import menu.MainMenu;

public class FileController {
    private static FileController _instance;

    private File[] currentFiles;

    // make private to not allow other classes to create an instance
    private FileController() {
        currentFiles = new File[0];
    }

    public static synchronized FileController getInstance() {
        if (_instance == null) {
            _instance = new FileController();
        }
        return _instance;
    }

    public File[] getCurrentFiles() {
        return currentFiles;
    }

    public void setCurrentFiles(String... filePaths) {
        currentFiles = new File[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            currentFiles[i] = new File(filePaths[i]);
        }
    }

    public void printCurrentFiles() {
        for (File file : currentFiles) {
            System.out.println(file.getName());
        }
    }

    // let the user browse and select new files
    public void browseFiles() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int option = chooser.showOpenDialog(MainFrame.getInstance());
        // if the user select files then update currentFiles
        if (option == JFileChooser.APPROVE_OPTION) {
            currentFiles = chooser.getSelectedFiles();
        }
        // update the scroll pane after the new files have been selected
        MainMenu.getInstance().updateFiles(
            // convert the File[] to String[] by using streams
            Arrays.stream(currentFiles).map(File::getName).toArray(String[]::new)
        );
    }

    public void addPrefixToCurrentFiles(String prefix) {
        for (File file : currentFiles) {
            rename(file, prefix + file.getName());
        }
    }

    public void addSuffixToCurrentFiles(String suffix) {
        for (File file : currentFiles) {
            // split with \\. to match with the literal dot character because split uses regex
            String[] sections = file.getName().split("\\.");
            // calculate the new file name by adding the suffix before the first extension
            String newFileName = sections[0] + suffix + Arrays.stream(sections, 1, sections.length).reduce("", (subtotal, element) -> subtotal + "." + element);
            rename(file, newFileName);
        }
    }

    // helper function to create a regex pattern
    private Pattern createRegexPattern(String regex, boolean caseSensitive) {
        try {
            if (caseSensitive) return Pattern.compile(regex);
            else return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException _e) {
           return null;
        }
    }

    public File[] findInCurrentFiles(String searchString, boolean useRegex, boolean caseSensitive) {
        Pattern pattern = createRegexPattern(searchString, caseSensitive);
        // if an error was caught then an empty array
        if (pattern == null) return new File[0];
        // filter by all of the file names that match the regex by using streams
        return Arrays.stream(currentFiles).filter(file -> {
            String name = file.getName();
            if (useRegex) {
                // returns true if a match is found
                return pattern.matcher(name).find();
            }
            // if not case sensitive then uppercase the name and the search string to ignore case
            String newName = caseSensitive ? name : name.toUpperCase();
            String newSearchString = caseSensitive ? searchString : searchString.toUpperCase();
            return newName.contains(newSearchString);
        }).toArray(File[]::new);
    }

    public void replaceInCurrentFiles(String target, String replacement, boolean useRegex, boolean caseSensitive) {
        // loop over each file and replace the any matches with the target string to the replacement string
        for (File file : currentFiles) {
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
