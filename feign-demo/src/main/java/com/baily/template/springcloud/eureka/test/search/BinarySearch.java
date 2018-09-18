package com.baily.template.springcloud.eureka.test.search;

/**
 * @ClassName: BinarySearch
 * @Description: 二分查找
 * @author:YB
 * @date:2018年07月24日 11:15
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] a = {1,2,3,5,6,9};
        System.out.println(binarySearchWithRecursion(a,5));
        System.out.println(binarySearchWithWhile(a,5));
    }

    /**
     * 非递归
     * @param array
     * @param a
     * @return
     */
    public static int binarySearchWithWhile(int []arr,int a){
        if (arr==null || arr.length<1) {
            return -1;
        }
        int li = 0;
        int hi = arr.length-1;
        int mid = 0;
        while (li<=hi) {
            mid = (li+hi)/2;
            if (a==arr[mid]) {
                return mid+1;
            } else if (a>arr[mid]) {
                li = mid+1;
            } else {
                hi = mid -1;
            }
        }
        return -1;
    }

    public static int binarySearchWithRecursion(int[] arr,int a) {
        return binarySearchWithRecursion(arr,a,0,arr.length-1);
    }

    /**
     * 递归
     * @param arr
     * @param a
     * @param li
     * @param hi
     * @return
     */
    private static int binarySearchWithRecursion(int[] arr,int a,int li,int hi) {
        if (arr==null || arr.length<1) {
            return -1;
        }
        if (li<=hi) {
            int mid = (li+hi)/2;
            if (a==arr[mid]) {
                return mid+1;
            } else if (a>arr[mid]) {
                return binarySearchWithRecursion(arr,a,mid+1,hi);
            } else {
                return binarySearchWithRecursion(arr,a,li,mid-1);
            }
        }
        return -1;
    }
}
