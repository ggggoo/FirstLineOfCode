package com.ycdage.activity.recyclerview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ycdage.activity.R;

import java.util.HashMap;
import java.util.List;

public class MRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context mContext;
    private List<HashMap<String, Object>> mDatas;

    public MRecyclerViewAdapter(Context context, List<HashMap<String, Object>> mData) {
        this.mContext = context;
        this.mDatas = mData;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolder holder = new RecyclerViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_recylerview, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.tv.setText((String) mDatas.get(position).get("text"));
        holder.iv.setImageDrawable(ContextCompat.getDrawable(mContext, (Integer) mDatas.get(position).get("image")));
    }

    @Override
    public int getItemCount() {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }
}
