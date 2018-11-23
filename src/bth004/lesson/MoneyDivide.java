package bth004.lesson;

import java.util.HashMap;
import java.util.Map;

/**
 * 获得某一金额的最少数量的零钱组合
 * @author zjxjwxk
 */
public class MoneyDivide {

    private static final int[] COIN = {100, 25, 10, 5, 1};

    public static Map<Integer, Integer> divide(int money) {
        Map<Integer, Integer> moneyList = new HashMap<>(5);
        for (int coin : COIN) {
            int count = money / coin;
            int value = coin * count;
            moneyList.put(coin, count);
            if (money - value < 0) {
                continue;
            }
            money -= value;
        }
        return moneyList;
    }

    public static void main(String[] args) {
        int money = 36;
        Map<Integer, Integer> result = divide(money);
        System.out.println(result);
    }
}
