package com.uitips;

import com.uitips.view.common.CommonWarnDialog;
import com.uitips.view.common.CommonWarnView;
import com.uitips.view.common.CommonLoadingDialog;
import com.uitips.view.common.CommonLoadingView;
import com.uitips.view.IWarnDialog;
import com.uitips.view.IWarnView;
import com.uitips.view.ILoadingDialog;
import com.uitips.view.ILoadingView;

/**
 * Created by lihoudong204 on 2018/10/19
 * 全局的配置
 */
public class UiTipsConfig {
    private ILoadingView.ILoadingViewFactory loadingViewFactory;
    private IWarnView.IWarnViewFactory warnViewFactory;
    private ILoadingDialog.ILoadingDialogFactory loadingDialogFactory;
    private IWarnDialog.IWarnDialogFactory warnDialogFactory;

    private UiTipsConfig(Builder builder) {
        this.loadingViewFactory = builder.loadingViewFactory;
        this.warnViewFactory = builder.warnViewFactory;
        this.warnDialogFactory = builder.warnDialogFactory;
        this.loadingDialogFactory = builder.loadingDialogFactory;


        if (loadingViewFactory == null) {
            loadingViewFactory = new CommonLoadingView.CommonLoadingViewFactory();
        }

        if (warnViewFactory == null) {
            warnViewFactory = new CommonWarnView.CommonWarnViewFactory();
        }

        if (loadingDialogFactory == null) {
            loadingDialogFactory = new CommonLoadingDialog.CommonLoadingDialogFactory();
        }

        if (warnDialogFactory == null) {
            warnDialogFactory = new CommonWarnDialog.CommonWarnDialogFactory();
        }
    }


    public void set() {
        UiTips.getInstance().config(this);
    }

    Builder newBuilder() {
        return new Builder(this);
    }

    public ILoadingView.ILoadingViewFactory getLoadingViewFactory() {
        return loadingViewFactory;
    }

    public IWarnView.IWarnViewFactory getWarnViewFactory() {
        return warnViewFactory;
    }

    public ILoadingDialog.ILoadingDialogFactory getLoadingDialogFactory() {
        return loadingDialogFactory;
    }

    public IWarnDialog.IWarnDialogFactory getWarnDialogFactory() {
        return warnDialogFactory;
    }

    public static class Builder {
        private ILoadingView.ILoadingViewFactory loadingViewFactory;
        private IWarnView.IWarnViewFactory warnViewFactory;
        private ILoadingDialog.ILoadingDialogFactory loadingDialogFactory;
        private IWarnDialog.IWarnDialogFactory warnDialogFactory;

        Builder() {

        }

        Builder(UiTipsConfig config) {
            loadingViewFactory = config.loadingViewFactory;
            warnViewFactory = config.warnViewFactory;
            loadingDialogFactory = config.loadingDialogFactory;
            warnDialogFactory = config.warnDialogFactory;
        }

        /**
         * 设置全局的加载样式工厂
         * @param loadingViewFactory
         * @return
         */
        public Builder loadingViewFactory(ILoadingView.ILoadingViewFactory loadingViewFactory) {
            this.loadingViewFactory = loadingViewFactory;
            return this;
        }

        /**
         * 设置全局的错误提示工厂
         * @param errorViewFactory
         * @return
         */
        public Builder warnViewFactory(IWarnView.IWarnViewFactory errorViewFactory) {
            this.warnViewFactory = errorViewFactory;
            return this;
        }

        public Builder loadingDialogFactory(ILoadingDialog.ILoadingDialogFactory loadingDialogFactory) {
            this.loadingDialogFactory = loadingDialogFactory;
            return this;
        }

        public Builder warnDialogFactory(IWarnDialog.IWarnDialogFactory warnDialogFactory) {
            this.warnDialogFactory = warnDialogFactory;
            return this;
        }

        public UiTipsConfig build(){
            return new UiTipsConfig(this);
        }
    }
}
