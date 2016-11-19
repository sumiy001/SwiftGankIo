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
import com.rdc.sumiy.swiftgankio.model.AndroidModel;
import com.rdc.sumiy.swiftgankio.model.dataBase.MyDatabaseHelper;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.listener.SwipeRefreshLayoutOnRefreshListener;
import com.rdc.sumiy.swiftgankio.utils.CallableContainer;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.refresh.RefreshAndroid;
import com.rdc.sumiy.swiftgankio.widget.FlexibleRecyclerView;
/**
 * Created by sumiy on 2016/8/12.
 */
public class AndroidFragment extends Fragment implements Callable {
    private static final String TAG = "AndroidFragment";
    private static final int ANDROID = Constant.ANDROID;
    private Activity mActivity;
    private static  MyDatabaseHelper dbHelper;
    public  static   FlexibleRecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayoutOnRefreshListener swipeRefreshLayoutOnRefreshListener;
    private View view;
    private RecyclerViewAdapter recyclerViewAdapter;
    public AndroidFragment(Activity mActivity) {
        this.mActivity = mActivity;
        CallableContainer.addCallabble(ANDROID, this);
        dbHelper = new MyDatabaseHelper(mActivity, "database.db", null, 3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_android, container, false);
        initUI();
        initData();
        return view;
    }
    public void initUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutInAndroid);
        mRecyclerView = (FlexibleRecyclerView) view.findViewById(R.id.recyclerInAndroid);
    }

    public void initData() {
        initDatabase();
        swipeRefreshLayoutOnRefreshListener = new SwipeRefreshLayoutOnRefreshListener(this, swipeRefreshLayout, new RefreshAndroid(this, swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshLayoutOnRefreshListener);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.holo_blue_light));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerViewAdapter = new RecyclerViewAdapter(NetorkGetDataUtils.androidModels);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        swipeRefreshLayoutOnRefreshListener.onRefresh();
    }
    @Override
    public  void initDatabase() {
        if (NetorkGetDataUtils.androidModels.size() != 0)
            return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Android", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String desc = cursor.getString(cursor.getColumnIndex("desc"));
                String createdAt = cursor.getString(cursor.getColumnIndex("createdAt"));
                String who = cursor.getString(cursor.getColumnIndex("who"));
                AndroidModel androidModel = new AndroidModel(url, desc, createdAt, who);
                NetorkGetDataUtils.androidModels.add(androidModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public void onCallBack() {
        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
    @Override
    public void onDetach() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Android",null,null);
        for (int i = 0; i < NetorkGetDataUtils.androidModels.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("url",
                    NetorkGetDataUtils.androidModels.get(i).getUrl());
            values.put("desc",
                    NetorkGetDataUtils.androidModels.get(i).getDesc());
            values.put("who",
                    NetorkGetDataUtils.androidModels.get(i).getWho());
            values.put("createdAt",
                    NetorkGetDataUtils.androidModels.get(i).getCreatedAt());
            db.insert("Android", null, values);

        }
        super.onDetach();
    }
}