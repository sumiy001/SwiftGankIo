package com.rdc.sumiy.swiftgankio.view.fragment;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.adapter.RecyclerViewInFuliAdapter;
import com.rdc.sumiy.swiftgankio.model.dataBase.MyDatabaseHelper;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.listener.RecyclerViewOnScrollListener;
import com.rdc.sumiy.swiftgankio.listener.SwipeRefreshLayoutOnRefreshListener;
import com.rdc.sumiy.swiftgankio.utils.CallableContainer;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
import com.rdc.sumiy.swiftgankio.utils.SpacesItemDecoration;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.refresh.RefreshFuli;
import com.rdc.sumiy.swiftgankio.widget.FlexibleRecyclerView;
/**
 * Created by sumiy on 2016/8/12.
 */
public class FuliFragment extends Fragment implements Callable {
    public FuliFragment(Activity context) {
        this.mActivity = context;
        dbHelper = new MyDatabaseHelper(mActivity, "database.db", null, 3);
        CallableContainer.addCallabble(FUJI_LOAD, this);
    }
    private static final int FUJI_LOAD = Constant.FULI;
    private Activity mActivity;
    public static FlexibleRecyclerView mRecyclerView;
    private static MyDatabaseHelper dbHelper;
    private RecyclerViewInFuliAdapter mRecyclerViewInFuliAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayoutOnRefreshListener swipeRefreshLayoutOnRefreshListener;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fuli, container, false);
        initUI();
        initData();
        return view;
    }
    private void initUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutInFuli);
        mRecyclerView = (FlexibleRecyclerView) view.findViewById(R.id.recyclerInFuli);
    }
    private void initData() {
        initDatabase();
        swipeRefreshLayoutOnRefreshListener = new SwipeRefreshLayoutOnRefreshListener(this, swipeRefreshLayout, new RefreshFuli(this, swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshLayoutOnRefreshListener);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.holo_blue_light));
        swipeRefreshLayoutOnRefreshListener.onRefresh();
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerViewInFuliAdapter = new RecyclerViewInFuliAdapter(NetorkGetDataUtils.imageUrls, mActivity);
        mRecyclerView.setAdapter(mRecyclerViewInFuliAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setOnScrollListener(new RecyclerViewOnScrollListener(staggeredGridLayoutManager));
    }
    @Override
    public   void initDatabase() {
        if (NetorkGetDataUtils.imageUrls.size() != 0)
            return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Fuli", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                NetorkGetDataUtils.imageUrls.add(url);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public void onDetach() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Fuli",null,null);
        for (int i = 0; i < NetorkGetDataUtils.imageUrls.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("url",
                    NetorkGetDataUtils.imageUrls.get(i));
            db.insert("Fuli", null, values);
        }
        super.onDetach();
    }
    @Override
    public void onCallBack() {
        mRecyclerViewInFuliAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}