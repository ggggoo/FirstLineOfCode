package com.ycdage.activity.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ycdage.activity.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView tv;
    public ImageView iv;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.recycler_view_tv);
        iv = itemView.findViewById(R.id.recycler_view_iv);
    }
}
