package com.able.libs;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by du.shaofeng on 2018/2/7.
 */
@EActivity(R.layout.picker_activity_layout)
public class PickerViewTestActivity extends AppCompatActivity {

    @AfterViews
    void onAfterViews() {
    }
}
