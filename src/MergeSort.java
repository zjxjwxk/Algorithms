/**
 * Merge sort
 * @author zjxjwxk
 */
public class MergeSort {

    /**
     * recursive method for merge sort
     * @param arr array to sort
     * @param result sorted array
     * @param start start index
     * @param end end index
     */
    private static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        // divide
        int len = end - start;
        int mid = (len / 2) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        // conquer
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        }
        while (start1 <= end1) {
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            result[k++] = arr[start2++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

    /**
     * merge sort
     * @param arr array to sort
     */
    public static void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, 0, len - 1);
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 9, 1, 5, 7, 6, 8};
        mergeSort(arr);
        print(arr);
    }
}
