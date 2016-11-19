package com.rdc.sumiy.swiftgankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.utils.DeviceInfoUtils;
import com.rdc.sumiy.swiftgankio.utils.PicassoTransformation;
import com.rdc.sumiy.swiftgankio.widget.RecyclableImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
/**
 * Created by sumiy on 2016/8/10.
 */
public class RecyclerViewInFuliAdapter extends RecyclerView.Adapter<RecyclerViewInFuliAdapter.RecyclerViewHolder> {
    private ArrayList<String> imageUrlList;
    Context context;
    public RecyclerViewInFuliAdapter(ArrayList<String> imageUrlList, Context context) {
        this.imageUrlList = imageUrlList;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_fuli,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        ViewGroup.LayoutParams lp = recyclerViewHolder.imageView.getLayoutParams();
        lp.width = DeviceInfoUtils.getDeviceWidth() / 2;
        recyclerViewHolder.imageView.setLayoutParams(lp);
        Picasso.with(context).load(imageUrlList.get(i)).transform(new PicassoTransformation()).placeholder(R.drawable.sold).error(R.drawable.playholder)
                .into(recyclerViewHolder.imageView);
    }
    @Override
    public int getItemCount() {
        return imageUrlList.size();

    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        RecyclableImageView imageView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
           imageView = (RecyclableImageView) itemView.findViewById(R.id.iv_recyclerInMain);
        }

    }


}
