package com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 0.定义一个数组，其中元素为公司的一级部门名称，按照【下标:值】格式打印出来。
 * 	    如：0:xxx, 1:xxx, 2:xxx
 * 1.将int数组【99、23、34、67、123、234、1、98】按照升序排列。
 * 2.去掉数组【1、2、3、4、2、2、1、5、6】中的重复元素。
 * 3.统计数组【1，5，6，12，1，32，5，6】中每个元素出现的次数。
 * 4.写一段代码，实现System.arraycopy方法的功能。
 */
public class Main {

    public static void main(String[] args) {
        printArr();

        int[] num = {99, 23, 34, 67, 123, 234, 1, 98};
        int[] sort = quickSort(num, 0, num.length -1);
        System.out.println(Arrays.toString(sort));

        int[] arr = {8, 8, 1, 2, 3, 4, 2, 2, 1, 5, 6};
        Object[] newArr = arrayUnique(arr);
        System.out.println(Arrays.toString(newArr));

        int[] arr2 = {1, 5, 6, 12, 1, 32, 5, 6};
        Map<Integer, Integer> newArr2 = arrayValueCount(arr2);
        System.out.println(newArr2);

        int[] arr3 = new int[10];
        arrayCopy(arr2, 2, arr3, 3, 5);
        System.out.println(Arrays.toString(arr3));
    }

    public static void printArr(){
        String[] department = new String[3];
        department[0] = "餐饮";
        department[1] = "零售";
        department[2] = "大数据";

        int len = department.length;
        String str = "";
        for (int i = 0; i < len; i++) {
            str += i + ":" + department[i];
            if (i != (len -1) ) {
                str += ", ";
            }
        }
        System.out.println(str);
    }


    /**
     * 快速排序
     * 升序排列
     * @param num
     * @param left
     * @param right
     * @return
     */
    public static int[] quickSort(int[] num, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return num;
        }
        //设置最左边的元素为基准值
        int key = num[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (num[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (num[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = key;
        quickSort(num, left, i - 1);
        quickSort(num, i + 1, right);

        return num;
    }


    /**
     * 数组去重
     * @param array
     * @return
     */
    public static Object[] arrayUnique(int[] array) {
        HashMap<Object, Object> map = new HashMap<>();
        for (Object item: array) {
            map.put(item, item);
        }
        return map.values().toArray();
    }


    /**
     * @param array
     * @return
     */
    public static Map<Integer, Integer> arrayValueCount(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer item: array) {
            if (map.containsKey(item)) {
                int val = map.get(item) + 1;
                map.put(item, val);
            } else {
                map.put(item, 1);
            }

        }
        return map;
    }


    public static void arrayCopy(int[] src, int srcPos, int[] dest, int destPos,  int length) {
        if (src == null || dest == null) {
            throw new NullPointerException();
        }

        int srcLen = src.length;
        if (srcPos > srcLen -1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int descLen = dest.length;
        if (destPos > descLen - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int len = Math.min(srcLen - srcPos + 1, descLen - destPos + 1);
        int len2 = Math.min(len, length);

        int i = 0;
        while (i < len2) {
            dest[destPos] = src[srcPos + i];
            ++destPos;
            ++i;
        }
    }

}
