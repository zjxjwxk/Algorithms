public class FindPeaks {

    public static int findPeak(int[] num) {

        int start = 0, end = num.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (num[mid + 1] > num[mid]) {
                start = mid + 1;
            } else if (num[mid - 1] > num[mid]) {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] num = {5, 4, 6, 8, 10, 1, 2, 7};
        System.out.println(findPeak(num));
    }
}
