package com.baily.template.springcloud.eureka.test.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RadixSort
 * @Description: 基数排序
 * @author:YB
 * @date:2018年07月23日 14:16
 */
public class RadixSortCustom {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 23, 13};
        System.out.println("排序之前：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        sort(a);
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }

    private static void sort(int[] array) {
        //找最大值
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        //计算次数
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        }

        //新建10个队列
        List<ArrayList> queue = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            queue.add(new ArrayList());
        }

        //进行times次分配和手机
        for (int i = 0; i < times; i++) {
            //分配
            for (int j = 0; j < array.length; j++) {
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList queue1 = queue.get(x);
                queue1.add(array[j]);
                queue.set(x,queue1);
            }

            //收集
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (queue.get(j).size()>0) {
                    ArrayList<Integer> queue2 = queue.get(j);
                    array[count] = queue2.get(0);
                    queue2.remove(0);
                    count++;
                }
            }
        }
    }
}
