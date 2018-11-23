package bth004.assignment2;

import java.io.*;
import java.util.Locale;
import java.util.Random;

/**
 * Write file
 * @author zjxjwxk
 */
public class WriteFile {

    /**
     * force Unicode UTF-8 encoding; otherwise it's system dependent
     */
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * assume language = English, country = US for consistency with In
     */
    private static final Locale LOCALE = Locale.US;

    private PrintWriter out;

    /**
     * Initializes an output stream from a file.
     *
     * @param  filename the name of the file
     */
    public WriteFile(String filename) {
        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints a int to this output stream and flushes this output stream.
     *
     * @param x the int to print
     */
    public void print(int x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a String to this output stream and flushes this output stream.
     *
     * @param x the String to print
     */
    public void print(String x) {
        out.print(x);
        out.flush();
    }

    public void writeRandomNumbers(int len) {
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            print(random.nextInt(10) + " ");
        }
    }

    public static void main(String[] args) {
        String numbersTxt1 = "numbers1.txt";
        String numbersTxt2 = "numbers2.txt";
        String numbersTxt3 = "numbers3.txt";
        String numbersTxt4 = "numbers4.txt";
        String numbersTxt5 = "numbers5.txt";

        WriteFile writeFile1 = new WriteFile(numbersTxt1);
        writeFile1.writeRandomNumbers(10000);

        WriteFile writeFile2 = new WriteFile(numbersTxt2);
        writeFile2.writeRandomNumbers(100000);

        WriteFile writeFile3 = new WriteFile(numbersTxt3);
        writeFile3.writeRandomNumbers(1000000);

        WriteFile writeFile4 = new WriteFile(numbersTxt4);
        writeFile4.writeRandomNumbers(10000000);

        WriteFile writeFile5 = new WriteFile(numbersTxt5);
        writeFile5.writeRandomNumbers(100000000);
    }
}
