package com.rdc.sumiy.swiftgankio.utils.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
/**
 * Created by sumiy on 2016/8/14.
 */
public class RefreshXia implements Refreshble {
    private static final String TAG ="RefreshXia" ;
    private Callable callable;
    private SwipeRefreshLayout swipeRefreshLayout;
    public RefreshXia(Callable callable,SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.callable = callable;
    }
    @Override
    public void refresh() {
        NetorkGetDataUtils.xiaModels.clear();
        NetorkGetDataUtils.xiaCurrentPage = 1;
        swipeRefreshLayout.setRefreshing(true);
        NetorkGetDataUtils.getXiaModelList(callable);
    }
}