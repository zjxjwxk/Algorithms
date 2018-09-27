
public class StableMarriage {

    public static void getSolution(int[][] boys, int[][] girls) {
        // 男生配对的女生
        int[] gfOfBoy = new int[boys.length];
        // 女生配对的男生
        int[] bfOfGirl = new int[girls.length];

        // 初始化配对情况
        for (int i = 0; i < boys.length; i++) {
            gfOfBoy[i] = -1;
            bfOfGirl[i] = -1;
        }
        // 统计已完成配对个数
        int count = 0;
        // 当存在未配对的男生时
        while (count < boys.length) {
            for (int i = 0; i < boys.length; i++) {
                // 该男生未配对时
                if (gfOfBoy[i] == -1) {
                    // 遍历男生的意向女生
                    for (int j = 0; j < boys[0].length; j++) {
                        // 该女生未配对，该男生与该女生配对成功
                        if (bfOfGirl[boys[i][j]] == -1) {
                            gfOfBoy[i] = boys[i][j];
                            bfOfGirl[boys[i][j]] = i;
                            count ++;
                            break;
                        }
                        // 该女生已配对，比较该男生与该女生当前男友的优先级
                        else {
                            int priority1 = 0, priority2 = 0;
                            // 获得该男生的优先级
                            for (; priority1 < girls[0].length; priority1++) {
                                if (i == girls[boys[i][j]][priority1]) {
                                    break;
                                }
                            }
                            // 获得该女生男友的优先级
                            for (; priority2 < girls[0].length; priority2 ++) {
                                if (bfOfGirl[boys[i][j]] == girls[boys[i][j]][priority2]) {
                                    break;
                                }
                            }
                            // 当该男生比该女生当前男友的优先级高时
                            if (priority1 < priority2) {
                                // 该女生当前男友成为单身
                                gfOfBoy[bfOfGirl[boys[i][j]]] = -1;
                                // 该男生与该女生配对成功
                                gfOfBoy[i] = boys[i][j];
                                bfOfGirl[boys[i][j]] = i;
                                break;
                            }
                        }
                    }
                }
            }
        }
        // 输出配对结果
        for (int i = 0; i < gfOfBoy.length; i++) {
            System.out.println("男生" + i + "和女生" + gfOfBoy[i] + "配对");
        }
    }

    public static void main(String[] args) {
        int[][] boys = {{1, 0, 2}, {1, 2, 0}, {2, 1, 0}};
        int[][] girls = {{1, 2, 0}, {2, 0, 1}, {1, 2, 0}};

        getSolution(boys, girls);
    }
}
