package com.uitips.view.common;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by lihoudong204 on 2018/10/23
 */
public class Utils {
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static void runOnUiThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            mainHandler.post(runnable);
        }
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == str2) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }
}
