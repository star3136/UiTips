package com.uitips.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.IntDef;
import android.view.View;

/**
 * Created by lihoudong204 on 2018/10/22
 * Dialog类型的提示接口
 */
public interface IWarnDialog {
    /**
     * 对话框的风格
     */
    int SINGLE_BUTTON = 0;
    int TWO_BUTTON = 1;

    @IntDef({SINGLE_BUTTON, TWO_BUTTON})
    @interface WarnDialogStyle{

    }

    /**
     * 显示
     */
    void show();

    /**
     * 是否正在显示
     * @return
     */
    boolean isShowing();
    /**
     * 隐藏
     */
    void hide();

    /**
     * 设置标题(非customDialog)
     * @param title
     * @return
     */
    IWarnDialog title(String title);

    /**
     * 设置标题(非customDialog)
     * @param resId
     * @return
     */
    IWarnDialog title(int resId);

    /**
     * 设置确定按钮的文本(非customDialog)
     * @param text
     * @return
     */
    IWarnDialog okButtonText(String text);

    /**
     * 设置确定按钮的文本(非customDialog)
     * @param resId
     * @return
     */
    IWarnDialog okButtonText(int resId);

    /**
     * 设置确定按钮的文本颜色(非customDialog)
     * @param color
     * @return
     */
    IWarnDialog okButtonTextColor(int color);
    /**
     * 设置确定按钮的点击事件(非customDialog)
     * @param listener
     * @return
     */
    IWarnDialog okButtonClick(View.OnClickListener listener);

    /**
     * 设置取消按钮的文本(非customDialog)
     * @param text
     * @return
     */
    IWarnDialog cancelButtonText(String text);

    /**
     * 设置取消按钮的文本(非customDialog)
     * @param resId
     * @return
     */
    IWarnDialog cancelButtonText(int resId);

    /**
     * 设置取消按钮的文本颜色(非customDialog)
     * @param color
     * @return
     */
    IWarnDialog cancelButtonTextColor(int color);

    /**
     * 设置取消按钮的点击事件(非customDialog)
     * @param listener
     * @return
     */
    IWarnDialog cancelButtonClick(View.OnClickListener listener);

    /**
     * 设置是否可以取消(非customDialog)
     * @param cancelable
     * @return
     */
    IWarnDialog cancelable(boolean cancelable);

    /**
     * 设置内容(非customDialog)
     * @param content
     * @return
     */
    IWarnDialog content(String content);

    /**
     * 设置内容(非customDialog)
     * @param resId
     * @return
     */
    IWarnDialog content(int resId);

    /**
     * 设置dimiss监听器
     * @param listener
     * @return
     */
    IWarnDialog onDismissListener(DialogInterface.OnDismissListener listener);

    /**
     * 设置cancle监听器
     * @param listener
     * @return
     */
    IWarnDialog onCancelListener(DialogInterface.OnCancelListener listener);

    /**
     * 设置key监听器
     * @param listener
     * @return
     */
    IWarnDialog onKeyListener(DialogInterface.OnKeyListener listener);
    /**
     * 设置dialog的风格(非customDialog)
     * @param style
     * @return
     */
    IWarnDialog style(@WarnDialogStyle int style);

    /**
     * 设置自定义的Dialog
     * 如果调用这个方法设置了自定义的Dialog，则优先显示这个自定义的Dialog
     * @param dialog
     * @return
     */
    IWarnDialog customDialog(Dialog dialog);

    /**
     * 工厂
     */
    interface IWarnDialogFactory {
        IWarnDialog create(Activity activity);
    }
}
