package com.example.commonlib.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

public class UtilToast {
    private static Toast toast = null;

    @SuppressLint("ShowToast")
    public static void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_LONG);
        } else {
            toast.setText(str);
        }
        toast.show();
    }
}
