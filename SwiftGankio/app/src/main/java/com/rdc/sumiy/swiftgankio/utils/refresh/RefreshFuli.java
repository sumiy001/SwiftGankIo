package com.rdc.sumiy.swiftgankio.utils.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;

/**
 * Created by sumiy on 2016/8/12.
 */
public class RefreshFuli implements Refreshble {
    private Callable callable;
    private SwipeRefreshLayout swipeRefreshLayout;
    public RefreshFuli(Callable callable, SwipeRefreshLayout swipeRefreshLayout) {
        this.callable = callable;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }
    @Override
    public void refresh() {
        NetorkGetDataUtils.imageUrls.clear();
        NetorkGetDataUtils.imageCurrentPage = 1;
        swipeRefreshLayout.setRefreshing(true);
        NetorkGetDataUtils.getBitmapUrlList(callable);
    }
}
