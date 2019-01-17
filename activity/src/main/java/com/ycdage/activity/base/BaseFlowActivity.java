package com.ycdage.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.ycdage.activity.R;
import com.ycdage.activity.util.DpUtils;

public abstract class BaseFlowActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;
    public LinearLayout mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);
        flexboxLayout = findViewById(R.id.flexbox_layout);
        mLayout = findViewById(R.id.layout_bottom);
        initElement();
        addButtons();
        startAction();
    }

    protected void initElement() {
    }

    protected void startAction() {
    }

    protected abstract void addButtons();

    protected void createNewItemTextView(String text, View.OnClickListener listener) {
        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(14);
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_gray_btn));
        tv.setText(text);
        tv.setTag(text);
        tv.setOnClickListener(listener);

        int padding = DpUtils.dpToPixel(this, 5);
        tv.setPadding(padding, padding, padding, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = DpUtils.dpToPixel(this, 8);
        layoutParams.setMargins(margin, margin, margin, margin);
        tv.setLayoutParams(layoutParams);
        flexboxLayout.addView(tv);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "null activity founded!", Toast.LENGTH_SHORT).show();
            return;
        }
        super.startActivityForResult(intent, requestCode);
    }
}
