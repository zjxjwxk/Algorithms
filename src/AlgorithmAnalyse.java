import java.io.File;

/**
 * @author zjxjwxk
 */
public class AlgorithmAnalyse {

    private static double mergeSortRecursive(int[] arr, int[] result, int start, int end, double operationsCount) {
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
        operationsCount += mergeSortRecursive(arr, result, start1, end1, operationsCount);
        operationsCount += mergeSortRecursive(arr, result, start2, end2, operationsCount);
        // conquer
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] <= arr[start2]) {
                operationsCount++;
                result[k++] = arr[start1++];
            } else {
                operationsCount++;
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
        double operationsCount = mergeSortRecursive(arr, result, 0, len - 1, count);
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
        System.out.println("Operation times: " + result[1] + " times");
    }

    public static int[] readFile(String pathname) {
        File file = new File(pathname);
        ReadFile readFile = new ReadFile(file);
        int[] arr = readFile.readAllInts();
        return arr;
    }

    public static void main(String[] args) {

        String numbers1 = "Numbers1.txt";
        String numbers2 = "Numbers2.txt";
        String numbers3 = "Numbers3.txt";
        String numbers4 = "Numbers4.txt";
        String numbers5 = "Numbers5.txt";
        int[] arr = readFile(numbers5);
//        printArray(arr);
        printMergeSortResult(arr);
//        printArray(arr);
    }
}
