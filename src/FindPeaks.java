public class FindPeaks {

    public static int findPeak(int[] nums) {

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid + 1] > nums[mid]) {
                start = mid + 1;
            } else if (nums[mid + 1] < nums[mid]) {
                end = mid;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 6, 8, 10, 1, 2, 7};
        System.out.println(findPeak(nums));
    }
}
