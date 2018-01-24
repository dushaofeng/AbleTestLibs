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
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(this.getClass().getName(), "dispatchTouchEvent:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(this.getClass().getName(), "onTouchEvent+" + event.getAction());
        return super.onTouchEvent(event);
    }
}
