package com.rdc.sumiy.swiftgankio.listener;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by sumiy on 2016/8/11.
 */
public class RecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    public RecyclerViewOnScrollListener(  StaggeredGridLayoutManager staggeredGridLayoutManager ) {
        this.staggeredGridLayoutManager = staggeredGridLayoutManager;
    }
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        staggeredGridLayoutManager.invalidateSpanAssignments();
    }
}
