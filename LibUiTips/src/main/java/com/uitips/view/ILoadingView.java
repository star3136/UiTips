package com.uitips.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lihoudong204 on 2018/10/19
 * View类型的加载样式的接口
 */
public interface ILoadingView {
    /**
     * 显示
     * @param parent
     */
    void show(ViewGroup parent);

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
     * 设置内容(非customView)
     * @param content
     * @return
     */
    ILoadingView content(String content);

    /**
     * 设置内容(非customView)
     * @param resId
     * @return
     */
    ILoadingView content(int resId);
    /**
     * 设置自定义的View
     * 如果调用这个方法设置了自定义的View，则优先显示这个自定义的View
     * @param view
     * @return
     */
    ILoadingView customView(View view);

    /**
     * 工厂
     */
    interface ILoadingViewFactory{
        ILoadingView create();
    }
}
