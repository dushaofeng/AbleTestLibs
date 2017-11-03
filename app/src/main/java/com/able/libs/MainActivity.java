package com.able.libs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Able on 2017/10/15.
 */
@EActivity(R.layout.super_main_layout)
public class MainActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Click
    void gotoKotlinAndBezier() {
        startActivity(new Intent(this, KotlinAndBezierTestActivity.class));
    }

    @Click
    void gotoDbflowAndAA() {
        AATestActivity_.intent(this).start();
    }

    @Click
    void gotoAlgorithm() {
        startActivity(new Intent(this, AlgorithmActivity.class));
    }

    @Click
    void gotoSensey() {
        SenseyTestActivity_.intent(this).start();
    }
}
