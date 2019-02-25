package com.uitips.view.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.uitips.R;

public class CustomDialog extends Dialog implements View.OnClickListener {
    public static final int STYLE_ONE_BUTTON = 0;
    public static final int STYLE_TWO_BUTTON = 1;

    private Context context;
    private TextView tvTitle;
    private TextView tvMessage;
    private TextView tvNegative;
    private TextView tvPositive;
    private View lineView;

    private CharSequence title;
    private CharSequence message;
    private CharSequence negativeText;
    private CharSequence positiveText;
    private int negativeColor = Color.parseColor("#333333");
    private int positiveColor = Color.parseColor("#333333");
    private View.OnClickListener positiveListener;
    private View.OnClickListener negativeListener;
    private int style = STYLE_TWO_BUTTON;



    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.uitips_dialog_custom);
        configWindow();


        tvTitle = findViewById(R.id.tv_title);
        tvMessage = findViewById(R.id.tv_message);
        tvNegative = findViewById(R.id.tv_negative);
        tvPositive = findViewById(R.id.tv_positive);
        lineView = findViewById(R.id.line);
        tvNegative.setOnClickListener(this);
        tvPositive.setOnClickListener(this);

        setTitle(title);
        setMessage(message);
        setPositiveButton(positiveText, positiveListener);
        setNegativeButton(negativeText, negativeListener);
        setPositiveButtonColor(positiveColor);
        setNegativeButtonColor(negativeColor);
        setStyle(style);
    }

    private void configWindow() {
        Window window = getWindow();
        window.setBackgroundDrawable(null);
        WindowManager.LayoutParams attrs = window.getAttributes();
        int width = window.getWindowManager().getDefaultDisplay().getWidth();
        attrs.width = (int) (width * 0.8f);
        attrs.dimAmount = 0.4f;
        attrs.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(attrs);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_positive){
            if (positiveListener != null) {
                positiveListener.onClick(v);
            } else {
                dismiss();
            }
        } else if(v.getId() == R.id.tv_negative){
            if (negativeListener != null) {
                negativeListener.onClick(v);
            } else {
                dismiss();
            }
        }
    }

    public void setTitle(CharSequence title){
        this.title = title;
        if(tvTitle != null){
            tvTitle.setText(title);
        }
    }

    public void setMessage(CharSequence message){
        this.message = message;
        if (tvMessage != null) {
            tvMessage.setText(message);
        }
    }

    public void setMessage(int resId){
        this.message = context.getString(resId);
        if (tvMessage != null) {
            tvMessage.setText(resId);
        }
    }

    public void setPositiveButton(CharSequence text, View.OnClickListener onClickListener){
        this.positiveText = text;
        this.positiveListener = onClickListener;
        if (tvPositive != null) {
            tvPositive.setText(text);
        }
    }

    public void setNegativeButton(CharSequence text, View.OnClickListener onClickListener){
        this.negativeText = text;
        this.negativeListener = onClickListener;
        if (tvNegative != null) {
            tvNegative.setText(text);
        }
    }

    public void setNegativeButtonColor(int color){
        this.negativeColor = color;
        if (tvNegative != null) {
            tvNegative.setTextColor(color);
        }
    }

    public void setPositiveButtonColor(int color){
        this.positiveColor = color;
        if(tvPositive != null){
            tvPositive.setTextColor(color);
        }
    }

    public void setStyle(int style){
        this.style = style;
        if (tvNegative == null) {
            return;
        }
        if (style == STYLE_ONE_BUTTON) {
            tvNegative.setVisibility(View.GONE);
            lineView.setVisibility(View.GONE);
        }else if(style == STYLE_TWO_BUTTON){
            tvNegative.setVisibility(View.VISIBLE);
            lineView.setVisibility(View.VISIBLE);
        }
    }
}
