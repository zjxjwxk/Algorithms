package bth004.assignment1;

import java.util.*;

/**
 * @author zjxjwxk
 */
public class MultipleKnapsackProblem {

    /**
     * Greedy algorithm for MKP
     * @param benefit the benefit of items
     * @param weight the weight of items
     * @param knapsackWeight the remaining weight of knapsacks
     * @return the relation of items to knapsacks
     */
    public static int[][] greedy(float[] benefit, float[] weight, int[] knapsackWeight) {
        // number of items
        int itemLen = benefit.length;
        // number of knapsacks
        int knapsackLen = knapsackWeight.length;
        // key: index of item, value: benefit / weight of item
        Map<Integer, Float> benefitPerWeightMap = new TreeMap<>();
        // if item i is included in knapsack j, result[i][j] = 1, otherwise result[i][j] = 0
        int[][] result = new int[itemLen][knapsackLen];
        // initialize the map
        for (int i = 0; i < itemLen; i++) {
            benefitPerWeightMap.put(i, (benefit[i] / weight[i]));
        }

        // the value comparator for sorting the entrySet in the map by value in descending order (using lambda)
        Comparator<Map.Entry<Integer, Float>> valueComparator = (o1, o2) -> o2.getValue().compareTo(o1.getValue());
        // convert the map to list to sort it by value comparator
        List<Map.Entry<Integer, Float>> benefitPerWeightList = new ArrayList<>(benefitPerWeightMap.entrySet());
        benefitPerWeightList.sort(valueComparator);

        // put items in list into knapsacks
        for (int j = 0; j < knapsackLen; j++) {
            // the items have been put in and ready to be delete
            List<Map.Entry<Integer, Float>> deleteList = new ArrayList<>();
            for (Map.Entry<Integer, Float> entry:
                    benefitPerWeightList) {
                int i = entry.getKey();
                if (weight[i] <= knapsackWeight[j]) {
                    result[i][j] = 1;
                    deleteList.add(entry);
                    knapsackWeight[j] -= weight[i];
                }
            }
            // delete the items have been put in
            benefitPerWeightList.removeAll(deleteList);
        }
        return result;
    }

    /**
     * Neighborhood Search for MKP
     * @param benefit the benefit of items
     * @param weight the weight of items
     * @param knapsackWeight the remaining weight of knapsacks
     * @return the relation of items to knapsacks
     */
    public static int[][] neighborhoodSearch(float[] benefit, float[] weight, int[] knapsackWeight){

        /*
        Using greedy algorithm to put items in knapsacks
         */

        // number of items
        int itemLen = benefit.length;
        // number of knapsacks
        int knapsackLen = knapsackWeight.length;
        // key: index of item, value: benefit / weight of item
        Map<Integer, Float> benefitPerWeightToIndexMap = new TreeMap<>();
        // if item i is included in knapsack j, result[i][j] = 1, otherwise result[i][j] = 0
        int[][] result = new int[itemLen][knapsackLen];
        // initialize the map
        for (int i = 0; i < itemLen; i++) {
            benefitPerWeightToIndexMap.put(i, (benefit[i] / weight[i]));
        }

        // the value comparator for sorting the entrySet in the map by value in descending order (using lambda)
        Comparator<Map.Entry<Integer, Float>> valueComparator = (o1, o2) -> o2.getValue().compareTo(o1.getValue());
        // convert the map to list to sort it by value comparator
        List<Map.Entry<Integer, Float>> benefitPerWeightToIndexList = new ArrayList<>(benefitPerWeightToIndexMap.entrySet());
        benefitPerWeightToIndexList.sort(valueComparator);

        printBenefitPerWeightList(benefitPerWeightToIndexList);

        int greedyTotalBenefit = 0;

        // put items in list into knapsacks
        for (int j = 0; j < knapsackLen; j++) {
            // the items have been put in and ready to be delete
            List<Map.Entry<Integer, Float>> deleteList = new ArrayList<>();
            for (Map.Entry<Integer, Float> entry:
                    benefitPerWeightToIndexList) {
                int i = entry.getKey();
                if (weight[i] <= knapsackWeight[j]) {
                    result[i][j] = 1;
                    greedyTotalBenefit += benefit[i];
                    deleteList.add(entry);
                    knapsackWeight[j] -= weight[i];
                }
            }
            // delete the items have been put in
            benefitPerWeightToIndexList.removeAll(deleteList);
        }
        // After greedy algorithm
        System.out.println("------After greedy algorithm------");
        printKnapsackWeight(knapsackWeight);
        printItemsNotIncluded(benefitPerWeightToIndexList);
        System.out.println("Total benefit:" + greedyTotalBenefit);
        printResult(result);

        /*
        Search neighborhood
         */

        int neighborTotalBenefit = greedyTotalBenefit;

        // traversing knapsacks
        for (int j1 = 0; j1 < knapsackLen - 1; j1++) {
            for (int j2 = j1 + 1; j2 < knapsackLen; j2++) {
                // traversing items in the two knapsacks
                for (int i1 = 0; i1 < itemLen; i1++) {
                    for (int i2 = 0; i2 < itemLen; i2++) {
                        // judge if item i1 and item i2 exists in knapsack j1 and j2 respectively
                        if (result[i1][j1] == 1 && result[i2][j2] == 1) {
                            // tempList for update benefitPerWeightToIndexList in iteration
                            List<Map.Entry<Integer, Float>> tempList = new ArrayList<>(benefitPerWeightToIndexList);
                            // traversing items that have not been included
                            for (Map.Entry<Integer, Float> entry:
                                 benefitPerWeightToIndexList) {
                                int i3 = entry.getKey();
                                // judge if the neighborhood solution is feasible
                                if (knapsackWeight[j2] + weight[i2] - weight[i1] >= 0
                                        && knapsackWeight[j1] + weight[i1] - weight[i3] >= 0) {
                                    // judge if the neighborhood solution is better
                                    if (neighborTotalBenefit - benefit[i2] + benefit[i3] > neighborTotalBenefit) {
                                        // change the current solution to the neighborhood solution
                                        result[i1][j1] = 0;
                                        result[i1][j2] = 1;

                                        result[i2][j2] = 0;

                                        result[i3][j1] = 1;

                                        tempList.remove(entry);
                                        int finalI2 = i2;
                                        tempList.add(new Map.Entry<Integer, Float>() {
                                            @Override
                                            public Integer getKey() {
                                                return finalI2;
                                            }

                                            @Override
                                            public Float getValue() {
                                                return benefit[finalI2] / weight[finalI2];
                                            }

                                            @Override
                                            public Float setValue(Float value) {
                                                return null;
                                            }
                                        });

                                        knapsackWeight[j1] += weight[i1] - weight[i3];
                                        knapsackWeight[j2] += weight[i2] - weight[i1];

                                        neighborTotalBenefit = (int) (neighborTotalBenefit - benefit[i2] + benefit[i3]);

                                        System.out.println("------Find a better solution------");
                                        System.out.println("Remove item" + i2 + " in " + "knapsack" + j2 + ", move item" + i1 + " from knapsack" + j1 + " to " + "knapsack" + j2 + " and put item" + i3 + " in knapsack" + j1);
                                        printKnapsackWeight(knapsackWeight);
                                        printItemsNotIncluded(tempList);
                                        System.out.println("Total benefit:" + neighborTotalBenefit);
                                        printResult(result);
                                    }
                                }
                            }
                            // update the list after traversing the list
                            benefitPerWeightToIndexList = tempList;
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void printResult(int[][] result) {
        System.out.println("Result:");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printKnapsackWeight(int[] knapsackWeight) {
        System.out.print("knapsackWeight: ");
        for (int i = 0; i < knapsackWeight.length; i++) {
            System.out.print("Knapsack" + i + ": " + knapsackWeight[i] + "  ");
        }
        System.out.println();
    }

    public static void printItemsNotIncluded(List<Map.Entry<Integer, Float>> list) {
        System.out.print("Items not included: ");
        for (Map.Entry<Integer, Float> entry :
                list) {
            System.out.print("Item" + entry.getKey() + " ");
        }
        System.out.println();
    }

    public static void printBenefitPerWeightList(List<Map.Entry<Integer, Float>> list) {
        System.out.println("Sorted Benefit/Weight List: ");
        for (Map.Entry<Integer, Float> entry :
                list) {
            System.out.println("Item" + entry.getKey() + " (index: " + entry.getKey() + ", benefit/weight: " + entry.getValue() + ")");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        /*
        Random items and knapsacks
         */
        System.out.println("------Random items and knapsacks------");
        int itemLen = 10;
        int knapsackLen = 4;
        float[] benefit = new float[itemLen];
        float[] weight = new float[itemLen];
        int[] knapsackWeight = new int[knapsackLen];

        Random random = new Random();
        for (int i = 0; i < itemLen; i++) {
            // random benefit: 1 ~ 10
            benefit[i] = random.nextInt(9) + 1;
            // random weight: 1 ~ 10
            weight[i] = random.nextInt(9) + 1;
        }
        for (int i = 0; i < knapsackLen; i++) {
            // random knapsack weight: 5 ~ 15
            knapsackWeight[i] = random.nextInt(10) + 5;
        }

        /*
        Print items and knapsacks
         */
        System.out.print("Items benefit:");
        for (int i = 0; i < itemLen; i++) {
            System.out.print(benefit[i] + " ");
        }
        System.out.println();

        System.out.print("Items weight:");
        for (int i = 0; i < itemLen; i++) {
            System.out.print(weight[i] + " ");
        }
        System.out.println();

        System.out.print("Knapsacks weight:");
        for (int i = 0; i < knapsackLen; i++) {
            System.out.print(knapsackWeight[i] + " ");
        }
        System.out.println();

        // neighborhood search
        neighborhoodSearch(benefit, weight, knapsackWeight);

    }
}
