package com.ycdage.algorithm;

import java.math.BigDecimal;

/**
 * https://leetcode-cn.com/problems/bulb-switcher/submissions/
 */
public class BulbSwitch {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int i = bulbSwitch(99999);
        long l1 = System.currentTimeMillis();
        String time = new BigDecimal((l1 - l)).divide(new BigDecimal(1000)).setScale(3).toString();
        System.out.println(time + " " + i);
    }

    private static int bulbSwitch(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int total = 1;
        for (int i = 3; i <= n; i++) {
            total = total + (isLight(i) ? 1 : 0);
        }
        return total;
    }

    private static boolean isLight(int num) {
        if (num <= 3) {
            return false;
        }
        int halfNum = num / 2;
        int total = 1;
        for (int i = 2; i <= halfNum; i++) {
            if (num % i == 0) {
                halfNum = num / i;
                if (halfNum != i) {
                    total = total + 2;
                } else {
                    total = total + 1;
                }
            }
        }
        return total % 2 == 0;
    }
}
