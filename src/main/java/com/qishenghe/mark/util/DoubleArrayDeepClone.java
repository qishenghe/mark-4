package com.qishenghe.mark.util;

/**
 * 2020/10/26
 */
public class DoubleArrayDeepClone {

    public static int[][] function (int[][] array) {

        int[][] newArr = new int[array.length][];
        for (int i = 0; i < array.length; i ++) {
            newArr[i] = array[i].clone();
        }
        return newArr;
    }

}
