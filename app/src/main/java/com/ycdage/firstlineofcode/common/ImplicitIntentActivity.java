package com.ycdage.firstlineofcode.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.ycdage.firstlineofcode.Constant.ConstantIntent;
import com.ycdage.firstlineofcode.R;
import com.ycdage.firstlineofcode.views.Util;

public class ImplicitIntentActivity extends AppCompatActivity {

    private FlexboxLayout flexboxLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        flexboxLayout = findViewById(R.id.flexbox_layout);
        addView();
    }

    private void addView() {
        createNewItemTextView("获取联系人", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("vnd.android.cursor.item/phone");
//                startActivityForResult(intent,99);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, ConstantIntent.REQUEST_TAKE_CONTACT);
                }
            }
        });

        createNewItemTextView("查看文件", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("vnd.android.cursor.item/vnd.google.note");
                startActivityForResult(intent, 99);
            }
        });

        createNewItemTextView("打电话", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:15950512338"));
                startActivityForResult(intent, 99);
            }
        });

        createNewItemTextView("打开相册", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("iamge/*");
                startActivityForResult(intent, 99);
            }
        });

        createNewItemTextView("拍照", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivityForResult(intent, 99);
            }
        });

        createNewItemTextView("拍照并返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.withAppendedPath(mLocationForPhotos, targetFilename));
                startActivityForResult(intent, 99);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, resultCode + "", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case ConstantIntent.REQUEST_TAKE_CONTACT:
                if(data != null){
                    Uri contactUri = data.getData();
                }
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this,"null activity founded!",Toast.LENGTH_SHORT).show();
            return;
        }
        super.startActivityForResult(intent, requestCode);
    }

    private void createNewItemTextView(String text, View.OnClickListener listener) {
        TextView tv = new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(14);
        tv.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_gray_btn));
        tv.setText(text);
        tv.setTag(text);
        tv.setOnClickListener(listener);

        int padding = Util.dpToPixel(this, 5);
        tv.setPadding(padding, padding, padding, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = Util.dpToPixel(this, 8);
        layoutParams.setMargins(margin, margin, margin, margin);
        tv.setLayoutParams(layoutParams);
        flexboxLayout.addView(tv);
    }

}
