package bth004.lesson;

/**
 * 获得股票买卖最大盈利
 * @author zjxjwxk
 */
public class FindMaxBenefit {

    public static int solution(int[] arr, int start, int end) {
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
        int benefit1 = solution(arr, start1, end1);
        int benefit2 = solution(arr, start2, end2);
        //conquer
        int min = arr[start1];
        int max = arr[start2];
        while (start1 <= end1) {
            if (arr[start1] < min) {
                min = arr[start1];
            }
            start1++;
        }
        while (start2 <= end2) {
            if (arr[start2] > max) {
                max = arr[start2];
            }
            start2++;
        }
        int benefit3 = max - min;
        return Math.max(Math.max(benefit1, benefit2), benefit3);
    }

    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 10, 6};
        int len = arr.length;
        System.out.println("Max benefit:" + solution(arr, 0, len - 1));
    }
}
