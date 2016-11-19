package com.rdc.sumiy.swiftgankio.view.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.rdc.sumiy.swiftgankio.adapter.RecyclerViewAdapter;
import com.rdc.sumiy.swiftgankio.listener.SwipeRefreshLayoutOnRefreshListener;
import com.rdc.sumiy.swiftgankio.model.dataBase.MyDatabaseHelper;
import com.rdc.sumiy.swiftgankio.widget.FlexibleRecyclerView;

/**
 * Created by sumiy on 2016/8/15.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected static MyDatabaseHelper dbHelper;
    public FlexibleRecyclerView mRecyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected SwipeRefreshLayoutOnRefreshListener swipeRefreshLayoutOnRefreshListener;
    protected View view;
    protected RecyclerViewAdapter recyclerViewAdapter;

    abstract protected void initUI();

    abstract protected void initData();
}
