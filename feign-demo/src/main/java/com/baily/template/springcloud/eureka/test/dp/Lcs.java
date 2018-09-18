package com.baily.template.springcloud.eureka.test.dp;

/**
 * @ClassName: Lcs
 * @Description:
 * @author:大贝
 * @date:2018年08月03日 21:04
 */
public class Lcs {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "cddd";
        System.out.println(lcs(str1,3,str2,4));
    }

    /**
     * 递归方式实现求两个字符串最长公共字序列的长度
     *
     * @param str1 第一个字符串
     * @param m    第一个字符串需要比较的长度
     * @param str2 第二个字符串
     * @param n    第一个字符串需要比较的长度
     * @return
     */
    public static int lcs(String str1, int m, String str2, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        //构建一个m + 1行 n + 1列的辅助二维数组,里面的元素初始值都为0
        int[][] arr = new int[m + 1][n + 1];
        //依次求二维数组中的值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //当最后一个字符相等时等于左上角的元素加1
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = getMax(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
        return arr[m][n];//二维数组右下角的元素即我们需要的值
    }


    public static int getMax(int num1, int num2) {
        return num1 > num2 ? num1 : num2;
    }
}
