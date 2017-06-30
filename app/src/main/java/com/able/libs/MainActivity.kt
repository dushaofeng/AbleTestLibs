package com.able.libs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.able.libs.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        bezierView.startAnimation();
    }
}
