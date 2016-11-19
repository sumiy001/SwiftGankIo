package com.rdc.sumiy.swiftgankio.utils.refresh;
import android.support.v4.widget.SwipeRefreshLayout;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
/**
 * Created by sumiy on 2016/8/14.
 */
public class RefreshQianduan implements Refreshble {
    private static final String TAG = "RefreshQianduan";
    private Callable callable;
    private SwipeRefreshLayout swipeRefreshLayout;

    public RefreshQianduan(Callable callable, SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.callable = callable;
    }
    @Override
    public void refresh() {
        NetorkGetDataUtils.qianduanModels.clear();
        NetorkGetDataUtils.qianduanCurrentPage = 1;
        swipeRefreshLayout.setRefreshing(true);
        NetorkGetDataUtils.getQianduanModelList(callable);
    }
}