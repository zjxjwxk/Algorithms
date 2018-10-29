public class BTH004Exercise2 {

    public static final int A = 2;

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

    public static void main(String[] args) {

        int a[] = {4, 2, 7, 0, 3, 8, 11, 1};
        int result[] = findMaxAndMin(a);
        System.out.println("max:" + result[0]);
        System.out.println("min:" + result[1]);
    }
}
