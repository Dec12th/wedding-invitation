package com.baily.template.springcloud.eureka.test.sort;

/**
 * @ClassName: SwapSort
 * @Description: 交换排序
 * 1.冒泡排序
 * 2.快速排序
 * @author:YB
 * @date:2018年07月23日 14:13
 */
public class SwapSortCustom {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 49, 76, 13};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
//        bubbleSort(a);
        quick(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 冒泡排序
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param a
     */
    private static void quick(int[] a) {
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int mid = getMiddle(a, low, high);
            quickSort(a, low, mid - 1);
            quickSort(a, mid + 1, high);
        }
    }

    private static int getMiddle(int[] a, int low, int high) {
        int temp = a[low];
        while (low < high) {
            while (low < high && a[high] >= temp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = temp;
        return low;
    }
}
