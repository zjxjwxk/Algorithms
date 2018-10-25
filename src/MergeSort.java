/**
 * @author zjxjwxk
 */
public class MergeSort {

    private static void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int len = end - start;
        int mid = (len / 2) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] <= arr[start2]) {
                result[k] = arr[start1];
                k++;
                start1++;
            } else {
                result[k] = arr[start2];
                k++;
                start2++;
            }
        }
        while (start1 <= end1) {
            result[k] = arr[start1];
            k++;
            start1++;
        }
        while (start2 <= end2) {
            result[k] = arr[start2];
            k++;
            start2++;
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

    private static void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, 0, len - 1);
    }

    public static void main(String[] args) {
        int[] arr = {4, 54, 11, 9, 5, 8, 37, 67, 24, 42, 2, 17, 50, 27, 36, 60};
        mergeSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
