package file_renaming;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFileChooser;

import gui.MainFrame;
import gui.MainMenu;

public class FileController {
    private static FileController _instance;

    private File[] currentFiles;

    // make private to not allow other classes to create an instance
    private FileController() {}

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
        MainMenu.getInstance().reloadScrollPane();
    }

    public void addPrefixToCurrentFiles(String prefix) {
        // use a regular for loop to be able to pass the refrence
        for (int i = 0; i < currentFiles.length; i++) {
            rename(i, prefix + currentFiles[i].getName());
        }
    }

    public void addSuffixToCurrentFiles(String suffix) {
        // use a regular for loop to be able to pass the refrence
        for (int i = 0; i < currentFiles.length; i++) {
            // split with \\. to match with the literal dot character because split uses regex
            String[] sections = currentFiles[i].getName().split("\\.");
            // calculate the new file name by adding the suffix before the first extension
            String newFileName = sections[0] + suffix + Arrays.stream(sections, 1, sections.length).reduce("", (subtotal, element) -> subtotal + "." + element);
            rename(i, newFileName);
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
        // use a linked hash set to be able to detect duplicates before actually renaming the files (it also needs to be ordered)
        LinkedHashSet<String> resultStrings = new LinkedHashSet<>();
        // loop over each file and replace the any matches with the target string to the replacement string
        for (File file : currentFiles) {
            // if the resultStrings.add method returns false, then return because the was a duplicate
            if (
                !resultStrings.add(replaceInString(file.getName(), target, replacement, useRegex, caseSensitive))
            ) {
                System.out.println("String was duplicate!");
                return; 
            }
        }
        // actually rename the files
        int i = 0;
        for (String newName : resultStrings) {
            // rename the file and update i
            boolean status = rename(i++, newName);
            if (!status) {
                // TODO, handle issue
                System.out.println("Issue when renaming");
            }
        }
    }

    private static String replaceInString(String text, String target, String replacement, boolean useRegex, boolean caseSensitive) {
        if (useRegex) {
            if (caseSensitive)
                return text.replaceAll(target, replacement);
            // (?i) means to start case-insensitive mode in regex  
            return text.replaceAll("(?i)" + target, replacement);   
        }
        // without regex
        if (caseSensitive)
            // text.replace doesn't use regex
            return text.replace(target, replacement);
        return replaceIgnoreCase(text, target, replacement);
    }
    
    private static String replaceIgnoreCase(String text, String target, String replacement) {
        int index = text.toLowerCase().indexOf(target.toLowerCase());
        // if there are no more matches then return the text
        if (index == -1) return text;
        // if there are still matches then call replaceIgnoreCase with the new text until all matches are replaced
        String newText = text.substring(0, index) + replacement + text.substring(index + target.length(), text.length());
        return replaceIgnoreCase(newText, target, replacement);
    }

    // returns true for success and false for failure (pass a file index to be able to update the refrence)
    public boolean rename(int fileIndex, String newFileName) {
        String newFilePath = currentFiles[fileIndex].getParentFile().toPath() + "/" + newFileName;
        // create the new file
        File newFile = new File(newFilePath);
        // rename the file and update the refrence
        boolean result = currentFiles[fileIndex].renameTo(newFile);
        currentFiles[fileIndex] = newFile;
        return result;
    }

    public static String getBaseName(File file) {
        String[] pathSections = file.getName().split("/");
        return pathSections[pathSections.length - 1];
    }
}
