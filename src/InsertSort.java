public class InsertSort {

    public static void main(String[] args) {
        int[] a = {3, 5, 1, 7, 4, 6, 2, 0, 9, 8};
        //数组长度，提取出来为了提高速度
        int length = a.length;
        //要插入的数
        int temp;
        for (int i = 1; i < length; i ++){
            //要插入的数
            temp = a[i];
            //已经排序好的序列元素个数
            int j = i - 1;
            //序列从后到前循环，将大于temp的数向后移动一格
            while (j >= 0 && temp < a[j]){
                a[j + 1] = a[j];
                j --;
            }
            //插入
            a[j + 1] = temp;
        }

        for (int i:
             a) {
            System.out.print(i);
        }
    }
}
