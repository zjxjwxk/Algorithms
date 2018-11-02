import java.util.Random;

/**
 * @author zjxjwxk
 */
public class AlgorithmTest {

    private static int mergeSortRecursive(int[] arr, int[] result, int start, int end, int operationsCount) {
        if (start >= end) {
            return 0;
        }
        // divide
        int len = end - start;
        int mid = (len / 2) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        mergeSortRecursive(arr, result, start1, end1, operationsCount);
        mergeSortRecursive(arr, result, start2, end2, operationsCount);
        // conquer
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] <= arr[start2]) {
                operationsCount++;
                result[k++] = arr[start1++];
            } else {
                result[k++] = arr[start2++];
            }
        }
        while (start1 <= end1) {
            operationsCount++;
            result[k++] = arr[start1++];
        }
        while (start2 <= end2) {
            operationsCount++;
            result[k++] = arr[start2++];
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
        return operationsCount;
    }

    public static double[] mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        int count = 0;
        long startTime = System.nanoTime();
        int operationsCount = mergeSortRecursive(arr, result, 0, len - 1, count);
        long entTime = System.nanoTime();
        double time = (entTime - startTime) / 1000000000.0;
        double[] ret = new double[2];
        ret[0] = time;
        ret[1] = operationsCount;
        return ret;
    }

    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printMergeSortResult(int[] arr){

        double[] result = mergeSort(arr);

        System.out.println("Merge Sort: ");
        System.out.println("Time: " + result[0] + "s");
        System.out.println("Operation times: " + result[1] + "times");
    }

    public static int[] generateArray(int num) {
        int[] arr = new int[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = generateArray(10000);
        printMergeSortResult(arr);
    }
}
