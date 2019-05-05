package com.ycdage.algorithm;

import java.math.BigDecimal;

/**
 * https://leetcode-cn.com/problems/bulb-switcher/submissions/
 */
public class BulbSwitch {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
//        int i = getAllLight(99999);
        int i = (int) Math.floor(Math.sqrt(99999));
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
        if (num < 3) {
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
        if (total % 2 == 0) {
            System.out.println(num);
            return true;
        }
        return total % 2 == 0;
    }

    /**
     * 一个数的因子是偶数亮
     * 例如8:(2,4,8)不亮；9(3,9)亮；12(2，3，4，6，12)不亮
     * 一个数的因子总是偶数+1，除非这个数是一个数的平方
     *
     * @param n
     * @return
     */
    private static int getAllLight(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int i = 2;
        do {
            double pow = Math.pow(i, 2);
            if (pow < n) {
                i = i + 1;
            } else {
                if (pow > n) {
                    i = i - 1;
                }
                break;
            }
        } while (true);
        return i;

    }
}
