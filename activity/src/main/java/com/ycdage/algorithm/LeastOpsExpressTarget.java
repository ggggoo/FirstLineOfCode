package com.ycdage.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/least-operators-to-express-number/
 *
 * 给定一个正整数 x，我们将会写出一个形如 x (op1) x (op2) x (op3) x ... 的表达式，
 * 其中每个运算符 op1，op2，… 可以是加、减、乘、除（+，-，*，或是 /）之一。
 * 例如，对于 x = 3，我们可以写出表达式 3 * 3 / 3 + 3 - 3，该式的值为 3 。
 * 在写这样的表达式时，我们需要遵守下面的惯例：
 *
 * 除运算符（/）返回有理数。
 * 任何地方都没有括号。
 * 我们使用通常的操作顺序：乘法和除法发生在加法和减法之前。
 * 不允许使用一元否定运算符（-）。例如，“x - x” 是一个有效的表达式，因为它只使用减法，但是 “-x + x” 不是，因为它使用了否定运算符。
 * 我们希望编写一个能使表达式等于给定的目标值 target 且运算符最少的表达式。返回所用运算符的最少数量。
 */
public class LeastOpsExpressTarget {
    static Map<String, Integer> memo;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int i = leastOpsExpressTarget(3, 929);
        long l1 = System.currentTimeMillis();
        String time = new BigDecimal((l1 - l)).divide(new BigDecimal(1000), 3).toString();
        System.out.println(time + "  " + i);
//        memo = new HashMap();
//        System.out.println( dp(0, 929,3) - 1);
    }

    private static int leastOpsExpressTarget(int x, int target) {
        int i = leastOpsExpressTarget2(x, target);
        int num = target / x;
        if (target % x != 0) {
            int second1 = leastOpsExpressTarget(x, (num + 1) * x);
            int second2 = leastOpsExpressTarget2(x, (num + 1) * x - target);
            int i1 = second1 + second2 + 1;
            return Math.min(i, i1);
        } else {
            return i;
        }
    }


    public static int dp(int i, int target, int x) {
        String code = "" + i + "#" + target;
        if (memo.containsKey(code))
            return memo.get(code);

        int ans;
        if (target == 0) {
            ans = 0;
        } else if (target == 1) {
            ans = cost(i);
            System.out.println("+3/3");
        } else if (i >= 39) {
            ans = target + 1;
        } else {
            int t = target / x;
            int r = target % x;
            int i1 = r * cost(i) + dp(i + 1, t, x);
            ans = Math.min(i1,
                    (x - r) * cost(i) + dp(i + 1, t + 1, x));
            if (ans == i1) {
                System.out.println("*" + x + "+" + target);
            } else {
                System.out.println("*" + x + "-" + target);
            }
        }

        memo.put(code, ans);
        return ans;
    }

    public static int cost(int x) {
        return x > 0 ? x : 2;
    }

    private static int leastOpsExpressTarget(int x, int target, List<Integer> targetRemoveList) {
        if (null == targetRemoveList) {
            targetRemoveList = new ArrayList<>();
        }
        int leastCount = 2 * target - 1;
        if (x == target) {
            return 0;
        }
        int i = 0;
        do {
            double pow = Math.pow(x, i);
            if (pow > target) {
                break;
            } else if (pow == target) {
                return i - 1;
            } else {
                i = i + 1;
            }
        } while (true);

        //x的n次方再加
        int pow1 = (int) Math.pow(x, i - 1);
        int targetAdd = target - pow1;
        int leastAdd;
        if (targetAdd == x) {
            leastAdd = i - 2 + 1;
        } else if (targetAdd > x) {
            List<Integer> mList = new ArrayList<>(targetRemoveList);
            leastAdd = i - 2 + 1 + leastOpsExpressTarget(x, targetAdd, mList);
        } else {
            leastAdd = Math.min(i - 2 + 1 + targetAdd * 2 - 1, i - 2 + 1 + (x - targetAdd) * 2 - 1 + 1);
        }
        leastCount = Math.min(leastAdd, leastCount);

        //x的n次方再减
        int pow2 = (int) Math.pow(x, i);
        int targetRemove = pow2 - target;
        if (!targetRemoveList.contains(targetRemove)) {
            targetRemoveList.add(targetRemove);
            int leastRemove;
            if (targetRemove == x) {
                leastRemove = i - 1 + 1;
            } else if (targetRemove > x) {
                List<Integer> mList = new ArrayList<>(targetRemoveList);
                leastRemove = i - 1 + 1 + leastOpsExpressTarget(x, targetRemove, mList);
            } else {
                leastRemove = Math.min(i - 1 + 1 + targetRemove * 2 - 1, i - 1 + 1 + (x - targetRemove) * 2 - 1 + 1);
            }
            leastCount = Math.min(leastRemove, leastCount);
        }
        return leastCount;
    }

    private static int leastOpsExpressTarget2(int x, int target) {
        if (x == target) {
            return 0;
        }

        int leftNum = target % x;
        int next = target / x;
        List<Integer> mList = new ArrayList();
        do {
            if (next < x) {
                mList.add(leftNum);
                if (next != 0) {
                    mList.add(next);
                }
                break;
            } else {
                mList.add(leftNum);
                leftNum = next % x;
                next = next / x;
            }
        } while (true);


        int mResult = 0;
        for (int index = mList.size() - 1; index >= 0; index--) {
            int i = mList.get(index);
            int addJia = i - 1;
            int addChu = i;
            int subsJia = 1 + (x - i - 1);
            int subsChu = x - i;

            if (index == 0) {
                if (i != 0) {
                    mResult = mResult + Math.min(addJia + addChu, subsJia + subsChu);
                    if (target > x) {
                        mResult = mResult + 1;
                    }
                }
            } else if (i != 0) {
                int a = (addJia + 1) * (index) - addChu + addJia;
                int b = (subsJia + 1) * (index) - subsChu + subsJia;
                if (index == mList.size() - 1) {
                    mResult = mResult + Math.min(a, b);
                } else {
                    mResult = mResult + Math.min(a, b) + 1;
                }
            }
        }
        return mResult;

//        int resultCount = 0;
//        int addAndSub = 0;
//        int lastDivide = 0;
//        int mAddAndSub, mDivide;

//            //加减号少，除号多
//            if (i == 0) {
//                mDivide = 0;
//                mAddAndSub = 0;
//            } else if (checkNum(addJia, addChu, subsJia, subsChu, index)) {
//                mDivide = addChu;
//                mAddAndSub = addJia;
//            } else {
//                mDivide = subsChu;
//                mAddAndSub = subsJia;
//            }
//            if (index != mList.size() - 1 && (mDivide != 0 || mAddAndSub != 0)) {
//                mAddAndSub = mAddAndSub + 1;
//            }
//
//            if (index == mList.size() - 1) {
//                resultCount = mAddAndSub + mDivide;
//            } else {
//                if (mAddAndSub != 0)
//                    resultCount = resultCount + addAndSub + 1 + mAddAndSub + mDivide - lastDivide * 2;
//                else
//                    resultCount = resultCount + addAndSub + 1 - lastDivide * 2;
//            }
//            lastDivide = mDivide;
//
//            if (index == mList.size() - 1) {
//                addAndSub = mAddAndSub;
//            } else if (mAddAndSub != 0) {
//                addAndSub = addAndSub + mAddAndSub;
//            }
//        }
//        return resultCount;
    }

    private static boolean checkNum(int addJia, int addChu, int subsJia, int subsChu, int index) {
        if (index == 0) {
            return addJia + addChu <= subsJia + subsChu;
        }
        int a = (addJia + 1) * (index) - addChu * 2 + addJia + addChu;
        int b = (subsJia + 1) * (index) - subsChu * 2 + subsJia + subsChu;
        return a <= b;
    }
}
