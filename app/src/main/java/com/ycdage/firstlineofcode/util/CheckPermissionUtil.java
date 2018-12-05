package com.ycdage.firstlineofcode.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckPermissionUtil {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void checkUserPermission(Activity activity, String[] permission, PermissionCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> requestList = new ArrayList<>();
            boolean hasPermission = true;
            for (String myPermission : permission) {
                int selfPermission = ContextCompat.checkSelfPermission(activity, myPermission);
                if (selfPermission != PackageManager.PERMISSION_GRANTED) {
                    hasPermission = false;
                    boolean showRequestPermissionRationale = ActivityCompat
                            .shouldShowRequestPermissionRationale(activity, myPermission);
                    if (showRequestPermissionRationale) {
                        Toast.makeText(activity, "请打开权限", Toast.LENGTH_SHORT).show();
                    } else {
                        requestList.add(myPermission);
                    }
                }
            }
            if (hasPermission) {
                callback.grantPermission();
                return;
            }

            int size = requestList.size();
            if (size > 0) {
                activity.requestPermissions((String[]) requestList.toArray(new String[size]), 1);
            }
        }
    }

    public interface PermissionCallback {
        void grantPermission();
    }
}
