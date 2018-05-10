package com.ycdage.firstlineofcode.ipc;

public class Test {
    static int totalAmount = 10;
    static int totalWater = 0;
    static int top = 0;
    static int ball = 0;

    public static void main(String args[]) {
        totalWater = totalAmount / 2;
        top = totalWater;
        ball = totalWater;

        doSale();
    }

    private static void doSale() {
        if (top >= 4) {
            saleTop();
        } else if (ball >= 2) {
            saleBall();
        } else {
            System.out.print(totalWater);
        }
    }

    private static void saleTop() {
        int sale = top / 4;
        top = top - sale * 4;
        totalWater = totalWater + sale;
        ball = ball + sale;
        doSale();
    }

    private static void saleBall() {
        int sale = ball / 2;
        ball = ball - sale * 2;
        totalWater = totalWater + sale;
        top = top + sale;
        doSale();
    }
}
