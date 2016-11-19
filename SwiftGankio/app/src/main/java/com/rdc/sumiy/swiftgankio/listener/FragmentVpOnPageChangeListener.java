package com.rdc.sumiy.swiftgankio.listener;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.rdc.sumiy.swiftgankio.utils.NaviBarTextViewManage;
import com.rdc.sumiy.swiftgankio.widget.CategoryTabStrip;
import com.rdc.sumiy.swiftgankio.widget.NaviBarTextView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sumiy on 2016/8/12.
 */
public class FragmentVpOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private static final String TAG = "FragmentVpOnPageChangeListener";
    private NaviBarTextViewManage naviBarTextViewManage;
    private CategoryTabStrip categoryTabStrip;
    public FragmentVpOnPageChangeListener(NaviBarTextViewManage naviBarTextViewManage, CategoryTabStrip categoryTabStrip) {
        this.naviBarTextViewManage = naviBarTextViewManage;
        this.categoryTabStrip = categoryTabStrip;

    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

        naviBarTextViewManage.beNormal();
        naviBarTextViewManage.beSpecial(position);
        categoryTabStrip.scrollTo((position-1) * NaviBarTextView.WIDTH,0);
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }
}