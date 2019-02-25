package com.uitips.view.common;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uitips.R;
import com.uitips.view.ILoadingView;
import com.uitips.view.base.BaseLoadingView;

/**
 * Created by lihoudong204 on 2018/10/19
 * 默认的加载样式
 */
public class CommonLoadingView extends BaseLoadingView {
    private ProgressBar progressBar;
    private TextView textView;

    protected CommonLoadingView() {

    }
    @Override
    protected View createLayout(ViewGroup parent) {
        Context context = parent.getContext();
        final RelativeLayout loadingLayout = new RelativeLayout(context);
        loadingLayout.setGravity(Gravity.CENTER_VERTICAL);
        progressBar = new ProgressBar(context);
//        progressBar.setIndeterminateDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.common_shape_progress_circle, null));
        progressBar.setId(R.id.ui_tips_common_progress_bar);
        RelativeLayout.LayoutParams progressBarLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBarLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        loadingLayout.addView(progressBar, progressBarLp);

        textView = new TextView(context);
        textView.setId(R.id.ui_tips_common_text_view);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.ui_tips_common_text_size));

        RelativeLayout.LayoutParams textViewLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewLp.topMargin = context.getResources().getDimensionPixelSize(R.dimen.ui_tips_common_margin_normal);
        textViewLp.addRule(RelativeLayout.BELOW, R.id.ui_tips_common_progress_bar);
        textViewLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        loadingLayout.addView(textView, textViewLp);

        return loadingLayout;
    }

    @Override
    protected void update() {
        textView.setText(content);
    }

    public static class CommonLoadingViewFactory implements ILoadingViewFactory{
        @Override
        public ILoadingView create() {
            return new CommonLoadingView();
        }
    }
}
