package com.rdc.sumiy.swiftgankio.utils.refresh;
import android.support.v4.widget.SwipeRefreshLayout;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;
import com.rdc.sumiy.swiftgankio.utils.NetworkGetRecentData;
/**
 * Created by sumiy on 2016/8/12.
 */
public class RefreshState2 implements Refreshble{
    private static final String TAG ="RefreshState2" ;
    private Callable callable;
    private SwipeRefreshLayout swipeRefreshLayout;
    public RefreshState2(Callable callable,SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.callable = callable;
    }
    @Override
    public void refresh() {
        swipeRefreshLayout.setRefreshing(true);
        NetworkGetRecentData.clean();
        NetworkGetRecentData.getCurrentData(callable);
    }
}
