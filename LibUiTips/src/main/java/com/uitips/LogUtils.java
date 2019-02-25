package com.uitips;

import android.util.Log;

public class LogUtils {
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "UiTips";

    public static void d(String msg){
        if(DEBUG){
            Log.d(TAG, msg);
        }
    }
}
