package bth004.homework;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author zjxjwxk
 */
public class WordsFrequencyRank {

    public static List<Map.Entry<String, Integer>> rank(String filePath) {

        // 文件读取
        File file = new File(filePath);
        BufferedReader br;
        Map<String, Integer> countMap = new HashMap<>();
        try {
            InputStream is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8), 512);

            // 使用IKAnalyzer进行分词
            IKSegmenter ikSegmenter = new IKSegmenter(br, true);
            Lexeme lexeme;
            String word;
            while ((lexeme = ikSegmenter.next()) != null){
                word = lexeme.getLexemeText();
                if (word.length() == 2) {
                    countMap.merge(word, 1, (a, b) -> a + b);
                }
            }

            // 逐行逐词遍历
//            for (String line = br.readLine(); line != null; line = br.readLine()){
//                int len = line.length();
//                for (int i = 0; i < len - 1; i ++) {
//                    String word = (String.valueOf(line.charAt(i)) + String.valueOf(line.charAt(i + 1)));
//                    countMap.merge(word, 1, (a, b) -> a + b);
//                }
//            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        List<Map.Entry<String, Integer>> countList = new ArrayList<>(countMap.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator = (o1, o2) -> o2.getValue() - o1.getValue();
        countList.sort(comparator);

        return countList;
    }

    public static void printCountMap(List<Map.Entry<String, Integer>> countList) {
        for (Map.Entry<String, Integer> word :
                countList) {
            System.out.println(word.getKey() + "  " + word.getValue());
        }
    }

    public static void main(String[] args) {
        String filepath = "lyrics/光明.txt";
        List<Map.Entry<String, Integer>> countMap = rank(filepath);
        if (countMap != null) {
            printCountMap(countMap);
        }
        System.out.println();
    }
}
