package com.able.libs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.able.libs.R.layout.algorithm_layout
import kotlinx.android.synthetic.main.algorithm_layout.*
import java.util.*
import kotlin.concurrent.thread

/**
 * Created by Able on 2017/8/29.
 * 测试各种算法的效率
 */
class AlgorithmActivity : AppCompatActivity() {
    private val ARRAY_COUNT = 10 * 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(algorithm_layout)
    }

    fun click(view: View) {
        when (view) {
        //冒泡算法
            bt_maopao -> {
                bt_maopao.text = "正在计算..."
                thread { maoPao() }
            }
            bt_xuanze -> {
                bt_xuanze.text = "正在计算..."
                thread { xuanZe() }
            }
            bt_charu -> {
                bt_charu.text = "正在计算..."
                thread { chaRu() }
            }
            else -> {
                Log.d("ssss", "Error")
            }
        }
    }

    /*
    冒泡算法
     */
    fun maoPao() {
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
        runOnUiThread { bt_maopao.text = "冒泡算法耗时：" + (end - start) / 1000 + "秒" }
    }

    /*
    插入算法
     */
    fun chaRu() {

    }

    /*
    选择算法
     */
    fun xuanZe() {

    }
}
