package com.rdc.sumiy.swiftgankio.utils.refresh;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.NetworkGetRecentData;
import com.rdc.sumiy.swiftgankio.widget.ObservableScroll;

/**
 * Created by sumiy on 2016/8/17.
 */
public class MyScrollOnRefreshListener extends ObservableScroll.ScrollOnRefreshListener {
    private Callable callable;
    private ImageView imageView;
    private Animation animation;

    public MyScrollOnRefreshListener(Callable callable, ImageView imageView, Context context) {
        this.callable = callable;
        this.imageView = imageView;
        animation = AnimationUtils.loadAnimation(context, R.anim.anim_state2refresh_complete);
    }

    @Override
    public void onRefresh() {
        NetworkGetRecentData.clean();
        imageView.startAnimation(animation);
        imageView.setVisibility(View.VISIBLE);
        NetworkGetRecentData.getCurrentData(callable);
    }
}
