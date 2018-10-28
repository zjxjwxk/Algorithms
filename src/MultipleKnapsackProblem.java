import java.util.*;

public class MultipleKnapsackProblem {

    public static int[][] greedy(float[] benefit, float[] weight, int[] knapsackWeight) {
        int itemLen = benefit.length;
        int knapsackLen = knapsackWeight.length;
        Map<Integer, Float> benefitPerWeightToIndexMap = new TreeMap<>();

        int[][] result = new int[itemLen][knapsackLen];
        for (int i = 0; i < itemLen; i++) {
            benefitPerWeightToIndexMap.put(i, (benefit[i] / weight[i]));
        }

        Comparator<Map.Entry<Integer, Float>> valueComparator = (o1, o2) -> o2.getValue().compareTo(o1.getValue());
        List<Map.Entry<Integer, Float>> benefitPerWeightToIndexList = new ArrayList<>(benefitPerWeightToIndexMap.entrySet());
        benefitPerWeightToIndexList.sort(valueComparator);

        for (int j = 0; j < knapsackLen; j++) {
            List<Map.Entry<Integer, Float>> deleteList = new ArrayList<>();
            for (Map.Entry<Integer, Float> entry:
                    benefitPerWeightToIndexList) {
                int i = entry.getKey();
                if (weight[i] <= knapsackWeight[j]) {
                    result[i][j] = 1;
                    deleteList.add(entry);
                    knapsackWeight[j] -= weight[i];
                }
            }
            benefitPerWeightToIndexList.removeAll(deleteList);
        }
        return result;
    }

    public static void main(String[] args) {
        float[] benefit = {3, 4, 2, 5, 6, 9, 1};
        float[] weight = {4, 5, 3, 4, 7, 5, 2};
        int[] knapsackWeight = {10, 10, 10};
        int[][] result = greedy(benefit, weight, knapsackWeight);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
