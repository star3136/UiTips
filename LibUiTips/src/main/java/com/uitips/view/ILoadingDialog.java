package com.uitips.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by lihoudong204 on 2018/10/22
 * Dialog类型的加载样式接口
 */
public interface ILoadingDialog {

    /**
     * 显示
     */
    void show();

    /**
     * 隐藏
     */
    void hide();

    /**
     * 是否正在显示
     * @return
     */
    boolean isShowing();

    /**
     * 设置内容(非customDialog)
     * @param content
     * @return
     */
    ILoadingDialog content(String content);

    /**
     * 设置内容(非customDialog)
     * @param resId
     * @return
     */
    ILoadingDialog content(int resId);

    /**
     * 设置是否可以取消(非customDialog)
     * @param cancelable
     * @return
     */
    ILoadingDialog cancelable(boolean cancelable);

    /**
     * 设置dimiss监听器
     * @param listener
     * @return
     */
    ILoadingDialog onDismissListener(DialogInterface.OnDismissListener listener);

    /**
     * 设置cancle监听器
     * @param listener
     * @return
     */
    ILoadingDialog onCancelListener(DialogInterface.OnCancelListener listener);

    /**
     * 设置key监听器
     * @param onKeyListener
     * @return
     */
    ILoadingDialog onKeyListener(DialogInterface.OnKeyListener listener);
    /**
     * 设置自定义的Dialog
     * 如果调用这个方法设置了自定义的Dialog，则优先显示这个自定义的Dialog
     * @param dialog
     * @return
     */
    ILoadingDialog customDialog(Dialog dialog);

    /**
     * 工厂
     */
    interface ILoadingDialogFactory{
        ILoadingDialog create(Activity activity);
    }
}
