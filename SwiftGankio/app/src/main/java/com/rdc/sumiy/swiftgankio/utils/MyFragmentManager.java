package com.rdc.sumiy.swiftgankio.utils;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.rdc.sumiy.swiftgankio.R;
/**
 * Created by sumiy on 2016/8/16.
 */
public class MyFragmentManager {
    private FragmentManager fragmentManager;
    public MyFragmentManager(FragmentActivity fragmentActivity) {
        this.fragmentManager = fragmentActivity.getSupportFragmentManager();
    }
    public void show(Fragment fragment) {
        if(!fragment.isAdded())
            add(fragment);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }
    public void hide(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment);
        fragmentTransaction.commit();
    }
    public void add(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_state2, fragment);
        fragmentTransaction.commit();
    }
}
