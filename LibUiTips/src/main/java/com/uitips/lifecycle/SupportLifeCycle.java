package com.uitips.lifecycle;

import android.support.v4.app.FragmentManager;

/**
 * Created by lihoudong204 on 2018/10/19
 */
public class SupportLifeCycle implements ILifeCycle {
    private FragmentManager fm;

    public SupportLifeCycle(FragmentManager fm) {
        this.fm = fm;
    }
    @Override
    public void onDestroy() {
        LifeCycleFragmentRetriver.remove(fm);
    }
}
