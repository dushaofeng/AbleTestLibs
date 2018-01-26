package com.able.libs.touchEventFlowTest;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.able.libs.R;

import org.androidannotations.annotations.EActivity;

/**
 * Created by du.shaofeng on 2018/1/24.
 */

@EActivity(R.layout.touch_event_test)
public class TouchEventFlowActivity extends AppCompatActivity {
    public static final String TAG = "TouchEventTag";

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean returnResult = super.dispatchTouchEvent(event);
        Log.d(TouchEventFlowActivity.TAG, "Activity--dispatchTouchEvent:" + event.getAction() + ",returnResult:" + returnResult);
        return returnResult;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean returnResult = super.onTouchEvent(event);
//        returnResult = true;
        Log.d(TouchEventFlowActivity.TAG, "Activity--onTouchEvent:" + event.getAction() + ",returnResult:" + returnResult);
        return returnResult;
    }
}
