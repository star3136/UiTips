package com.uitips.view.common;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uitips.R;
import com.uitips.view.IWarnView;
import com.uitips.view.base.BaseWarnView;

/**
 * Created by lihoudong204 on 2018/10/19
 * 默认的提示View实现
 */
public class CommonWarnView extends BaseWarnView {

    private LinearLayout errorLayout;
    private ImageView ivError;
    private TextView tvError;

    protected CommonWarnView() {

    }
    @Override
    public IWarnView content(String content) {
        this.content = content;
        return this;
    }

    private int getErrorRes(@WarnViewType int type) {
        switch (type) {
            case NETWORK:
                return R.drawable.common_ic_error_network;
            case EMPTY_DATA:
                return R.drawable.common_ic_error_empty;
            case NOT_FOUND:
                return R.drawable.common_ic_error_not_found;
            case SEARCH_EMPTY:
                return R.drawable.common_ic_error_search_empty;
            case CUSTOM:
                return warnImage;
            default:
                return 0;
        }
    }

    @Override
    protected void update() {
        ivError.setImageResource(getErrorRes(type));
        tvError.setText(content);
    }

    @Override
    protected View createLayout(ViewGroup parent) {
        Context context = parent.getContext();
        errorLayout = new LinearLayout(context);
        errorLayout.setOrientation(LinearLayout.VERTICAL);
        errorLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        ivError = new ImageView(context);
        ivError.setScaleType(ImageView.ScaleType.CENTER_CROP);
        errorLayout.addView(ivError, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tvError = new TextView(context);
        tvError.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.ui_tips_common_text_size));
        tvError.setTextColor(context.getResources().getColor(R.color.ui_tips_common_text_color));
        LinearLayout.LayoutParams tvErrorLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvErrorLp.topMargin = context.getResources().getDimensionPixelSize(R.dimen.ui_tips_common_margin_normal);
        errorLayout.addView(tvError, tvErrorLp);

        errorLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                errorLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) errorLayout.getLayoutParams();
                lp.topMargin = (int) (((ViewGroup) errorLayout.getParent()).getHeight() * 0.26);
                errorLayout.setLayoutParams(lp);
                return false;
            }
        });
        return errorLayout;
    }

    public static class CommonWarnViewFactory implements IWarnViewFactory {
        @Override
        public IWarnView create() {
            return new CommonWarnView();
        }
    }
}
