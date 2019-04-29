package com.ycdage.algorithm;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * <p>
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，
 * 我们希望索引的数字  i < j 且有 (time[i] + time[j]) % 60 == 0。
 */
public class NumPairsDivisibleBy60 {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
//        int[] a = {30, 20, 150, 100, 40};
        int[] a = {60,60,60};
        System.out.println(numPairsDivisibleBy60(a));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static int numPairsDivisibleBy60(int[] time) {
        if (time.length < 2) {
            return 0;
        }
        int totalCount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int mTime : time) {
            int i = mTime % 60;
            int key;
            if (i != 0) {
                key = 60 - i;
            } else {
                key = 0;
            }
            if (map.containsKey(key)) {
                totalCount = totalCount + map.get(key);
            }
            map.put(i,map.getOrDefault(i,0) + 1);

        }
        return totalCount;
    }

    private static void addNumToMap(HashMap<Integer, Integer> map, int i) {
        Integer integer = map.get(i);
        if(integer == null){
            integer = 0;
        }
        map.put(i,integer + 1);
    }
}
