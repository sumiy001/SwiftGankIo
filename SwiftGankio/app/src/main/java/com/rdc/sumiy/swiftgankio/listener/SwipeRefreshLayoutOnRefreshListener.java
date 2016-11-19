package com.rdc.sumiy.swiftgankio.listener;
import android.support.v4.widget.SwipeRefreshLayout;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;

import de.greenrobot.event.EventBus;

/**
 * Created by sumiy on 2016/8/11.
 */
public class SwipeRefreshLayoutOnRefreshListener implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "SwipeRefreshLayoutOnRefreshListener";
    private Callable callble;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  Refreshble refreshble;
    public SwipeRefreshLayoutOnRefreshListener(Callable callble, SwipeRefreshLayout swipeRefreshLayout,Refreshble refreshble) {
        this.callble = callble;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.refreshble = refreshble;
    }
    @Override
    public void onRefresh() {
        refreshble.refresh();
    }

}
