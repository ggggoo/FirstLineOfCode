package com.ycdage.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/similar-string-groups/
 */
public class NumSimilarGroups {
    public static void main(String[] args) {
        String[] a = {"tars", "rats", "arts", "star"};
        int i = numSimilarGroups(a);
        System.out.println(i);
    }

    private static int numSimilarGroups(String[] A) {
        String similar = A[0];
        Set<Integer> similarSet = new HashSet<>();
        for (String s : A) {
            int i = checkSimilarNum(similar, s);
            similarSet.add(i);
        }
        return getSeriesNum(similarSet);
    }

    private static int getSeriesNum(Set<Integer> similarSet) {
        int totalCount = 1;
        int primiralInt = 0;
        for (int i : similarSet) {
            if (i != 0 && i != primiralInt + 1) {
                totalCount++;
            }
            primiralInt = i;
        }
        return totalCount;
    }

    private static int checkSimilarNum(String similar, String s) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (similar.charAt(i) != s.charAt(i)) {
                s1.append(similar.charAt(i)+"");
                s2.append(s.charAt(i)+"");
            }
        }
        return transformStringNum(s1.toString(), s2.toString(), 0);
    }

    private static int transformStringNum(String s1, String s2, int count) {
        if (s1.equals(s2)) {
            return count;
        }
        for (int i = 0; i < s1.length(); i++) {
            char s1Pos = s1.charAt(i);
            char s2Pos = s2.charAt(i);
            int indexS2 = s2.indexOf(s1Pos);
            while (indexS2 != -1) {
                if (s1.charAt(indexS2) == s2Pos) {
                    StringBuilder sb = new StringBuilder(s1);
                    StringBuilder replace = sb.replace(i, i + 1, String.valueOf(s2Pos))
                            .replace(indexS2, indexS2 + 1, String.valueOf(s1Pos));
                    count = count + transformStringNum(replace.toString(), s2, count + 1);
                    s1 = replace.toString();
                    if(s1.equals(s2))
                        return count+1;
                    break;
                } else {
                    indexS2 = s2.indexOf(s1Pos, indexS2);
                }
            }
        }
        return count;
    }
}
