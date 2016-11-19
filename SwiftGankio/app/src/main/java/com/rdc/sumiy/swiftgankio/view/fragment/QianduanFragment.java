package com.rdc.sumiy.swiftgankio.view.fragment;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.adapter.RecyclerViewAdapter;
import com.rdc.sumiy.swiftgankio.listener.SwipeRefreshLayoutOnRefreshListener;
import com.rdc.sumiy.swiftgankio.model.QianduanModel;
import com.rdc.sumiy.swiftgankio.model.dataBase.MyDatabaseHelper;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.CallableContainer;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.refresh.RefreshQianduan;
import com.rdc.sumiy.swiftgankio.widget.FlexibleRecyclerView;
/**
 * Created by sumiy on 2016/8/14.
 */
public class QianduanFragment extends Fragment implements Callable {

    private static final String TAG = "QianduanFragment";
    private static final int QIANDUAN_LOAD = Constant.QIANDUAN;
    private Activity mActivity;
    public static FlexibleRecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayoutOnRefreshListener swipeRefreshLayoutOnRefreshListener;
    private View view;
    private RecyclerViewAdapter recyclerViewAdapter;
    private static MyDatabaseHelper dbHelper;

    public QianduanFragment(Activity mActivity) {
        this.mActivity = mActivity;
        CallableContainer.addCallabble(QIANDUAN_LOAD, this);
        dbHelper = new MyDatabaseHelper(mActivity, "database.db", null, 3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qianduan, container, false);
        initUI();
        initData();
        return view;
    }
    public void initUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutInQianduan);
        mRecyclerView = (FlexibleRecyclerView) view.findViewById(R.id.recyclerQianduan);
    }
    public void initData() {
        initDatabase();
        swipeRefreshLayoutOnRefreshListener = new SwipeRefreshLayoutOnRefreshListener(this, swipeRefreshLayout, new RefreshQianduan(this, swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshLayoutOnRefreshListener);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.holo_blue_light));
        swipeRefreshLayoutOnRefreshListener.onRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerViewAdapter = new RecyclerViewAdapter(NetorkGetDataUtils.qianduanModels);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }
    @Override
    public  void initDatabase() {
        if (NetorkGetDataUtils.qianduanModels.size() != 0)
            return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Qianduan", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String desc = cursor.getString(cursor.getColumnIndex("desc"));
                String createdAt = cursor.getString(cursor.getColumnIndex("createdAt"));
                String who = cursor.getString(cursor.getColumnIndex("who"));
                QianduanModel qianduanModel = new QianduanModel(url, desc, createdAt, who);
                NetorkGetDataUtils.qianduanModels.add(qianduanModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public void onDetach() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Qianduan", null, null);
        for (int i = 0; i < NetorkGetDataUtils.qianduanModels.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("url",
                    NetorkGetDataUtils.qianduanModels.get(i).getUrl());
            values.put("desc",
                    NetorkGetDataUtils.qianduanModels.get(i).getDesc());
            values.put("who",
                    NetorkGetDataUtils.qianduanModels.get(i).getWho());
            values.put("createdAt",
                    NetorkGetDataUtils.qianduanModels.get(i).getCreatedAt());
            db.insert("Qianduan", null, values);
        }
        super.onDetach();
    }
    @Override
    public void onCallBack() {
        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

}
