package bth004.homework;

/**
 * Counting sort of 0~99
 * @author zjxjwxk
 */
public class CountingSort {

    /**
     * Counting sort of 0~99
     * @param arr array to sort
     * @return sorted array
     */
    public static int[] countingSort(int[] arr) {

        int len = arr.length;

        int[] result = new int[len];

        // k is the length of the count array
        int k = 100;

        int[] count = new int[k];

        // Counting
        for (int i = 0; i < len; i++) {
            int a = arr[i];
            count[a]++;
        }

        // Accumulate count
        for (int i = 1; i < k; i++) {
            count[i] = count[i] + count[i - 1];
        }

        // Reverse fill result array
        for (int i = 0; i < len; i ++) {
            int a = arr[i];
            result[count[a] - 1] = a;
            count[a]--;
        }

        return result;
    }

    /**
     * print array
     * @param arr array to print
     */
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 1, 9, 99, 0, 9, 4, 5};
        System.out.println("Before counting sort:");
        printArr(arr);
        System.out.println("After counting sort:");
        int[] result = countingSort(arr);
        printArr(result);
    }
}
