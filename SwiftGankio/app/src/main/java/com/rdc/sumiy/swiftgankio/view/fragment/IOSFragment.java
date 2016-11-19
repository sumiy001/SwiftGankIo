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
import com.rdc.sumiy.swiftgankio.model.IOSModel;
import com.rdc.sumiy.swiftgankio.model.dataBase.MyDatabaseHelper;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.CallableContainer;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.refresh.RefreshiOS;
import com.rdc.sumiy.swiftgankio.widget.FlexibleRecyclerView;

/**
 * Created by sumiy on 2016/8/14.
 */
public class IOSFragment extends Fragment implements Callable {

    private static final String TAG = "IOSFragment";
    private static final int IOS_LOAD = Constant.IOS;
    private Activity mActivity;
    public static  FlexibleRecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayoutOnRefreshListener swipeRefreshLayoutOnRefreshListener;
    private View view;
    private RecyclerViewAdapter recyclerViewAdapter;
    private static MyDatabaseHelper dbHelper;

    public IOSFragment(Activity mActivity) {
        this.mActivity = mActivity;
        CallableContainer.addCallabble(IOS_LOAD, this);
        dbHelper = new MyDatabaseHelper(mActivity, "database.db", null, 3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ios, container, false);
        initUI();
        initData();
        return view;
    }

    public void initUI() {
        initDatabase();
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutIniOS);
        mRecyclerView = (FlexibleRecyclerView) view.findViewById(R.id.recycleriOS);
    }
    @Override
    public void initDatabase() {
        if (NetorkGetDataUtils.iOSModels.size() != 0)
            return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("iOS", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String desc = cursor.getString(cursor.getColumnIndex("desc"));
                String createdAt = cursor.getString(cursor.getColumnIndex("createdAt"));
                String who = cursor.getString(cursor.getColumnIndex("who"));
                IOSModel iosModel = new IOSModel(url, desc, createdAt, who);
                NetorkGetDataUtils.iOSModels.add(iosModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void initData() {
        swipeRefreshLayoutOnRefreshListener = new SwipeRefreshLayoutOnRefreshListener(this, swipeRefreshLayout, new RefreshiOS(this, swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshLayoutOnRefreshListener);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.holo_blue_light));
        swipeRefreshLayoutOnRefreshListener.onRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerViewAdapter = new RecyclerViewAdapter(NetorkGetDataUtils.iOSModels);
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onDetach() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("iOS", null, null);
        for (int i = 0; i < NetorkGetDataUtils.iOSModels.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("url",
                    NetorkGetDataUtils.iOSModels.get(i).getUrl());
            values.put("desc",
                    NetorkGetDataUtils.iOSModels.get(i).getDesc());
            values.put("who",
                    NetorkGetDataUtils.iOSModels.get(i).getWho());
            values.put("createdAt",
                    NetorkGetDataUtils.iOSModels.get(i).getCreatedAt());
            db.insert("iOS", null, values);
        }
        super.onDetach();
    }

    @Override
    public void onCallBack() {
        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

}
