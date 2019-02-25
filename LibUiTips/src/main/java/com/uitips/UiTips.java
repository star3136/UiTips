package com.uitips;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.uitips.lifecycle.LifeCycleFragment;
import com.uitips.lifecycle.SupportLifeCycleFragment;
import com.uitips.lifecycle.LifeCycleFragmentRetriver;

/**
 * Created by lihoudong204 on 2018/10/17
 * 统一加载样式、错误提示的工具类
 */
public class UiTips {
    private static final String TAG = UiTips.class.getSimpleName();
    private static UiTips instance;
    private UiTipsConfig config;

    public static UiTipsConfig.Builder newConfig() {
        if (getInstance().config == null) {
            return new UiTipsConfig.Builder();
        } else {
            return getInstance().config.newBuilder();
        }
    }

    private UiTips() {
    }

    static UiTips getInstance() {
        if (instance == null) {
            synchronized (UiTips.class) {
                if (instance == null) {
                    instance = new UiTips();
                }
            }
        }
        return instance;
    }

    void config(UiTipsConfig config) {
        this.config = config;
    }

    UiTipsConfig getConfig() {
        return config;
    }

    /**
     * 和context绑定
     * @param context
     * @return
     */
    public static UiDispatcher with(Context context) {
        if (context instanceof FragmentActivity) {
            return with((FragmentActivity) context);
        } else if (context instanceof Activity) {
            return with((Activity) context);
        }

        throw new IllegalArgumentException("Invalid context");
    }

    /**
     * 绑定Fragment生命周期
     * @param fragment
     * @return
     */
    public static UiDispatcher with(Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("fragment is null");
        }
        checkConfig();

        SupportLifeCycleFragment lifeCycleFragment = LifeCycleFragmentRetriver.get(fragment);
        return lifeCycleFragment.getUiDispatcher();
    }

    /**
     * 绑定Fragment生命周期
     * @param fragment
     * @return
     */
    public static UiDispatcher with(android.app.Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("fragment is null");
        }
        checkConfig();

        LifeCycleFragment lifeCycleFragment = LifeCycleFragmentRetriver.get(fragment);
        return lifeCycleFragment.getUiDispatcher();
    }

    /**
     * 绑定FragmentActivity生命周期
     * @param activity
     * @return
     */
    public static UiDispatcher with(FragmentActivity activity) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        checkConfig();

        SupportLifeCycleFragment lifeCycleFragment = LifeCycleFragmentRetriver.get(activity);
        return lifeCycleFragment.getUiDispatcher();
    }

    /**
     * 绑定Activity生命周期
     * @param activity
     * @return
     */
    public static UiDispatcher with(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
        checkConfig();

        LifeCycleFragment lifeCycleFragment = LifeCycleFragmentRetriver.get(activity);
        return lifeCycleFragment.getUiDispatcher();
    }


    private static void checkConfig() {
        if (getInstance().config == null) {
            LogUtils.d("UiTips did not set custom config, use default");
            newConfig().build().set();
        }
    }
}
