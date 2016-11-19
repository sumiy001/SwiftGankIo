package com.rdc.sumiy.swiftgankio.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.utils.DpPxTransform;

/**
 * Created by sumiy on 2016/8/16.
 */
public class ObservableScroll extends ScrollView {
    private static final String TAG = "ObservableScroll";
    private int mRefreshHeight = 160;
    private int oneThridRefreshHeight;
    private LinearLayout refreshlinearImage;
    private TextView refreshTextview;
    private ImageView imageView;
    private boolean needRefresh = true;
    private Context context;
    private ScrollOnRefreshListener scrollOnRefreshListener = null;
    private Animation animNeed;
    private Animation animIneed;

    public ObservableScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mRefreshHeight = DpPxTransform.Dp2Px(context, 160);
        oneThridRefreshHeight = mRefreshHeight / 3;
        animNeed = AnimationUtils.loadAnimation(context, R.anim.anim_refresh_need);
        animIneed = AnimationUtils.loadAnimation(context, R.anim.anim_refresh_ineed);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, mRefreshHeight);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int scrollY;
        float scale;
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                scrollY = getScrollY();
                if (scrollY < mRefreshHeight) {
                    scale = (float)(mRefreshHeight - getScrollY())/3000+1;
                    imageView.setScaleX(scale);
                    imageView.setScaleY(scale);
                    imageView.requestLayout();
                }
                if (scrollY < oneThridRefreshHeight) {
                    if (needRefresh) {
                        needRefresh = false;
                        refreshlinearImage.startAnimation(animNeed);
                        refreshTextview.setText("上拉撤销");
                    }
                } else if (scrollY < mRefreshHeight) {
                    if (!needRefresh) {
                        needRefresh = true;
                        refreshlinearImage.startAnimation(animIneed);
                        refreshTextview.setText("下拉刷新");
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                imageView.setScaleY(1);
                imageView.setScaleX(1);
                scrollY = getScrollY();
                if (scrollY <= oneThridRefreshHeight) {
                    if (scrollOnRefreshListener != null) {
                        refreshlinearImage.clearAnimation();
                        refreshlinearImage.setVisibility(View.GONE);
                        refreshTextview.setText("正在刷新...");
                        scrollOnRefreshListener.onRefresh();
                    }
                }
                else if (scrollY < mRefreshHeight)
                    scrollTo(0, mRefreshHeight);
                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void setRefreshView(LinearLayout linearLayout, TextView textView, ImageView imageView) {
        this.refreshlinearImage = linearLayout;
        this.refreshTextview = textView;
        this.imageView = imageView;
    }

    public void setScrollOnRefreshListener(ScrollOnRefreshListener scrollOnRefreshListener) {
        this.scrollOnRefreshListener = scrollOnRefreshListener;
    }

    public static abstract class ScrollOnRefreshListener {
        abstract public void onRefresh();
    }

}
