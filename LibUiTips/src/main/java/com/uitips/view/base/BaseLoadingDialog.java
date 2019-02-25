package com.uitips.view.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;

import com.uitips.view.ILoadingDialog;
import com.uitips.view.common.Utils;

import java.lang.ref.WeakReference;

/**
 * Created by lihoudong204 on 2018/10/23
 * Dialog类型加载样式基类
 */
public abstract class BaseLoadingDialog implements ILoadingDialog {
    private Activity activity;
    private Dialog dialog;
    private Dialog customDialog;

    protected String content;
    protected boolean cancelable = true;
    private WeakReference<Activity> weakActivity;
    private boolean changed;
    protected DialogInterface.OnDismissListener onDismissListener;
    protected DialogInterface.OnCancelListener onCancelListener;
    protected DialogInterface.OnKeyListener onKeyListener;

    public BaseLoadingDialog(Activity activity) {
        this.activity = activity;
        weakActivity = new WeakReference<>(activity);
    }
    @Override
    public void show() {
        Utils.runOnUiThread(postShowRunnable);
    }

    @Override
    public void hide() {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss(); //Dialog自己会判断并放在主线程处理
            dialog = null;
            changed = true;
        }
    }

    @Override
    public boolean isShowing() {
        Dialog target = customDialog != null ? customDialog : dialog;
        if (target != null) {
            return target.isShowing();
        }
        return false;
    }

    @Override
    public ILoadingDialog content(String content) {
        if (!Utils.equals(this.content, content)) {
            this.content = content;
            changed = true;
        }
        return this;
    }

    @Override
    public ILoadingDialog content(int resId) {
        return content(activity.getResources().getString(resId));
    }

    @Override
    public ILoadingDialog cancelable(boolean cancelable) {
        if (this.cancelable != cancelable) {
            this.cancelable = cancelable;
            changed = true;
        }
        return this;
    }

    @Override
    public ILoadingDialog onDismissListener(DialogInterface.OnDismissListener listener) {
        if (onDismissListener != listener) {
            onDismissListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public ILoadingDialog onCancelListener(DialogInterface.OnCancelListener listener) {
        if (onCancelListener != listener) {
            onCancelListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public ILoadingDialog onKeyListener(DialogInterface.OnKeyListener listener) {
        if (onKeyListener != listener) {
            onKeyListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public ILoadingDialog customDialog(Dialog dialog) {
        if (this.customDialog != dialog) {
            this.customDialog = dialog;
            changed = true;
        }
        return this;
    }

    private Runnable postShowRunnable = new Runnable() {
        @Override
        public void run() {
            Dialog target = null;
            if (customDialog != null) {
                if (dialog != null && dialog.isShowing()) { //现销毁之前的dialog
                    dialog.dismiss();
                }
                target = customDialog;
            }
            if (target == null) {
                if (dialog == null) {
                    dialog = createDialog(activity);
                }
                target = dialog;
            }
            if (changed && customDialog == null) {
                update();
            }

            if (!target.isShowing()) {
                if(weakActivity.get() != null && !weakActivity.get().isDestroyed() && !weakActivity.get().isFinishing()){
                    target.show();
                }
            }
            changed = false;

        }
    };

    /**
     * 创建Dialog
     * @param activity
     * @return
     */
    protected abstract Dialog createDialog(Activity activity);

    /**
     * 更新
     */
    protected abstract void update();
}
