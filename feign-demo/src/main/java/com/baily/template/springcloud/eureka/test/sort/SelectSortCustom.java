package com.baily.template.springcloud.eureka.test.sort;

/**
 * @ClassName: SelectSort
 * @Description: 选择排序
 * 1.直接选择排序
 * 2.堆排序
 * @author:YB
 * @date:2018年07月19日 23:24
 */
public class SelectSortCustom {
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
        for (int i = 0; i < a.length - 1; i++) {
            buildMaxHeap(a, a.length - 1 - i);
            swap(a, 0, a.length - 1 - i);
        }
    }

    /**
     * 对data数组从0到lastIndex建大顶堆
     *
     * @param data
     * @param lastIndex
     */
    private static void buildMaxHeap(int[] data, int lastIndex) {
        for (int i = (lastIndex-1)/2; i >=0 ; i--) {
            int k = i;
            while (2*k+1<=lastIndex) {
                int biggerIndex = 2*k+1;
                if (biggerIndex<lastIndex) {
                    if (data[biggerIndex]<data[biggerIndex+1]) {
                        biggerIndex++;
                    }
                }
                if (data[k]<data[biggerIndex]) {
                    swap(data,k,biggerIndex);
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 交换
     *
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
