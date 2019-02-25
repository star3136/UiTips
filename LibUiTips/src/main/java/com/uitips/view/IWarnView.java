package com.uitips.view;

import android.support.annotation.IntDef;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lihoudong204 on 2018/10/19
 * View类型的提示接口
 */
public interface IWarnView {
    /**
     * 支持的错误类型
     */
    int NETWORK = 0;  //网络错误
    int EMPTY_DATA = 1;  //没有数据
    int NOT_FOUND = 2; //页面不存在
    int SEARCH_EMPTY = 3; //搜索无结果

    int UNKNOWN = 999;  //未知错误
    int CUSTOM = 1000;  //使用指定的图片

    @IntDef({NETWORK, EMPTY_DATA, NOT_FOUND, SEARCH_EMPTY, UNKNOWN, CUSTOM})
    @interface WarnViewType {

    }

    /**
     * 显示
      * @param parent 被添加到的父View
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

    IWarnView type(@WarnViewType int type);

    /**
     * 设置自定义的View
     * 如果调用这个方法设置了自定义的View，则优先显示这个自定义的View
     * @param view
     * @return
     */
    IWarnView customView(View view);

    /**
     * 设置内容(非customView)
     * @param content
     * @return
     */
    IWarnView content(String content);

    /**
     * 设置内容(非customView)
     * @param resId
     * @return
     */
    IWarnView content(int resId);
    /**
     * 设置提醒的图片，需要type为CUSTOM才会生效
     * @param resId
     * @return
     */
    IWarnView warnImage(int resId);

    /**
     * 工厂
     */
    interface IWarnViewFactory {
        IWarnView create();
    }
}
