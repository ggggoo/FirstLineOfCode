package com.ycdage.algorithm;

public class LeastLengthOfNoSame {
    public static void main(String[] args) {
        int aaa = lengthOfLongestSubstring("abcabcbb");
        System.out.println(aaa);
    }

    private static int lengthOfLongestSubstring(String s) {
        return getResultFromIndex(s, 0, 0);
    }

    private static int getResultFromIndex(String s, int mIndex, int maxLength) {
        StringBuilder readBuffer;
        if (maxLength > 0) {
            readBuffer = new StringBuilder(s.substring(0, maxLength-1));
        } else {
            readBuffer = new StringBuilder("");
        }
        for (int index = mIndex; index < s.length(); index++) {
            String ss = s.substring(index, index + 1);
            int i = readBuffer.indexOf(ss);
            if (i != -1) {
                return getResultFromIndex(s.substring(i + 1), index - i - 1,
                        readBuffer.length() > maxLength ? readBuffer.length() : maxLength);
            } else {
                readBuffer.append(ss);
            }
        }
        return readBuffer.length() > maxLength ? readBuffer.length() : maxLength;
    }
}
