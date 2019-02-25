package com.uitips.lifecycle;

import android.app.Activity;
import android.app.Fragment;

import com.uitips.UiDispatcher;

/**
 * Created by lihoudong204 on 2018/10/18
 * 用于绑定生命周期的Fragment
 */
public class LifeCycleFragment extends Fragment {
    private ILifeCycle lifeCycle;
    private UiDispatcher uiDispatcher;
    private Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setLifeCycle(ILifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public UiDispatcher getUiDispatcher() {
        if (uiDispatcher == null) {
            uiDispatcher = new UiDispatcher(activity == null ? getActivity() : activity);
        }
        return uiDispatcher;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifeCycle != null) {
            lifeCycle.onDestroy();
        }
    }
}
