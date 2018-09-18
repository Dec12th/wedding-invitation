package com.baily.template.springcloud.eureka.test.sort;

/**
 * @ClassName: MergeSort
 * @Description: 归并排序
 * @author:YB
 * @date:2018年07月23日 14:15
 */
public class MergeSortCustom {

    public static void main(String[] args) {
        int[] a = {49, 38};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        sort(a, 0, a.length - 1);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * 归并排序 简介:将两个（或两个以上）有序表合并成一个新的有序表
     * 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列 时间复杂度为O(nlogn) 稳定排序方式
     *
     * @param nums 待排序数组
     * @return 输出有序数组
     */
    public static int[] sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sort(nums, low, mid);
            sort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
        return nums;
    }

    public static void merge(int[] nums, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = 0;
        int[] temps = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temps[k++] = nums[i++];
            } else {
                temps[k++] = nums[j++];
            }
        }
        while (i<=mid) {
            temps[k++] = nums[i++];
        }
        while (j<=high) {
            temps[k++] = nums[j++];
        }

        for (int x = 0; x< temps.length; x++) {
            nums[x+low] = temps[x];
        }
    }
}
