package com.ycdage.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/expression-add-operators/
 */
public class AddOperators {
    private static int Target;
    private static List<String> mList;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        addOperators("123456789", 45);
        long l1 = System.currentTimeMillis();
        String time = new BigDecimal((l1 - l)).divide(new BigDecimal(1000)).setScale(3).toString();
        if (mList.size() == 0) {
            System.out.println(time+" result = null");
            return;
        }
        for (String s : mList) {
            System.out.println(time+" result = " + s);
        }
    }

    private static List<String> addOperators(String num, int target) {
        mList = new ArrayList<>();
        Target = target;
        for (int i = 0; i < num.length() - 1; i++) {
            addOperatorInNum(new StringBuffer(num), i);
        }
        return mList;
    }

    private static void addOperatorInNum(StringBuffer sb, int startIndex) {
        if (startIndex >= sb.length() - 1) {
            calculateNum(sb);
            return;
        }
        startIndex = startIndex + 1;
        for (int i = startIndex; i <= sb.length() - 1; i = i + 1) {
            for (int k = 1; k <= 4; k++) {
                StringBuffer mSb = new StringBuffer(sb);
                String operator = null;
                switch (k) {
                    case 1:
                        operator = " + ";
                        addOperatorInNum(mSb.insert(i, operator), i + 3);
                        break;
                    case 2:
                        operator = " - ";
                        addOperatorInNum(mSb.insert(i, operator), i + 3);
                        break;
                    case 3:
                        operator = " * ";
                        addOperatorInNum(mSb.insert(i, operator), i + 3);
                        break;
                    default:
                        if(mSb.charAt(i-1) != '0') {
                            addOperatorInNum(mSb, i + 3);
                        }
                        break;
                }
            }
        }
    }

    private static void calculateNum(StringBuffer sb) {
        String[] split = sb.toString().split(" ");
        List<String> nums = new ArrayList<>();
        for (String s : split) {
            nums.add(s);
        }
        int i = nums.indexOf("*");
        while (i != -1) {
            String a = new BigDecimal(nums.get(i - 1)).multiply(new BigDecimal(nums.get(i + 1))).toString();
            nums.remove(i - 1);
            nums.remove(i - 1);
            nums.remove(i - 1);
            nums.add(i - 1, a);
            i = nums.indexOf("*");
        }
        BigDecimal bigDecimal = new BigDecimal(nums.get(0));
        for (int index = 1; index < nums.size() - 1; index = index + 2) {
            switch (nums.get(index)) {
                case "+":
                    bigDecimal = bigDecimal.add(new BigDecimal(nums.get(index + 1)));
                    break;
                case "-":
                    bigDecimal = bigDecimal.subtract(new BigDecimal(nums.get(index + 1)));
                    break;
                default:
                    break;
            }
        }
//        System.out.println(sb + "计算:" + bigDecimal.toString());
        if (bigDecimal.compareTo(new BigDecimal(Target)) == 0) {
            String resultString = sb.toString().replace(" ", "");
            if (!mList.contains(resultString))
                mList.add(resultString);
        }
    }


    public List<String> addOperators2(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return res;
        }

        help(num, target, 0, 0, 0, "", res);
        return res;
    }

    private void help(String num, int target, long curRes, long diff, int start, String curExp, List<String> expressions) {
        if (start == num.length() && (long)target == curRes) {
            expressions.add(new String(curExp));
        }

        for (int i = start; i < num.length(); i++) {
            String cur = num.substring(start, i + 1);
            if (cur.length() > 1 && cur.charAt(0) == '0') {
                break;
            }

            if (curExp.length() > 0) {
                help(num, target, curRes + Long.valueOf(cur), Long.valueOf(cur), i + 1, curExp + "+" + cur, expressions);
                help(num, target, curRes - Long.valueOf(cur), -Long.valueOf(cur), i + 1, curExp + "-" + cur, expressions);
                help(num, target, (curRes - diff) + diff * Long.valueOf(cur), diff * Long.valueOf(cur),
                        i + 1, curExp + "*" + cur, expressions);
            } else {
                help(num, target, Long.valueOf(cur), Long.valueOf(cur), i + 1, cur, expressions);
            }
        }
    }

}
