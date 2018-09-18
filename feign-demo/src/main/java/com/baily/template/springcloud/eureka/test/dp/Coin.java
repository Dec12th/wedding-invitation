package com.baily.template.springcloud.eureka.test.dp;

/**
 * @ClassName: Coin
 * @Description: 硬币问题
 * @author:大贝
 * @date:2018年08月03日 20:38
 */
public class Coin {
    public static void main(String[] args) {
        int[] coins = {1, 3, 5};
        int sum = 10;
        dp(coins,sum);
    }

    public static void dp(int[] coins, int s) {
        int[] min = new int[100];
        int[] max = new int[100];
        for (int i = 0; i < min.length; i++) {
            min[i] = max[i] = 0;
        }
        for (int j = 0; j < coins.length; j++) {
            for (int i = 0; i <= s; i++) {
                if (i >= coins[j]) {
                    if (min[i - coins[j]] + 1 < min[i]) {
                        min[i] = min[i - coins[j]] + 1;//最小组合过程
                    }
                    if (max[i - coins[j]] + 1 > max[i]) {
                        max[i] = max[i - coins[j]] + 1;//最大组合过程
                    }
                }
            }
        }
    }
}
