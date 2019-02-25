package com.uitips;

import android.app.Activity;

import com.uitips.view.IWarnDialog;
import com.uitips.view.IWarnView;
import com.uitips.view.ILoadingDialog;
import com.uitips.view.ILoadingView;

/**
 * Created by lihoudong204 on 2018/10/17
 * 分发具体的请求，每个UiDispatcher对应一个FragmentActivity或者Fragment
 */
public class UiDispatcher {
    private Activity activity;

    private ILoadingView loadingView;
    private IWarnView warnView;

    private ILoadingDialog loadingDialog;
    private IWarnDialog warnDialog;

    public UiDispatcher(Activity activity) {
        this.activity = activity;
    }

    /**
     * 返回加载样式
     * @return
     */
    public ILoadingView loadingView() {
        if (loadingView == null) {
            createLoadingView();
        }
        return loadingView;
    }


    /**
     * 返回错误提示
     * @return
     */
    public IWarnView warnView() {
        if (warnView == null) {
            createWarnView();
        }
        return warnView;
    }

    public ILoadingDialog loadingDialog() {
        if (loadingDialog == null) {
            createLoadingDialog();
        }

        return loadingDialog;
    }

    public IWarnDialog warnDialog() {
        if (warnDialog == null) {
            createWarnDialog();
        }
        return warnDialog;
    }

    private void createWarnDialog() {
        IWarnDialog.IWarnDialogFactory factory = UiTips.getInstance().getConfig().getWarnDialogFactory();
        warnDialog = factory.create(activity);
    }

    private void createLoadingDialog() {
        ILoadingDialog.ILoadingDialogFactory factory = UiTips.getInstance().getConfig().getLoadingDialogFactory();
        loadingDialog = factory.create(activity);
    }

    private void createLoadingView() {
        ILoadingView.ILoadingViewFactory factory = UiTips.getInstance().getConfig().getLoadingViewFactory();
        loadingView = factory.create();
    }

    private void createWarnView() {
        IWarnView.IWarnViewFactory factory = UiTips.getInstance().getConfig().getWarnViewFactory();
        warnView = factory.create();
    }
}
