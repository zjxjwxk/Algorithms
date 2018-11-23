package bth004.lesson;

import java.util.*;

/**
 * 求一列字符的全排列数列
 * @author zjxjwxk
 */
public class Permutation {

    public List<String> permutation(char[] chars){
        int len = chars.length;
        List<String> stringList1 = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char[] charArr1 = Arrays.copyOfRange(chars, 0, i);
            char[] charArr2 = Arrays.copyOfRange(chars, i + 1, len);

            List<String> stringList2 = permutation(charArr1);
            for (int j = 0; j < stringList2.size(); j++) {
                // todo
            }
        }
        // todo
        return null;
    }

    public static char[] addArr(char[] charArr1, char[] charArr2) {
        char[] result = new char[charArr1.length + charArr2.length];
        // todo
        return null;
    }

    public static void main(String[] args) {

    }
}
