package com.rdc.sumiy.swiftgankio.listener;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.BaseAdapter;

import com.rdc.sumiy.swiftgankio.utils.NaviBarTextViewManage;
import com.rdc.sumiy.swiftgankio.widget.NaviBarTextView;
/**
 * Created by sumiy on 2016/8/14.
 */
public class NaviBarTvOnClickListener implements View.OnClickListener{
    private ViewPager viewPager;
    NaviBarTextViewManage naviBarTextViewManage;
    public NaviBarTvOnClickListener(ViewPager viewPager,NaviBarTextViewManage naviBarTextViewManage) {
        this.viewPager = viewPager;
        this.naviBarTextViewManage  = naviBarTextViewManage;
    }
    @Override
    public void onClick(View v) {
         viewPager.setCurrentItem(((NaviBarTextView) v).position,true);
        naviBarTextViewManage.beNormal();
        naviBarTextViewManage.beSpecial(((NaviBarTextView) v).position);
    }
}
