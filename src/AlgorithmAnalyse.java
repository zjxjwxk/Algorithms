import java.io.File;

/**
 * Algorithm analyse of merge sort and bubble sort
 * @author zjxjwxk
 */
public class AlgorithmAnalyse {

    private static double readTime;
    private static double initTime;
    private static double algorithmTime;

    /**
     * recursive method for merge sort
     * @param arr array to sort
     * @param result sorted array
     * @param start start index
     * @param end end index
     * @param operationsCount count for operation times
     * @return operation times
     */
    private static long mergeSortRecursive(int[] arr, int[] result, int start, int end, long operationsCount) {
        if (start >= end) {
            return operationsCount;
        }
        // divide
        int len = end - start;
        int mid = (len / 2) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        operationsCount = mergeSortRecursive(arr, result, start1, end1, operationsCount);
        operationsCount = mergeSortRecursive(arr, result, start2, end2, operationsCount);
        // conquer
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            operationsCount++;
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
        return operationsCount;
    }

    /**
     * merge sort
     * @param arr array to sort
     * @return array of operation times, initialization time and algorithm time
     */
    public static long mergeSort(int[] arr) {

        int len = arr.length;
        int[] result = new int[len];

        long algorithmStartTime = System.nanoTime();
        long operationsCount = mergeSortRecursive(arr, result, 0, len - 1, 0);
        long algorithmEndTime = System.nanoTime();

        algorithmTime = (algorithmEndTime - algorithmStartTime) / 1000000000.0;

        return operationsCount;
    }

    /**
     * bubble sort
     * @param arr array to sort
     * @return operation times
     */
    public static long bubbleSort(int[] arr) {
        long operationsCount = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j+1]) {
                    operationsCount++;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return operationsCount;
    }

    /**
     * print array
     * @param arr arr to print
     */
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * print merge sort result
     * @param filename name of the file to read array
     */
    public static void printMergeSortResult(String filename){

        System.out.println("Merge Sort: ");
        long readAndInitStartTime = System.nanoTime();
        int arr[] = readFile(filename);
        long readAndInitEndTime = System.nanoTime();
        double readAndInitTime = (readAndInitEndTime - readAndInitStartTime) / 1000000000.0;
        initTime = readAndInitTime - readTime;

        System.out.println("Time for reading the file: " + readTime + "s");
        System.out.println("Time for initialization: " + initTime + "s");

        long operationsCount = mergeSort(arr);

        System.out.println("Time for algorithm: " + algorithmTime + "s");
        System.out.println("Operation times: " + operationsCount + " times");
    }

    /**
     * print bubble sort result
     * @param filename name of the file to read array
     */
    public static void printBubbleSortResult(String filename) {

        System.out.println("Bubble Sort: ");
        long readAndInitStartTime = System.nanoTime();
        int arr[] = readFile(filename);
        long readAndInitEndTime = System.nanoTime();
        double readAndInitTime = (readAndInitEndTime - readAndInitStartTime) / 1000000000.0;
        initTime = readAndInitTime - readTime;

        System.out.println("Time for reading the file: " + readTime + "s");
        System.out.println("Time for initialization: " + initTime + "s");

        long algorithmStartTime = System.nanoTime();
        long operationsCount = bubbleSort(arr);
        long algorithmEndTime = System.nanoTime();
        algorithmTime = (algorithmEndTime - algorithmStartTime) / 1000000000.0;

        System.out.println("Time for algorithm: " + algorithmTime + "s");
        System.out.println("Operation times: " + operationsCount + " times");
    }

    /**
     * read file
     * @param filename name of the file to read array
     * @return the array in the file
     */
    public static int[] readFile(String filename) {

        File file = new File(filename);
        ReadFile readFile = new ReadFile(file);

        long readStartTime =  System.nanoTime();
        int[] arr = readFile.readAllInts();
        long readEndTime = System.nanoTime();

        readTime = (readEndTime - readStartTime) / 1000000000.0;

        return arr;
    }

    public static void main(String[] args) {

        // 10^4
        String numbers1 = "Numbers1.txt";
        // 10^5
        String numbers2 = "Numbers2.txt";
        // 10^6
        String numbers3 = "Numbers3.txt";
        // 10^7
        String numbers4 = "Numbers4.txt";
        // 10^8
        String numbers5 = "Numbers5.txt";
        printBubbleSortResult(numbers1);
    }
}
