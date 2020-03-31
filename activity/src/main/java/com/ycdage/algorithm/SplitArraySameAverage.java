package com.ycdage.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定的整数数组 A ，我们要将 A数组 中的每个元素移动到 B数组 或者 C数组中。
 * （B数组和C数组在开始的时候都为空）
 * 当且仅当在我们的完成这样的移动后，可使得B数组的平均值和C数组的平均值相等，并且B数组和C数组都不为空。
 * <p>
 * https://leetcode-cn.com/problems/split-array-with-same-average/
 */
public class SplitArraySameAverage {
    public static void main(String[] args) {
        int[] A = {2,0,5,6,16,12,15,12,4};
        System.out.println(splitArraySameAverage(A));
    }

    private static boolean splitArraySameAverage(int[] A) {
        List<Integer> nums = new ArrayList<>();
        double totalNum = calcTotalNum(A, nums);
        double average = totalNum / A.length;
        if (A.length == 2 && A[0] != (int)average && A[1] != (int)average) {
            return false;
        }
        return checkAllNumForAverage(nums, average);
    }

    private static boolean checkAllNumForAverage(List<Integer> nums, double average) {
        while (true) {
            if(nums.size() <= 0){
                return true;
            }
            int zeroValue = nums.get(0);
            if(zeroValue == 0){
                nums.remove(0);
                continue;
            }
            if(zeroValue == average){
                nums.remove(0);
            }else {
                int target = (int) Math.abs(average*2 - zeroValue);
                int targetIndex = nums.indexOf(target);
                if (targetIndex != -1 && targetIndex > 0) {
                    nums.remove(0);
                    nums.remove(targetIndex - 1);
                }else{
                    return false;
                }
            }
        }
    }

    private static int calcTotalNum(int[] a, List<Integer> nums) {
        int total = 0;
        for (int i : a) {
            total = total + i;
            nums.add(i);
        }
        return total;
    }
}
