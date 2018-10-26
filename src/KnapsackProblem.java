import java.util.Arrays;

public class KnapsackProblem {

    public static int[] tabuSearch(int[] value, int[] weight, int iteratorCount, int maxWeight) {
        int len = value.length;
        int[] currentList = new int[len];
        int[] tabuList = new int[len];
        int maxValueSum = 0;
        for (int i = 0; i < iteratorCount; i++) {
            for (int j = 0; j < len; j++) {
                if (currentList[i] == 0) {
                    currentList[i] = 1;
                } else {
                    currentList[i] = 0;
                }
                int valueSum = valueSum(currentList, value);
                int weightSum = weightSum(currentList, weight);
                if (weightSum < maxWeight) {
                    if (valueSum > maxValueSum) {
                        if (Arrays.equals(currentList, tabuList)) {
                            maxValueSum = valueSum;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static int valueSum(int[] arr, int[] value) {
        int valueSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                valueSum += value[i];
            }
        }
        return valueSum;
    }

    public static int weightSum(int[] arr, int[] weight) {
        int weightSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                weightSum += weight[i];
            }
        }
        return weightSum;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2};
        int[] b = {0, 1, 2};
        System.out.println(Arrays.equals(a, b));
    }
}
