package com.uitips.view.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

import com.uitips.R;
import com.uitips.view.ILoadingDialog;
import com.uitips.view.IWarnDialog;
import com.uitips.view.common.Utils;

import java.lang.ref.WeakReference;

/**
 * Created by lihoudong204 on 2018/10/23
 * Dialog类型的提醒基类
 */
public abstract class BaseWarnDialog implements IWarnDialog, View.OnClickListener {
    private Activity activity;

    protected String title;
    protected String okButtonText;
    protected int okButtonTextColor;
    protected View.OnClickListener okListener;
    protected String cancelButtonText;
    protected int cancelButtonTextColor;
    protected View.OnClickListener cancelListener;
    protected boolean cancelable;
    protected int style;
    protected String content;
    protected DialogInterface.OnDismissListener onDismissListener;
    protected DialogInterface.OnCancelListener onCancelListener;
    protected DialogInterface.OnKeyListener onKeyListener;

    protected boolean changed = true;

    private Dialog dialog;
    private Dialog customDialog;
    private WeakReference<Activity> weakActivity;

    public BaseWarnDialog(Activity activity) {
        this.activity = activity;

        initDefault();
    }

    private void initDefault() {
        okButtonText = activity.getResources().getString(R.string.ui_tips_ok);
        okListener = this;
        cancelButtonText = activity.getResources().getString(R.string.ui_tips_cancel);
        cancelListener = this;
        cancelable = true;
        style = SINGLE_BUTTON;

        weakActivity = new WeakReference<>(activity);
    }
    @Override
    public void show() {
        Utils.runOnUiThread(postShowRunable);
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
    public void hide() {
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
            changed = true;
        }
    }

    @Override
    public IWarnDialog title(String title) {
        if (!Utils.equals(this.title, title)) {
            this.title = title;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog okButtonText(String text) {
        if (!Utils.equals(this.okButtonText, text)) {
            this.okButtonText = text;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog okButtonTextColor(int color) {
        if (this.okButtonTextColor != color) {
            this.okButtonTextColor = color;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog okButtonClick(View.OnClickListener listener) {
        if (this.okListener != listener) {
            this.okListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog cancelButtonText(String text) {
        if (!Utils.equals(this.cancelButtonText, text)) {
            this.cancelButtonText = text;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog cancelButtonTextColor(int color) {
        if (this.cancelButtonTextColor != color) {
            this.cancelButtonTextColor = color;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog cancelButtonClick(View.OnClickListener listener) {
        if (this.cancelListener != listener) {
            this.cancelListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog cancelable(boolean cancelable) {
        if (this.cancelable != cancelable) {
            this.cancelable = cancelable;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog content(String content) {
        if (!Utils.equals(this.content, content)) {
            this.content = content;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog title(int resId) {
        return title(activity.getResources().getString(resId));
    }

    @Override
    public IWarnDialog okButtonText(int resId) {
        return okButtonText(activity.getResources().getString(resId));
    }

    @Override
    public IWarnDialog cancelButtonText(int resId) {
        return cancelButtonText(activity.getResources().getString(resId));
    }

    @Override
    public IWarnDialog content(int resId) {
        this.content = activity.getResources().getString(resId);
        return content(activity.getResources().getString(resId));
    }

    @Override
    public IWarnDialog onDismissListener(DialogInterface.OnDismissListener listener) {
        if (onDismissListener != listener) {
            onDismissListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog onCancelListener(DialogInterface.OnCancelListener listener) {
        if (onCancelListener != listener) {
            onCancelListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog onKeyListener(DialogInterface.OnKeyListener listener) {
        if (onKeyListener != listener) {
            onKeyListener = listener;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog style(int style) {
        if (this.style != style) {
            this.style = style;
            changed = true;
        }
        return this;
    }

    @Override
    public IWarnDialog customDialog(Dialog dialog) {
        if (this.customDialog != dialog) {
            this.customDialog = dialog;
            changed = true;
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        hide();
    }

    protected abstract Dialog createDialog(Activity activity);

    protected abstract void update();


    private Runnable postShowRunable = new Runnable() {
        @Override
        public void run() {
            Dialog target = null;
            if (customDialog != null) {
                if (dialog != null && dialog.isShowing()) { //先销毁之前的dialog
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
}
