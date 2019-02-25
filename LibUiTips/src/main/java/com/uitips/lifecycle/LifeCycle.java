package com.uitips.lifecycle;

import android.app.FragmentManager;

/**
 * Created by lihoudong204 on 2018/10/19
 */
public class LifeCycle implements ILifeCycle {
    private FragmentManager fm;

    public LifeCycle(FragmentManager fm) {
        this.fm = fm;
    }
    @Override
    public void onDestroy() {
        LifeCycleFragmentRetriver.remove(fm);
    }
}
