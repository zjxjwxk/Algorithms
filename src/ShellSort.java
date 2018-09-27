public class ShellSort {

    public static void main(String[] args){
        int[] a = {3, 5, 1, 7, 4, 6, 2, 0, 9, 8};
        int d = a.length;
        while (d != 0){
            d /= 2;
            //d为分的组数
            for (int x = 0; x < d; x ++){
                //组中的元素，从第二个数开始
                for (int i = x + d; i < a.length; i += d){
                    //j为有序序列最后一位的位数
                    int j = i - d;
                    int temp = a[i];
                    //从后往前遍历
                    for (; j >= 0 && temp < a[j]; j -= d){
                        //向后移动d位
                        a[j + d] = a[j];
                    }
                    //插入
                    a[j + d] = temp;
                }
            }
        }
        for (int i :
                a) {
            System.out.print(i);
        }
    }
}
