package com.rdc.sumiy.swiftgankio.view.activity;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.adapter.MyFramgmentPagerAdapter;
import com.rdc.sumiy.swiftgankio.listener.FragmentVpOnPageChangeListener;
import com.rdc.sumiy.swiftgankio.listener.NaviBarTvOnClickListener;
import com.rdc.sumiy.swiftgankio.utils.FragmentContainer;
import com.rdc.sumiy.swiftgankio.utils.NaviBarTextViewManage;
import com.rdc.sumiy.swiftgankio.utils.ToastUtils;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.MyFragmentManager;
import com.rdc.sumiy.swiftgankio.utils.refresh.ScrollToTop;
import com.rdc.sumiy.swiftgankio.view.fragment.State2Fragment;
import com.rdc.sumiy.swiftgankio.widget.ArcMenu;
import com.rdc.sumiy.swiftgankio.widget.CategoryTabStrip;
import com.rdc.sumiy.swiftgankio.widget.NaviBarTextView;
public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private ArcMenu arcMenu;
    private NaviBarTextViewManage naviBarTextViewManage;
    private CategoryTabStrip categoryTabStrip;
    private int mState = Constant.STATE_ONE;
    private State2Fragment state2Fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initData();
    }
    private void initUI() {
        arcMenu = (ArcMenu) findViewById(R.id.id_arcmenu1);
        viewPager = (ViewPager) findViewById(R.id.fragmentViewpager);
        categoryTabStrip = (CategoryTabStrip) findViewById(R.id.categoryTabStrip);
    }

    private void initData() {
        final MyFragmentManager  myFragmentManager = new MyFragmentManager(this);
        naviBarTextViewManage = NaviBarTextViewManage.getInstance(this);
        naviBarTextViewManage.setNaviBarTvListener(new NaviBarTvOnClickListener(viewPager, naviBarTextViewManage));
        final FragmentContainer fragmentContainer = FragmentContainer.getInstance(this);
        viewPager.setAdapter(new MyFramgmentPagerAdapter(getSupportFragmentManager(),
                fragmentContainer.getFragments()));
        viewPager.setOnPageChangeListener(new FragmentVpOnPageChangeListener(naviBarTextViewManage, categoryTabStrip));
        viewPager.setOffscreenPageLimit(2);
        arcMenu.onMenuItemClickListener = new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                switch (pos) {
                    case 0:
                        ToastUtils.showToast(MainActivity.this, "功能正在开发，敬请期待", 1500);
                        break;
                    case 1:
                        ScrollToTop.scroll(viewPager.getCurrentItem());
                        break;
                    case 2:
                        state2Fragment = State2Fragment.getInstances();
                        if (mState == Constant.STATE_ONE) {
                            mState = Constant.STATE_TWO;
                            viewPager.setVisibility(View.GONE);
                            categoryTabStrip.setVisibility(View.GONE);
                            myFragmentManager.show(state2Fragment);
                        } else {
                            mState = Constant.STATE_ONE;
                            viewPager.setVisibility(View.VISIBLE);
                            categoryTabStrip.setVisibility(View.VISIBLE);
                            myFragmentManager.hide(state2Fragment);
                        }
                        break;
                }
            }
        };
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        NaviBarTextView.WIDTH = naviBarTextViewManage.getNaviBarTv(0).getWidth();
        super.onWindowFocusChanged(hasFocus);
    }
}
