package bth004.homework;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Count the frequency of words and rank them
 * @author zjxjwxk
 */
public class WordsFrequencyRank {

    /**
     * Read the words in the file according to filepath,
     * divide words by IKAnalyzer, count the frequency of
     * words and put them into HashMap(key = "word", value = frequency),
     * finally rank them by frequency
     * @param filePaths array of paths of files
     * @return List of sorted Map Entry of words frequency
     */
    public static List<Map.Entry<String, Integer>> rank(String[] filePaths) {

        Map<String, Integer> countMap = new HashMap<>();

        // Read all files
        for (String filePath:
             filePaths) {
            File file = new File(filePath);
            BufferedReader br;
            try {
                InputStream is = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8), 512);
                // Divide words by IKAnalyzer
                IKSegmenter ikSegmenter = new IKSegmenter(br, true);
                Lexeme lexeme;
                String word;
                // Count the frequency of words and put into HashMap
                while ((lexeme = ikSegmenter.next()) != null){
                    word = lexeme.getLexemeText();
                    if (word.length() == 2) {
                        countMap.merge(word, 1, (a, b) -> a + b);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("文件不存在！");
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Sort the words by frequency
        List<Map.Entry<String, Integer>> countList = new ArrayList<>(countMap.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> o2.getValue() - o1.getValue();
        countList.sort(comparator);

        return countList;
    }

    /**
     * Print List of sorted Map Entry of words frequency
     * @param countList List to print
     */
    public static void printCountMap(List<Map.Entry<String, Integer>> countList) {
        for (Map.Entry<String, Integer> word :
                countList) {
            System.out.println(word.getKey() + "  " + word.getValue());
        }
    }

    public static void main(String[] args) {
        String[] filePaths = {"lyrics/光明.txt", "lyrics/当我想你的时候.txt", "lyrics/怒放的生命.txt",
                "lyrics/春天里.txt", "lyrics/青春.txt"};
        List<Map.Entry<String, Integer>> countMap = rank(filePaths);
        if (countMap != null) {
            printCountMap(countMap);
        }
        System.out.println();
    }
}
