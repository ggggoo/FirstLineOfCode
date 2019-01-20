package com.ycdage.activity.recyclerview;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ycdage.activity.R;

import java.util.ArrayList;
import java.util.List;

public class IMRecycleActivity extends Activity {

    private RecyclerView recyclerView;
    private EditText editText;
    private Button btn1, btn2;
    private List<IMBean> beans;
    private IMAdapter imAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_im);
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);

        beans = new ArrayList<>();
        addTestData();
        setRecyclerViewAdapter();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editMsg = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(editMsg)) {
                    sendMsgToRecyclerView(editMsg, true);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editMsg = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(editMsg)) {
                    sendMsgToRecyclerView(editMsg, false);
                }
            }
        });
    }

    private void addTestData() {
        IMBean bean = new IMBean();
        bean.isLeftSend = true;
        bean.msg = "开始聊天";
        beans.add(bean);
    }

    private void setRecyclerViewAdapter() {
        imAdapter = new IMAdapter();
        recyclerView.setAdapter(imAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new VerticalSpace());
    }

    private void sendMsgToRecyclerView(String editMsg, boolean isLeftSend) {
        IMBean bean = new IMBean();
        bean.isLeftSend = isLeftSend;
        bean.msg = editMsg;
        beans.add(bean);

        imAdapter.notifyItemInserted(beans.size() - 1);
        recyclerView.scrollToPosition(beans.size() - 1);
    }

    class IMAdapter extends RecyclerView.Adapter<IMHolder> {

        @Override
        public IMHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_im_recycler, null);
            return new IMHolder(view);
        }

        @Override
        public void onBindViewHolder(IMHolder holder, int position) {
            holder.tvLeft.setVisibility(beans.get(position).isLeftSend ? View.VISIBLE : View.GONE);
            holder.tvLeft.setText(beans.get(position).isLeftSend ? beans.get(position).msg : "");
            holder.tvRight.setVisibility(!beans.get(position).isLeftSend ? View.VISIBLE : View.GONE);
            holder.tvRight.setText(!beans.get(position).isLeftSend ? beans.get(position).msg : "");
        }

        @Override
        public int getItemCount() {
            return beans.size();
        }
    }

    class IMHolder extends RecyclerViewHolder {
        TextView tvLeft, tvRight;

        IMHolder(View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.tv_left);
            tvRight = itemView.findViewById(R.id.tv_right);
        }
    }

    class VerticalSpace extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = 10;
            outRect.right = 10;
            outRect.top = 8;
            outRect.bottom = 8;
        }
    }

    class IMBean {
        String msg;
        boolean isLeftSend;
    }
}
