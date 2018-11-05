import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Read file
 * @author zjxjwxk
 */
public class ReadFile {

    /**
     * assume Unicode UTF-8 encoding
      */
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * assume language = English, country = US for consistency with System.out.
     */
    private static final Locale LOCALE = Locale.US;

    /**
     * the default token separator; we maintain the invariant that this value
     * is held by the scanner's delimiter between calls
     */
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    /**
     * used to read the entire input. source:
     */
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    private Scanner scanner;

    /**
     * Initializes an input stream from a file.
     *
     * @param  file the file
     * @throws IllegalArgumentException if cannot open {@code file}
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */
    public ReadFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file argument is null");
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }

    /**
     * Reads all remaining tokens from this input stream, parses them as integers,
     * and returns them as an array of integers.
     *
     * @return all remaining lines in this input stream, as an array of integers
     */
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Integer.parseInt(fields[i]);
        }
        return vals;
    }

    /**
     * Reads all remaining tokens from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining tokens in this input stream, as an array of strings
     */
    public String[] readAllStrings() {
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }
        String[] decapitokens = new String[tokens.length-1];
        for (int i = 0; i < tokens.length-1; i++) {
            decapitokens[i] = tokens[i+1];
        }
        return decapitokens;
    }

    /**
     * Reads and returns the remainder of this input stream, as a string.
     *
     * @return the remainder of this input stream, as a string
     */
    public String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // not that important to reset delimeter, since now scanner is empty, but let's do it anyway
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }
}