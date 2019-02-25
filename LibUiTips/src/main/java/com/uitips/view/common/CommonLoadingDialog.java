package com.uitips.view.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import com.uitips.view.ILoadingDialog;
import com.uitips.view.base.BaseLoadingDialog;

/**
 * Created by lihoudong204 on 2018/10/22
 * 默认的加载框
 */
public class CommonLoadingDialog extends BaseLoadingDialog {
    ProgressDialog dialog;

    protected CommonLoadingDialog(Activity activity) {
        super(activity);
    }

    @Override
    protected Dialog createDialog(Activity activity) {
        dialog = new ProgressDialog(activity);
        return dialog;
    }

    @Override
    protected void update() {
        dialog.setMessage(content);
        dialog.setCancelable(cancelable);
        dialog.setOnDismissListener(onDismissListener);
        dialog.setOnCancelListener(onCancelListener);
        dialog.setOnKeyListener(onKeyListener);
    }

    public static class CommonLoadingDialogFactory implements ILoadingDialogFactory {
        @Override
        public ILoadingDialog create(Activity activity) {
            return new CommonLoadingDialog(activity);
        }
    }
}
