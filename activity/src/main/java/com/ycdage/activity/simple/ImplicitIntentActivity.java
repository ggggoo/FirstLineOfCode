package com.ycdage.activity.simple;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ycdage.activity.BaseFlowActivity;
import com.ycdage.activity.contants.ConstantIntent;
import com.ycdage.activity.util.CheckPermissionUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImplicitIntentActivity extends BaseFlowActivity {

    private Uri imageUri;

    @Override
    protected void initViews() {
        createNewItemTextView("获取联系人", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(intent, ConstantIntent.TAKE_CONTACT);
            }
        });

        createNewItemTextView("获取联系人2", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("vnd.android.cursor.item/phone");
                startActivityForResult(intent, 99);
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

        createNewItemTextView("拍照并返回缩略图", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, ConstantIntent.TAKE_PHOTO_SIMPLE);
            }
        });

        createNewItemTextView("拍照并返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        + "/first/" + System.currentTimeMillis() + ".jpg");
                file.getParentFile().mkdirs();
                imageUri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, ConstantIntent.TAKE_PHOTO);
            }
        });

        createNewItemTextView("拍照并返回v7", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckPermissionUtil.checkUserPermission(ImplicitIntentActivity.this, new String[]{Manifest.permission.CAMERA},
                        new CheckPermissionUtil.PermissionCallback() {
                            @Override
                            public void grantPermission() {
                                takeProviderPhoto();
                            }
                        });
            }
        });
    }

    private void takeProviderPhoto(){
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/img/first";
        if (new File(path).exists()) {
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @SuppressLint("SimpleDateFormat")
        String filename = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mTmpFile = new File(path, filename + ".jpg");
        mTmpFile.getParentFile().mkdirs();
        String authority = getPackageName() + ".fileprovider";
        imageUri = FileProvider.getUriForFile(ImplicitIntentActivity.this, authority, mTmpFile);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, ConstantIntent.TAKE_PHOTO_v7);
    }

    private void showPhoto(Bitmap bitmap) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,
                300);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mLayout.addView(imageView, params);
    }

    private void showPhoto(Uri uri) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,
                300);
        Glide.with(this).load(uri).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mLayout.addView(imageView, params);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, resultCode + "", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case ConstantIntent.TAKE_CONTACT:
                if (data != null) {
                    Uri contactUri = data.getData();
                }
                break;
            case ConstantIntent.TAKE_PHOTO_SIMPLE:
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                showPhoto(bitmap);
                break;
            case ConstantIntent.TAKE_PHOTO:
                showPhoto(imageUri);
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (TextUtils.equals(permissions[0], Manifest.permission.CAMERA)
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           takeProviderPhoto();
        } else {
            Toast.makeText(ImplicitIntentActivity.this, "未获取权限", Toast.LENGTH_SHORT).show();
        }
    }
}
