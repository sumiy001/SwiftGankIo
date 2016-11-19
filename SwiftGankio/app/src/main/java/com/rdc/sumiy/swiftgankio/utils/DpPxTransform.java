package com.rdc.sumiy.swiftgankio.utils;

import android.content.Context;

/**
 * Created by sumiy on 2016/8/16.
 */
public class DpPxTransform {
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static  int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
