package com.rdc.sumiy.swiftgankio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.utils.ParserTime;
import com.rdc.sumiy.swiftgankio.utils.factory.ArticleTitle;

import java.util.ArrayList;
/**
 * Created by sumiy on 2016/8/12.
 */
public class RecyclerViewAdapter<T extends ArticleTitle> extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    ArrayList<T> models;

    public RecyclerViewAdapter(ArrayList<T> models) {
        this.models = models;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.textView_desc.setText(models.get(i).getDesc());
        String who = models.get(i).getWho();
        who = who.equals("null") ? "匿名作者" : "作者:" + who;
        recyclerViewHolder.textView_author.setText(who + "  " + ParserTime.parser(models.get(i).getCreatedAt()));
    }
    @Override
    public int getItemCount() {
        return models.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textView_desc;
        TextView textView_author;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            textView_desc = (TextView) itemView.findViewById(R.id.tv_recler_desc);
            textView_author = (TextView) itemView.findViewById(R.id.tv_recycler_who);
        }
    }
}