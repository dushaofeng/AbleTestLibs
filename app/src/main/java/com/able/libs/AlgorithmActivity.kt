package com.able.libs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.able.libs.R.layout.algorithm_layout
import kotlinx.android.synthetic.main.algorithm_layout.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

/**
 * Created by Able on 2017/8/29.
 * 测试各种算法的效率
 */
class AlgorithmActivity : AppCompatActivity() {
    private val ARRAY_COUNT = 100000
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

    fun getSortList(): IntArray {
        var sortList = IntArray(ARRAY_COUNT)
        var ra = Random()
        for (i in sortList.indices) {
            sortList[i] = ra.nextInt(ARRAY_COUNT * 10)
        }
        return sortList
    }

    fun swapByIndex(list: IntArray, x: Int, y: Int) {
        var temp = list[x]
        list[x] = list[y]
        list[y] = temp
    }
    /*
    冒泡算法
     */
    fun maoPao() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        Log.d("ssss","Before"+sortList.contentToString())
        for (i in 0..sortList.size - 2) {
            for (j in 0..sortList.size - 2 - i) {
                if (sortList[j] > sortList[j + 1]) {
                    swapByIndex(sortList, j, j + 1)
                }
            }
        }
        val end = System.currentTimeMillis()
        Log.d("ssss","After"+sortList.contentToString())
        runOnUiThread { bt_maopao.text = ""+sortList.size + "个数的" + "冒泡算法耗时：" + (end - start) / 1000 + "秒" }
    }

    /*
    插入算法
     */
    fun chaRu() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        Log.d("ssss", "Before" + sortList.contentToString())
        //Todo
        for (i in 1..(sortList.size - 1)) {
            for (j in i downTo 1) {
                if (sortList[j] > sortList[j - 1]) {
                    break
                }
                swapByIndex(sortList, j - 1, j)
            }
        }
        val end = System.currentTimeMillis()
        Log.d("ssss", "After" + sortList.contentToString())
        runOnUiThread { bt_charu.text = "" + sortList.size + "个数的" + "插入算法耗时：" + (end - start) / 1000 + "秒" }
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        Log.d("ssss", "排序是否成功： " + result)
    }

    /*
    选择算法
     */
    fun xuanZe() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        Log.d("ssss", "Before" + sortList.contentToString())
        //Todo
        for (i in 0..sortList.size - 2) {
            var minIndex = i
            for (j in i + 1..sortList.size - 1) {
                if (sortList[minIndex] > sortList[j]) {
                    //找到比较小的值的索引
                    minIndex = j
                }
            }
            swapByIndex(sortList, i, minIndex)
        }
        val end = System.currentTimeMillis()
        Log.d("ssss", "After" + sortList.contentToString())
        runOnUiThread { bt_xuanze.text = "" + sortList.size + "个数的" + "选择算法耗时：" + (end - start) / 1000 + "秒" }
    }
}
