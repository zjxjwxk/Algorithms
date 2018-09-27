
public class StableMarriage {

    public void getSolution(int[][] boys, int[][] girls) {
        int[] gfOfBoy = new int[boys.length];
        int[] bfOfGirl = new int[girls.length];

        for (int i = 0; i < boys.length; i++) {
            gfOfBoy[i] = -1;
            bfOfGirl[i] = -1;
        }
        int count = 0;
        while (count < boys.length) {
            for (int i = 0; i < boys.length; i++) {
                if (gfOfBoy[i] == -1) {
                    for (int j = 0; j < girls.length; j++) {
                        if (bfOfGirl[j] == -1) {
                            gfOfBoy[i] = j;
                            bfOfGirl[j] = i;
                            break;
                        } else if (bfOfGirl[])

                    }
                }
            }
        }
    }
}
