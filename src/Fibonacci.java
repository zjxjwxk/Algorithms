/**
 * Fibonacci
 * @author zjxjwxk
 */
public class Fibonacci {

    /**
     * Fibonacci
     * @param n the index of the Fibonacci
     * @return the value of input index
     */
    public static int fibRecursive(int n) {
        int f;
        if (n <= 1) {
            f = n;
        } else {
            f = fibRecursive(n - 1) + fibRecursive(n - 2);
        }
        return f;
    }

    public static void main(String[] args) {
        int num = 3;
        System.out.println(fibRecursive(num));
    }
}
