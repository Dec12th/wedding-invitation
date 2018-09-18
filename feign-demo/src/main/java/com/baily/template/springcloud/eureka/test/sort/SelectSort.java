package com.baily.template.springcloud.eureka.test.sort;

import java.util.Arrays;

/**
 * @ClassName: SelectSort
 * @Description: 选择排序
 * 1.直接选择排序
 * 2.堆排序
 * @author:YB
 * @date:2018年07月19日 23:24
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
//        directSelectSort(a);
        heapSort(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 直接选择排序
     *
     * @param a
     */
    public static void directSelectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int n = i;
            for (int j = i; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    n = j;
                }
            }
            a[n] = a[i];
            a[i] = min;
        }
    }

    /**
     * 堆排序
     *
     * @param a
     */
    public static void heapSort(int[] a) {
        // 循环建堆
        for (int i = 0; i < a.length - 1; i++) {
            // 建堆
            buildMaxHeap(a, a.length - 1 - i);
            // 交换堆顶和最后一个元素
            swap(a, 0, a.length - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * 对data数组从0到lastIndex建大顶堆
     * @param data
     * @param lastIndex
     */
    private static void buildMaxHeap(int[] data, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // k保存正在判断的节点
            int k = i;
            // 如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                // k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                // 如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    // 若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        // biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                // 如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    // 交换他们
                    swap(data, k, biggerIndex);
                    // 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 交换
     * @param data
     * @param i
     * @param j
     */
    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
