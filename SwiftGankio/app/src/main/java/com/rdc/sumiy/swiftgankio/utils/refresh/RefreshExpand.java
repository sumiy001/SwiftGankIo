package com.rdc.sumiy.swiftgankio.utils.refresh;
import android.support.v4.widget.SwipeRefreshLayout;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
/**
 * Created by sumiy on 2016/8/14.
 */
public class RefreshExpand implements Refreshble {
    private static final String TAG ="RefreshExpand" ;
    private Callable callable;
    private SwipeRefreshLayout swipeRefreshLayout;
    public RefreshExpand(Callable callable,SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.callable = callable;
    }
    @Override
    public void refresh() {
        NetorkGetDataUtils.expandModels.clear();
        NetorkGetDataUtils.expandCurrentPage = 1;
        swipeRefreshLayout.setRefreshing(true);
        NetorkGetDataUtils.getExpandModelList(callable);
    }
}