package com.rdc.sumiy.swiftgankio.utils.refresh;
import android.support.v4.widget.SwipeRefreshLayout;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.myinterface.Refreshble;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
/**
 * Created by sumiy on 2016/8/12.
 */
public class RefreshAndroid implements Refreshble{
    private static final String TAG ="RefreshAndroid" ;
    private Callable callable;
    private SwipeRefreshLayout swipeRefreshLayout;
    public RefreshAndroid(Callable callable,SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.callable = callable;
    }
    @Override
    public void refresh() {
        NetorkGetDataUtils.androidModels.clear();
        NetorkGetDataUtils.androidCurrentPage = 1;
        swipeRefreshLayout.setRefreshing(true);
        NetorkGetDataUtils.getAndroidModelList(callable);
    }
}
