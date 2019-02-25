package com.uitips.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.uitips.LogUtils;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by lihoudong204 on 2018/10/18
 */
public class LifeCycleFragmentRetriver {
    private static Map<FragmentManager, SupportLifeCycleFragment> supportFragments = new WeakHashMap<>();
    private static Map<android.app.FragmentManager, LifeCycleFragment> fragments = new WeakHashMap<>();

    private static final String FRAGMENT_TAG = LifeCycleFragmentRetriver.class.getName();

    public static SupportLifeCycleFragment get(Context context) {
        if (context instanceof FragmentActivity) {
            return get((FragmentActivity)context);
        }

        throw new IllegalArgumentException("Invalid context");

    }

    public static SupportLifeCycleFragment get(final FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        SupportLifeCycleFragment lifeCycleFragment = get(fm);
        lifeCycleFragment.setActivity(activity);
        return lifeCycleFragment;
    }

    public static LifeCycleFragment get(final Activity activity) {
        android.app.FragmentManager fm = activity.getFragmentManager();
        LifeCycleFragment lifeCycleFragment = get(fm);
        lifeCycleFragment.setActivity(activity);
        return lifeCycleFragment;
    }

    public static SupportLifeCycleFragment get(final Fragment fragment) {
        FragmentManager fm = fragment.getChildFragmentManager();
        SupportLifeCycleFragment lifeCycleFragment = get(fm);
        lifeCycleFragment.setActivity(fragment.getActivity());
        return lifeCycleFragment;
    }

    public static LifeCycleFragment get(final android.app.Fragment fragment) {
        android.app.FragmentManager fm = fragment.getChildFragmentManager();
        LifeCycleFragment lifeCycleFragment = get(fm);
        lifeCycleFragment.setActivity(fragment.getActivity());
        return lifeCycleFragment;
    }

    private static SupportLifeCycleFragment get(FragmentManager fm){
        SupportLifeCycleFragment lifeCycleFragment = (SupportLifeCycleFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (lifeCycleFragment == null) { //FragmentManager.commitAllowingStateLoss()并不是及时生效
            lifeCycleFragment = supportFragments.get(fm);
        }
        if (lifeCycleFragment == null) {
            lifeCycleFragment = new SupportLifeCycleFragment();
            fm.beginTransaction().add(lifeCycleFragment, FRAGMENT_TAG).commitAllowingStateLoss();
            supportFragments.put(fm, lifeCycleFragment);
            lifeCycleFragment.setLifeCycle(new SupportLifeCycle(fm));
        }else {
            /**
             * 有可能app被销毁，又被启动，这时候FragmentManager中有这个Fragment，但是fragments中没有，所以需要补上
             */
            supportFragments.put(fm, lifeCycleFragment);
        }
        return lifeCycleFragment;
    }

    private static LifeCycleFragment get(android.app.FragmentManager fm){
        LifeCycleFragment lifeCycleFragment = (LifeCycleFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (lifeCycleFragment == null) { //FragmentManager.commitAllowingStateLoss()并不是及时生效
            lifeCycleFragment = fragments.get(fm);
        }
        if (lifeCycleFragment == null) {
            lifeCycleFragment = new LifeCycleFragment();
            fm.beginTransaction().add(lifeCycleFragment, FRAGMENT_TAG).commitAllowingStateLoss();
            fragments.put(fm, lifeCycleFragment);
            lifeCycleFragment.setLifeCycle(new LifeCycle(fm));
        }else {
            LogUtils.d("找到了fragment");
            /**
             * 有可能app被销毁，又被启动，这时候FragmentManager中有这个Fragment，但是fragments中没有，所以需要补上
             */
            fragments.put(fm, lifeCycleFragment);
        }
        return lifeCycleFragment;
    }

    public static void remove(FragmentManager fm) {
        if (fm != null) {
            supportFragments.remove(fm);
        }
    }

    public static void remove(android.app.FragmentManager fm) {
        if (fm != null) {
            fragments.remove(fm);
        }
    }
}
