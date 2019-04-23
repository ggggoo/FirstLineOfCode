package com.ycdage.algorithm;

/**
 * 斐波那契数列
 */
public class Fibonacci {
    public static void main(String[] args) {
        fibonacci(10);
        fibonacci(20);
        fibonacci(30);
    }

    /**
     *
     * @param printCount 斐波那契项数
     */
    private static void fibonacci(int printCount) {
        System.out.println("计算斐波那契数列"+printCount +"位: ");
        if (printCount <= 0) {
            System.out.println("error,count not zero");
            return;
        }
        System.out.print("1 ");

        if (printCount >= 2) {
            System.out.print("1 ");
            int num1 = 1;
            int num2 = 1;
            int count = 2;
            while (count < printCount) {
                int numCached = num2;
                num2 = num1 + num2;
                num1 = numCached;
                count++;
                System.out.print(num2 + " ");
            }
            System.out.println("");
        }


    }
}
