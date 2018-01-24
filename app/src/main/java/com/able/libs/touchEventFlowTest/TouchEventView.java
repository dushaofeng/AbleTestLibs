package com.able.libs.touchEventFlowTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by du.shaofeng on 2018/1/24.
 */

public class TouchEventView extends android.support.v7.widget.AppCompatButton {
    public TouchEventView(Context context) {
        super(context);
    }

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(this.getClass().getName(), "dispatchTouchEvent:" + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(this.getClass().getName(), "onTouchEvent:" + event.getAction());
        return super.onTouchEvent(event);
    }
}
