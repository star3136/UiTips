package com.uitips.view.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;

import com.uitips.view.IWarnDialog;
import com.uitips.view.base.BaseWarnDialog;

/**
 * Created by lihoudong204 on 2018/10/22
 * 默认的提示Dialog
 */
public class CommonWarnDialog extends BaseWarnDialog {


    private CustomDialog dialog;

    protected CommonWarnDialog(Activity activity) {
        super(activity);
    }


    @Override
    protected Dialog createDialog(Activity activity) {
        dialog = new CustomDialog(activity);
        okButtonTextColor = Color.parseColor("#333333");
        cancelButtonTextColor = Color.parseColor("#333333");
        return dialog;
    }

    @Override
    protected void update() {
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setPositiveButton(okButtonText, okListener);
        dialog.setNegativeButton(cancelButtonText, cancelListener);
        dialog.setPositiveButtonColor(okButtonTextColor);
        dialog.setNegativeButtonColor(cancelButtonTextColor);
        dialog.setStyle(style == SINGLE_BUTTON ? CustomDialog.STYLE_ONE_BUTTON : CustomDialog.STYLE_TWO_BUTTON);
        dialog.setCancelable(cancelable);
        dialog.setOnDismissListener(onDismissListener);
        dialog.setOnCancelListener(onCancelListener);
        dialog.setOnKeyListener(onKeyListener);
    }

    public static class CommonWarnDialogFactory implements IWarnDialogFactory{
        @Override
        public IWarnDialog create(Activity activity) {
            return new CommonWarnDialog(activity);
        }
    }
}
