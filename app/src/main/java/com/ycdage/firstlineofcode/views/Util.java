package com.ycdage.firstlineofcode.views;

import android.content.Context;
import android.util.DisplayMetrics;

public class Util {

    public static int dpToPixel(Context context, int dp) {
        if (context == null)
            return dp;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int pixel = Math.round(dp * displayMetrics.density);
        return dp < 0 ? dp : pixel;
    }

    public static int pixelToDp(Context context, int pixel) {
        if(context == null)
            return pixel;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(pixel / displayMetrics.density);
        return pixel < 0 ? pixel : dp;
    }


}
