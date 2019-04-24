package com.ycdage.algorithm;

/**
 * 给定一个字符串s，找到s中最长的回文子串。你可以假设s的最大长度为1000。
 * 示例
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String s = "lphbehiapswjudnbcossedgioawodnwdruaaxhbkwrxyzaxygmnjgwysafuqbmtzwdkihbwkrjefrsgjbrycembzzlwhxneiijgzidhngbmxwkhphoctpilgooitqbpjxhwrekiqupmlcvawaiposqttsdgzcsjqrmlgyvkkipfigttahljdhtksrozehkzgshekeaxezrswvtinyouomqybqsrtegwwqhqivgnyehpzrhgzckpnnpvajqevbzeksvbezoqygjtdouecnhpjibmsgmcqcgxwzlzztdneahixxhwwuehfsiqghgeunpxgvavqbqrelnvhnnyqnjqfysfltclzeoaletjfzskzvcdwhlbmwbdkxnyqappjzwlowslwcbbmcxoiqkjaqqwvkybimebapkorhfdzntodhpbhgmsspgkbetmgkqlolsltpulgsmyapgjeswazvhxedqsypejwuzlvegtusjcsoenrcmypexkjxyduohlvkhwbrtzjnarusbouwamazzejhnetfqbidalfomecehfmzqkhndpkxinzkpxvhwargbwvaeqbzdhxzmmeeozxxtzpylohvdwoqocvutcelgdsnmubyeeeufdaoznxpvdiwnkjliqtgcmvhilndcdelpnilszzerdcuokyhcxjuedjielvngarsgxkemvhlzuprywlypxeezaxoqfges";
        String s1 = "aaaa";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 0)
            return "";
        if(s.length() == 1)
            return s;
        String lastResult = s.substring(0,1);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                if(lastResult.length() < 2){
                    lastResult = s.substring(i,i+2);
                }
                int pos = i - 1;
                while (pos >= 0 && 2 * i + 1 - pos < s.length()) {
                    if (s.charAt(pos) == s.charAt(i + 1 + i - pos)) {
                        if (2 * i - pos + 2 - pos > lastResult.length()) {
                            lastResult = s.substring(pos, 2 * i - pos + 2);
                        }
                        pos--;
                    } else {
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2)) {
                if(lastResult.length() < 3){
                    lastResult = s.substring(i,i+3);
                }
                int pos = i - 1;
                while (pos >= 0 && 2 * i + 2 - pos < s.length()) {
                    if (s.charAt(pos) == s.charAt(i + 2 + i - pos)) {
                        if (2 * i - pos + 3 - pos > lastResult.length()) {
                            lastResult = s.substring(pos, 2 * i - pos + 3);
                        }
                        pos--;
                    } else {
                        break;
                    }
                }
            }
        }
        return lastResult;
    }
}
