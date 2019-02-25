package com.uitips.view.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.uitips.view.ILoadingView;
import com.uitips.view.common.Utils;

/**
 * Created by lihoudong204 on 2018/10/23
 * View类型的加载样式基类
 */
public abstract class BaseLoadingView implements ILoadingView {
    private ViewGroup parent;
    private Context context;
    private RelativeLayout rootLayout;

    private View customView; //自定义的View

    protected String content;

    private boolean changed = true;

    @Override
    public void show(ViewGroup parent) {
        this.parent = parent;
        context = parent.getContext();
        Utils.runOnUiThread(postShowRunnable);
    }


    @Override
    public boolean isShowing() {
        if (rootLayout != null && rootLayout.getVisibility() == View.VISIBLE) {
            return true;
        }
        return false;
    }

    @Override
    public void hide() {
        if (rootLayout != null) {
            Utils.runOnUiThread(postHideRunnable);
        }
    }

    @Override
    public ILoadingView content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public ILoadingView content(int resId) {
        this.content = context.getResources().getString(resId);
        return this;
    }

    @Override
    public ILoadingView customView(View view) {
        if (this.customView != view) {
            this.customView = view;
            changed = true;
        }
        return this;
    }


    protected abstract View createLayout(ViewGroup parent);

    protected abstract void update();

    private Runnable postShowRunnable = new Runnable() {
        @Override
        public void run() {
            if (rootLayout == null || changed) {
                if (rootLayout != null) {
                    rootLayout.removeAllViews();
                } else {
                    rootLayout = new RelativeLayout(context);
                    rootLayout.setGravity(Gravity.CENTER);
                }
                /**
                 * 如果设置了自定义View，则使用自定义View
                 */
                View loadingLayout = customView != null ? customView : createLayout(rootLayout);

                RelativeLayout.LayoutParams loadingLayoutLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                loadingLayoutLp.addRule(RelativeLayout.CENTER_IN_PARENT);
                rootLayout.addView(loadingLayout, loadingLayoutLp);
            }
            if (rootLayout.getParent() != parent) {
                if (rootLayout.getParent() != null) {
                    ((ViewGroup) rootLayout.getParent()).removeView(rootLayout);
                }
                parent.addView(rootLayout);
            }
            if (customView == null) {
                update();
            }

            rootLayout.setVisibility(View.VISIBLE);
            changed = false;
        }
    };

    private Runnable postHideRunnable = new Runnable() {
        @Override
        public void run() {
            rootLayout.setVisibility(View.GONE);
        }
    };
}
