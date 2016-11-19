package com.rdc.sumiy.swiftgankio.utils;

import android.app.Activity;
import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.listener.NaviBarTvOnClickListener;
import com.rdc.sumiy.swiftgankio.widget.NaviBarTextView;
import java.util.ArrayList;

/**
 * Created by sumiy on 2016/8/14.
 */
public class NaviBarTextViewManage {
    private static ArrayList<NaviBarTextView> naviBarTextViews = new ArrayList<>();
    private static Activity mActivity;
    private NaviBarTextView naviBarTvAndroid;
    private NaviBarTextView naviBarTvFuli;
    private NaviBarTextView naviBarTvVideo;
    private NaviBarTextView naviBarTviOS;
    private NaviBarTextView naviBarTvQuanduan;
    private NaviBarTextView naviBarTvExpand;
    private NaviBarTextView naviBarTvXia;

    private NaviBarTextViewManage(Activity mActivity) {
        naviBarTvAndroid = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_android);
        naviBarTvFuli = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_fuli);
        naviBarTvVideo = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_video);
        naviBarTviOS = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_ios);
        naviBarTvQuanduan = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_quanduan);
        naviBarTvExpand = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_expand);
        naviBarTvXia = (NaviBarTextView) mActivity.findViewById(R.id.navi_tv_xia);

        initNaviBarTextViews();
    }
    private static class NaviBarTextViewManageHolder {
        private static NaviBarTextViewManage naviBarTextViewManage = new NaviBarTextViewManage(mActivity);
    }

    public static NaviBarTextViewManage getInstance(Activity activity) {
        mActivity = activity;
        return NaviBarTextViewManageHolder.naviBarTextViewManage;
    }
    private Object readResolve() {
        return NaviBarTextViewManageHolder.naviBarTextViewManage;
    }
    private void initNaviBarTextViews() {
        naviBarTextViews.add(naviBarTvAndroid);
        naviBarTextViews.add(naviBarTvFuli);
        naviBarTextViews.add(naviBarTvVideo);
        naviBarTextViews.add(naviBarTviOS);
        naviBarTextViews.add(naviBarTvQuanduan);
        naviBarTextViews.add(naviBarTvExpand);
        naviBarTextViews.add(naviBarTvXia);

        naviBarTvAndroid.isSelect = true;
        naviBarTvAndroid.invalidate();
    }
    public  NaviBarTextView getNaviBarTv(int position){
        return naviBarTextViews.get(position);
    }
    public void beNormal() {
        for (int i = 0; i < naviBarTextViews.size(); i++) {
            NaviBarTextView naviBarTextView = naviBarTextViews.get(i);
            naviBarTextView.isSelect = false;
            naviBarTextView.invalidate();
        }
    }
    public void beSpecial(int position) {
        NaviBarTextView naviBarTextView = naviBarTextViews.get(position);
        naviBarTextView.isSelect = true;
        naviBarTextView.invalidate();
    }

    public void setNaviBarTvListener(NaviBarTvOnClickListener naviBarTvListener) {
        for (int i = 0; i < naviBarTextViews.size(); i++) {
            NaviBarTextView naviBarTextView = naviBarTextViews.get(i);
            naviBarTextView.setOnClickListener(naviBarTvListener);
        }
    }
}
