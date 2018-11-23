package bth004.homework;

/**
 * 0~99的计数排序
 * @author zjxjwxk
 */
public class CountingSort {

    public static int[] countingSort(int[] arr) {

        int len = arr.length;

        int[] result = new int[len];

        // 假设arr中均为0~99的数，k为所需的用于计数的数组长度
        int k = 100;

        int[] count = new int[k];

        // 计数
        for (int i = 0; i < len; i++) {
            count[arr[i]]++;
        }

        // 累加计数
        for (int i = 1; i < k; i++) {
            count[i] = count[i] + count[i - 1];
        }

        // 反向填充结果数组
        for (int i = len - 1; i >= 0; i--) {
            int a = arr[i];
            result[count[a] - 1] = arr[i];
            count[a]--;
        }

        return result;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 1, 9, 99, 0};
        printArr(arr);
        int[] result = countingSort(arr);
        printArr(result);
    }
}
