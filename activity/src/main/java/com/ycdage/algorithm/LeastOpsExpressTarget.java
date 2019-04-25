package com.ycdage.algorithm;

import java.util.ArrayList;
import java.util.List;

public class LeastOpsExpressTarget {
    private static List<Integer> targetList1 = new ArrayList<>();
    private static List<Integer> targetList2 = new ArrayList<>();

    public static void main(String[] args) {
        int i = leastOpsExpressTarget(5, 501);
        System.out.println(i);
    }

    private static int leastOpsExpressTarget(int x, int target) {
        return leastOpsExpressTarget(x, target, false, false);
    }

    private static int leastOpsExpressTarget(int x, int target, boolean add, boolean isFirst) {
        int leastCount = 2 * target - 1;
        if (x == target) {
            System.out.println("x=target");
            return 0;
        }
        int i = 0;
        do {
            double pow = Math.pow(x, i);
            if (pow > target) {
                break;
            } else {
                i = i + 1;
            }
        } while (true);

        boolean useAdd = false;
        //x的n次方再加
        int pow1 = (int) Math.pow(x, i - 1);
        int targetAdd = target - pow1;
        int leastAdd;
        if (targetAdd == x) {
            leastAdd = i - 2 + 1;
        } else if (targetAdd > x) {
            leastAdd = i - 2 + 1 + leastOpsExpressTarget(x, targetAdd, isFirst ? true : add, false);
        } else {
            leastAdd = i - 2 + 1 + targetAdd * 2 - 1;
        }
        if (leastAdd < leastCount) {
            useAdd = true;
            leastCount = leastAdd;
        }

        //x的n次方再减
        int pow2 = (int) Math.pow(x, i);
        int targetRemove = pow2 - target;
        if (checkTargetRemove(targetRemove, isFirst, add)) {
            int leastRemove;
            if (targetRemove == x) {
                leastRemove = i - 1 + 1;
            } else if (targetRemove > x) {
                leastRemove = i - 1 + 1 + leastOpsExpressTarget(x, targetRemove, isFirst ? false : add, false);
            } else {
                leastRemove = i - 1 + 1 + targetRemove * 2 - 1;
            }
            if (leastRemove < leastCount) {
                useAdd = false;
                leastCount = leastRemove;
            }
        }

        System.out.println(x + " " + target + " " + leastCount + " " + (useAdd ? "+"+targetAdd : "-"+targetRemove));
        return leastCount;
    }

    private static boolean checkTargetRemove(int targetRemove, boolean isFirst, boolean add) {
        if (isFirst) {
            targetList2.add(targetRemove);
            return true;
        } else if (add) {
            if (!targetList1.contains(targetRemove)) {
                targetList1.add(targetRemove);
                return true;
            }
        } else {
            if (!targetList2.contains(targetRemove)) {
                targetList2.add(targetRemove);
                return true;
            }
        }
        return false;
    }
}
