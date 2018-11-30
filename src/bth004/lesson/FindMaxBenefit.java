package bth004.lesson;

/**
 * Find the max benefit from a price array
 * @author zjxjwxk
 */
public class FindMaxBenefit {

    /**
     * A recursive method to find the max benefit
     * index[] of buying and selling from an array
     * with start index and end index
     * @param arr The price array ordered by time
     * @param start The start index
     * @param end The end index
     * @return The max benefit index[] of buying and selling.
     * Index[0] is index of buying and index[1] is selling
     */
    public static int[] findMaxBenefit(int[] arr, int start, int end) {
        if (start >= end) {
            return new int[]{start, end};
        }

        // Divide
        int len = end - start;
        int mid = (len / 2) + start;
        int start1 = start;
        int end1 = mid;
        int start2 = mid + 1;
        int end2 = end;
        // The max benefit index[] of buying and selling of two parts
        int[] index1 = findMaxBenefit(arr, start1, end1);
        int[] index2 = findMaxBenefit(arr, start2, end2);

        // Conquer
        int min = arr[start1];
        int max = arr[start2];
        int minIndex = start1;
        int maxIndex = start2;
        while (start1 <= end1) {
            if (arr[start1] < min) {
                min = arr[start1];
                minIndex = start1;
            }
            start1++;
        }
        while (start2 <= end2) {
            if (arr[start2] > max) {
                max = arr[start2];
                maxIndex = start2;
            }
            start2++;
        }
        // Return the max benefit index[] of buying and selling
        int benefit1 = arr[index1[1]] - arr[index1[0]];
        int benefit2 = arr[index2[1]] - arr[index2[0]];
        int benefit3 = max - min;
        if (benefit1 > benefit2) {
            if (benefit1 > benefit3) {
                return index1;
            } else {
                return new int[]{minIndex, maxIndex};
            }
        } else {
            if (benefit2 > benefit3) {
                return index2;
            } else {
                return new int[]{minIndex, maxIndex};
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 10, 6, 3, 9, 8, 1, 3};
        int len = arr.length;
        int[] index = findMaxBenefit(arr, 0, len - 1);
        System.out.println("Buy:" + " index:" + index[0] + " price:" + arr[index[0]]);
        System.out.println("Sell:" + " index:" + index[1] + " price:" + arr[index[1]]);
        System.out.println("Max benefit:" + (arr[index[1]] - arr[index[0]]));
    }
}
