package com.rdc.sumiy.swiftgankio.view.fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
import com.rdc.sumiy.swiftgankio.model.VideoModel;
import com.rdc.sumiy.swiftgankio.model.dataBase.MyDatabaseHelper;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.CallableContainer;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.refresh.RefreshVideo;
import com.rdc.sumiy.swiftgankio.widget.FlexibleRecyclerView;
/**
 * Created by sumiy on 2016/8/13.
 */
public class VideoFragment extends Fragment implements Callable {
    private static final int VIDEO_LOAD = Constant.VIDEO;
    private static final String TAG = "VideoFragment";
    private Activity mActivity;
    public static  FlexibleRecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayoutOnRefreshListener swipeRefreshLayoutOnRefreshListener;
    private View view;
    private RecyclerViewAdapter recyclerViewAdapter;
    private static MyDatabaseHelper dbHelper;
    public VideoFragment(Activity activity) {
        this.mActivity = activity;
        CallableContainer.addCallabble(VIDEO_LOAD, this);
        dbHelper = new MyDatabaseHelper(mActivity, "database.db", null, 3);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        initUI();
        initData();
        return view;
    }
    private void initUI() {
        mRecyclerView = (FlexibleRecyclerView) view.findViewById(R.id.recyclerInVideo);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutInVideo);
    }

    private void initData() {
        initDatabase();
        swipeRefreshLayoutOnRefreshListener = new SwipeRefreshLayoutOnRefreshListener(this, swipeRefreshLayout, new RefreshVideo(this, swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshLayoutOnRefreshListener);
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(android.R.color.holo_blue_light));
        swipeRefreshLayoutOnRefreshListener.onRefresh();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerViewAdapter = new RecyclerViewAdapter( NetorkGetDataUtils.videoModels);
        mRecyclerView.setAdapter(recyclerViewAdapter);

    }
    @Override
    public void initDatabase() {
        if (NetorkGetDataUtils.videoModels.size() != 0)
            return;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Video", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String desc = cursor.getString(cursor.getColumnIndex("desc"));
                String createdAt = cursor.getString(cursor.getColumnIndex("createdAt"));
                String who = cursor.getString(cursor.getColumnIndex("who"));
                VideoModel videoModel = new VideoModel(url, desc, createdAt, who);
                NetorkGetDataUtils.videoModels.add(videoModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    @Override
    public void onDetach() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Video", null, null);
        for (int i = 0; i < NetorkGetDataUtils.videoModels.size(); i++) {
            ContentValues values = new ContentValues();
            values.put("url",
                    NetorkGetDataUtils.videoModels.get(i).getUrl());
            values.put("desc",
                    NetorkGetDataUtils.videoModels.get(i).getDesc());
            values.put("who",
                    NetorkGetDataUtils.videoModels.get(i).getWho());
            values.put("createdAt",
                    NetorkGetDataUtils.videoModels.get(i).getCreatedAt());
            db.insert("Video", null, values);
        }
        super.onDetach();
    }
    @Override
    public void onCallBack() {
        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}
