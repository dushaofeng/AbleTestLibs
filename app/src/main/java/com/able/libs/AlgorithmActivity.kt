package com.able.libs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.able.libs.R.layout.algorithm_layout
import kotlinx.android.synthetic.main.algorithm_layout.*
import java.util.*

/**
 * Created by Able on 2017/8/29.
 * 测试各种算法的效率
 */
class AlgorithmActivity : AppCompatActivity(), View.OnClickListener {
    private val ARRAY_COUNT = 10*10000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(algorithm_layout)
        bt_maopao.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Maopao()
    }


    /*
    冒泡算法
     */
    fun Maopao() {
        val start = System.currentTimeMillis()
        val sortList = IntArray(ARRAY_COUNT)
        val ra = Random()
        for (i in sortList.indices) {
            sortList[i] = ra.nextInt(ARRAY_COUNT)
        }
        val len = sortList.size
        for (i in 0..len - 1 - 1) {
            for (j in 0..len - 1 - i - 1) {
                if (sortList[j] > sortList[j + 1]) {        // 相邻元素两两对比
                    val temp = sortList[j + 1]        // 元素交换
                    sortList[j + 1] = sortList[j]
                    sortList[j] = temp
                }
            }
        }
        val end = System.currentTimeMillis()
        Log.d("ssss", "耗时：" + (end - start))
    }
}
