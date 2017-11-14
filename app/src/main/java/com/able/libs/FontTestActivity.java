package com.able.libs;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by du.shaofeng on 2017/11/14.
 */
@EActivity(R.layout.font_test_activity_layout)
public class FontTestActivity extends AppCompatActivity {
    @AfterViews
    void onAfterViews() {

    }
}
