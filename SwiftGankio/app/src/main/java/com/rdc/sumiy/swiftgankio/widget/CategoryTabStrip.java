package com.rdc.sumiy.swiftgankio.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;
import com.rdc.sumiy.swiftgankio.utils.DeviceInfoUtils;

/**
 * Created by sumiy on 2016/8/14.
 */
public class CategoryTabStrip extends HorizontalScrollView {
    public static int fristVisiablePosition = 0;
    public static int tabCountPerPager = 3;
    public static int tabCount = 7;
    public static int tabCountMol = 1;
    public CategoryTabStrip(Context context) {
        this(context, null);
    }
    public CategoryTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CategoryTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CategoryTabStrip(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public void scrollToLeft(int position) {
        if(position == 1){
            scrollTo(tabCountMol*120, 0);
        }else
        scrollTo(position / tabCountPerPager*DeviceInfoUtils.getDeviceWidth(), 0);
    }
    public void scrollToRight(int position) {
        scrollTo(position / tabCountPerPager * DeviceInfoUtils.getDeviceWidth(), 0);
    }

}
