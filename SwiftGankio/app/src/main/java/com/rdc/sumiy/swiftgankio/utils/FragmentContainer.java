package com.rdc.sumiy.swiftgankio.utils;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.rdc.sumiy.swiftgankio.view.fragment.AndroidFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.ExpandFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.FuliFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.IOSFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.QianduanFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.VideoFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.XiaFragment;

import java.util.ArrayList;

/**
 * Created by sumiy on 2016/8/13.
 */
public class FragmentContainer {
    private static FragmentContainer fragmentContainer = null;
    private Activity mActivity;
    private static FuliFragment fuliFragment;
    private static AndroidFragment androidFragment;
    private static VideoFragment videoFragment;
    private static IOSFragment iOSFragment;
    private static QianduanFragment qianduanFragment;
    private static ExpandFragment expandFragment;
    private static XiaFragment xiaFragment;
    private static ArrayList<Fragment> fragments = new ArrayList<>();

    private FragmentContainer(Activity activity) {
        this.mActivity = activity;
        fuliFragment = new FuliFragment(mActivity);
        androidFragment = new AndroidFragment(mActivity);
        videoFragment = new VideoFragment(mActivity);
        iOSFragment = new IOSFragment(mActivity);
        qianduanFragment = new QianduanFragment(mActivity);
        expandFragment = new ExpandFragment(mActivity);
        xiaFragment = new XiaFragment(mActivity);
        init();
    }

    private void init() {
        fragments.add(androidFragment);
        fragments.add(fuliFragment);
        fragments.add(videoFragment);
        fragments.add(iOSFragment);
        fragments.add(qianduanFragment);
        fragments.add(expandFragment);
        fragments.add(xiaFragment);
    }
    public  static  Fragment getFragment(int pos){
           return  fragments.get(pos);
    }
    public static FragmentContainer getInstance(Activity activity) {
        if (fragmentContainer == null) {
            synchronized (FragmentContainer.class) {
                if (fragmentContainer == null) {
                    fragmentContainer = new FragmentContainer(activity);
                    return fragmentContainer;
                }
            }
        }
        return fragmentContainer;
    }
    public ArrayList<Fragment> getFragments() {
        return fragments;
    }
}
