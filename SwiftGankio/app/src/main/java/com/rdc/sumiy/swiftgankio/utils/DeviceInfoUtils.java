package com.rdc.sumiy.swiftgankio.utils;

import android.content.Context;
import android.view.WindowManager;

import com.rdc.sumiy.swiftgankio.GankioApplication;

/**
 * Created by sumiy on 2016/8/11.
 */
public class DeviceInfoUtils {
    static WindowManager wm = (WindowManager) GankioApplication.gankioApplication.
            getSystemService(Context.WINDOW_SERVICE);
    private static final int WIDTH = wm.getDefaultDisplay().getWidth();
    private static int HEIGHT = wm.getDefaultDisplay().getHeight();
    public static int getDeviceHeight() {
        return HEIGHT;
    }
    public static int getDeviceWidth() {
        return WIDTH;
    }

}
