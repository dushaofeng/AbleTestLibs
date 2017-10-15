package com.able.libs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Created by Able on 2017/10/15.
 */
@EActivity(R.layout.super_main_layout)
public class MainActivity extends AppCompatActivity {
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
