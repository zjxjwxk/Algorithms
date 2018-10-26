import java.util.Arrays;

public class KnapsackProblem {

    public static int[] tabuSearch(int[] value, int[] weight, int iteratorCount, int maxWeight, int[] initArr) {
        int len = value.length;
        int[] currentList = initArr;
        int[] tabuList = new int[len];
        for (int i = 0; i < iteratorCount; i++) {
            int maxValueSum = 0;
            int[] maxValueList = new int[len];

            for (int j = 0; j < len; j++) {
                int[] tempList = currentList.clone();
                if (tempList[j] == 0) {
                    tempList[j] = 1;
                } else {
                    tempList[j] = 0;
                }
                int valueSum = valueSum(tempList, value);
                int weightSum = weightSum(tempList, weight);
                if (weightSum < maxWeight) {
                    if (valueSum > maxValueSum) {
                        if (!Arrays.equals(tempList, tabuList)) {
                            maxValueSum = valueSum;
                            maxValueList = tempList;
                        }
                    }
                }
            }
            tabuList = currentList;
            currentList = maxValueList;
        }
        return currentList;
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
        int[] value = {4, 10, 5, 7, 4, 3};
        int[] weight = {3, 9, 4, 6, 5, 2};
        int iteratorCount = 5;
        int maxWeight = 15;
        int[] initArr = {0, 0, 0, 0, 0, 0};
        int[] result = tabuSearch(value, weight, iteratorCount, maxWeight, initArr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
