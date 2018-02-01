package com.able.libs.touchEventFlowTest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by du.shaofeng on 2018/1/24.
 */

public class TouchEventViewGroup extends LinearLayout {
    public TouchEventViewGroup(Context context) {
        super(context);
    }

    public TouchEventViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean returnResult = super.dispatchTouchEvent(ev);
//        returnResult = false;
        Log.d(TouchEventFlowActivity.TAG, "ViewGroup--dispatchTouchEvent:" + ev.getAction() + ",returnResult:" + returnResult);
        return returnResult;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean returnResult = super.onInterceptTouchEvent(ev);
//        returnResult = false;
        Log.d(TouchEventFlowActivity.TAG, "ViewGroup--onInterceptTouchEvent:" + ev.getAction() + ",returnResult:" + returnResult);
        return returnResult;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean returnResult = super.onTouchEvent(event);
        returnResult = true;
        Log.d(TouchEventFlowActivity.TAG, "ViewGroup--onTouchEvent:" + event.getAction() + ",returnResult:" + returnResult);
        return returnResult;
    }
}
