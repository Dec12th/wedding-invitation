package com.baily.template.springcloud.eureka.test;

/**
 * @ClassName: Test2
 * @Description: 一个大小为n的数组，里面的数都属于范围[0, n-1]，有不确定的重复元素，找到至少一个重复元素，要求O(1)空间和O(n)时间
 * 2,1,4,7,5
 * @author:YB
 * @date:2018年07月18日 15:22
 */
public class Test2 {
    public static int isRepeat(int a[],int n) {
        if (n <= 0) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            if (a[i]!=i) {
                if (a[i]==a[a[i]]) {
                    return a[i];
                }
                int temp = a[i];
                a[i] = a[a[i]];
                a[a[i]] = temp;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = new int[]{1,2,2,1};
        System.out.println(isRepeat(a,4));

        //斐波那契数列
//        for (int i = 0; i < 10; i++) {
//            System.out.print(fib(i)+" ");
//        }
    }

    /**
     * 斐波那契数列
     * @param x
     * @return
     */
    public static int fib(int x) {
        int result;
        if (x==0) {
            result = x;
        } else  if (x<2) {
            result = 1;
        } else {
            result =fib(x-1)+fib(x-2);
        }
        return result;
    }
}
