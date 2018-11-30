package bth004.lesson;

/**
 * Fibonacci by dynamical programming
 * @author zjxjwxk
 */
public class FibonacciDynamical {

    /**
     * Fibonacci by dynamical programming
     * @param n Length of the fibonacci array
     * @return The fibonacci array
     */
    public static int[] fibonacciDynamical(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int f;
            if (i <= 1) {
                f = 1;
            } else {
                f = result[i - 1] + result[i - 2];
            }
            result[i] = f;
        }
        return result;
    }

    /**
     * Print array
     * @param arr array to print
     */
    public static void printArr(int[] arr) {
        for (int n : arr) {
            System.out.print(n + " ");
        }
    }

    public static void main(String[] args) {
        int num = 20;
        int[] result = fibonacciDynamical(num);
        printArr(result);
    }
}
