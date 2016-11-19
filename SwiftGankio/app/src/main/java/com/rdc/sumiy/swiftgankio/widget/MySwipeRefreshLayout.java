package com.rdc.sumiy.swiftgankio.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sumiy on 2016/8/16.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    public MySwipeRefreshLayout(Context context) {
        super(context);
    }
    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isNeedIntercept = false;
        switch (ev.getAction()){
            case  MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int i = (int) getY();
                if(i == 0)
                    isNeedIntercept = true;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return  isNeedIntercept;
    }
}
