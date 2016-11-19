package com.rdc.sumiy.swiftgankio.adapter;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import retrofit.Retrofit;

/**
 * Created by sumiy on 2016/8/12.
 */
public class MyFramgmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentArrayList;
    public MyFramgmentPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }
    @Override
    public Fragment getItem(int position) {
        EventBus
        return fragmentArrayList.get(position);
    }
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}
