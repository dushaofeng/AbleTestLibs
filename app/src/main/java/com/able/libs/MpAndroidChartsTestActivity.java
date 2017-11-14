package com.able.libs;

import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by du.shaofeng on 2017/11/14.
 */

@EActivity(R.layout.mp_android_chart_activity_layout)
public class MpAndroidChartsTestActivity extends AppCompatActivity {
    @ViewById(R.id.barchart)
    BarChart mBarChart;

    @AfterViews
    void onAfterViews() {

    }
}
