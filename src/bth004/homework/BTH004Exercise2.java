package bth004.homework;

/**
 * @author zjxjwxk
 */
public class BTH004Exercise2 {

    public static int[] findMaxAndMin(int[] a) {
        int len = a.length;
        int min, max;
        if (len % 2 == 1) {
            min = a[0];
            max = a[0];
            for (int i = 1; i < a.length - 1; i += 2) {
                if (a[i] < a[i + 1]) {
                    if (a[i] < min) {
                        min = a[i];
                    }
                    if (a[i + 1] > max) {
                        max = a[i + 1];
                    }
                } else{
                    if (a[i] > max) {
                        max = a[i];
                    }
                    if (a[i + 1] < min) {
                        min = a[i + 1];
                    }
                }
            }
        } else {
            if (a[0] < a[1]) {
                min = a[0];
                max = a[1];
            } else {
                min = a[1];
                max = a[0];
            }
            for (int i = 2; i < a.length - 1; i += 2) {
                if (a[i] < a[i + 1]) {
                    if (a[i] < min) {
                        min = a[i];
                    }
                    if (a[i + 1] > max) {
                        max = a[i + 1];
                    }
                } else {
                    if (a[i] > max) {
                        max = a[i];
                    }
                    if (a[i + 1] < min) {
                        min = a[i + 1];
                    }
                }
            }
        }
        return new int[]{max, min};
    }

    public static int findSecondMax(int[] a){
        int[] maxTwo = getNextArr(a);
        if (maxTwo[0] > maxTwo[1]) {
            return maxTwo[1];
        } else {
            return maxTwo[0];
        }
    }

    private static int[] getNextArr(int[] a) {
        int len = a.length;
        if (len == 2) {
            return a;
        }
        int nextArrLen = len / 2;
        int j = 0;
        int[] nextArr = new int[nextArrLen];
        for (int i = 0; i < len; i += 2) {
            if (a[i] > a[i + 1]) {
                nextArr[j++] = a[i];
            } else {
                nextArr[j++] = a[i + 1];
            }
        }
        printArr(nextArr);
        return getNextArr(nextArr);
    }

    public static void printArr(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static int findMedian(int[] a) {
        if (a[0] < a[1]) {
            if (a[2] < a[0]) {
                return a[0];
            }
            if (a[2] > a[1]) {
                return a[1];
            }
            return a[2];
        } else {
            if (a[2] > a[0]) {
                return a[0];
            }
            if (a[2] < a[1]) {
                return a[1];
            }
            return a[2];
        }
    }

    public static void main(String[] args) {

        /*
          findMaxAndMin test
         */
//        int a[] = {4, 2, 7, 0, 3, 8, 11, 1};
//        int result[] = findMaxAndMin(a);
//        System.out.println("max:" + result[0]);
//        System.out.println("min:" + result[1]);

        /*
          findSecondMax test
         */
//        int[] a = {4, 8, 1, 3, 6, 10, 7, 9, 13, 2, 15, 5, 16, 11, 14, 16};
//        System.out.println("Second max:" + findSecondMax(a));

        /*
          findMedian test
         */
        int[] a = {2, 1, 3};
        System.out.println("Median:" + findMedian(a));
    }
}
