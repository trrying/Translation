package com.owm.translation.weidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.owm.translation.R;

/**
 * 自定义Textview，可以设置加粗
 * Created by ouweiming on 2016/11/10.
 */

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttr(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(context, attrs);
    }

    private void setAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        getPaint().setFakeBoldText(typedArray.getBoolean(R.styleable.CustomTextView_text_style_bold, false));
        typedArray.recycle();
    }


}
