package com.uitips.view.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.uitips.view.IWarnView;
import com.uitips.view.common.Utils;

/**
 * Created by lihoudong204 on 2018/10/23
 * View类型的提醒基类
 */
public abstract class BaseWarnView implements IWarnView {
    private Context context;
    private ViewGroup parent;
    private RelativeLayout rootLayout;
    protected int type = CUSTOM;
    protected String content;
    protected int warnImage;
    protected View customView;
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
    public IWarnView customView(View view) {
        if (this.customView != view) {
            this.customView = view;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnView content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public IWarnView content(int resId) {
        this.content = context.getResources().getString(resId);
        return this;
    }

    @Override
    public IWarnView warnImage(int resId) {
        this.warnImage = resId;
        return this;
    }

    @Override
    public IWarnView type(int type) {
        this.type = type;
        return this;
    }

    protected abstract void update();

    protected abstract View createLayout(ViewGroup parent);


    private Runnable postShowRunnable = new Runnable() {
        @Override
        public void run() {
            if (rootLayout == null || changed) {
                if (rootLayout != null) {
                    rootLayout.removeAllViews();
                } else {
                    rootLayout = new RelativeLayout(context);
                }
                /**
                 * 如果设置了自定义View，则使用自定义View
                 */
                View loadingLayout = customView != null ? customView : createLayout(rootLayout);

                RelativeLayout.LayoutParams loadingLayoutLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
