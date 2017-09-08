package com.able.libs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
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
    private var ARRAY_COUNT = 1000
    private val DEBUG: Boolean = false
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
            bt_xier -> {
                bt_xier.text = "正在计算..."
                thread { xier() }
            }
            bt_kuaipai -> {
                bt_kuaipai.text = "正在计算..."
                thread { kuaipai() }
            }
            bt_dui -> {
                bt_dui.text = "正在计算..."
                thread { dui() }
            }
            bt_jishu -> {
                bt_jishu.text = "正在计算..."
                thread { jishu() }
            }
            else -> {
                makeLog("Error")
            }
        }
    }

    private fun jishu() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("Before" + sortList.contentToString())
        //Todo
        //寻找当前序列中的最大值，目的是计算最大的位数
        var max = 0
        for (i in 0..sortList.size - 1) {
            if (sortList[i] > max) {
                //找到当前序列最大数
                max = sortList[i]
            }
        }
        //通过最大数找到最大数的位数
        var maxDigit = max.toString().length
        var mod = 10
        var dev = 1
        for (i in 0 until maxDigit) {
            var counter = mutableMapOf<Int, ArrayList<Int>>()
            for (j in 0 until sortList.size) {
                // 取出某个数的第mod位的值
                // 比如数字123，123%100=23,23继续计算23/10=2，最终拿到123的中间位数字
                var bucket = (sortList[j] % mod) / dev
                //按照当前位数作为key以此存入counter的map中
                if (counter[bucket] == null) {
                    counter[bucket] = ArrayList<Int>()
                }
                counter[bucket]?.add(sortList[j])
            }
            var pos = 0
            for (i in 0..9) {
                counter[i]?.forEach {
                    //依次将counter中的值放入列表中
                    //第一次经过进--出的操作就对个位数字进行了排序
                    //第二次经过进--出的操作就对十位进行了排序
                    //经过上面两次排序，就达到了个位+十位的综合排序，也就完成了两位数字的整体排序
                    sortList[pos++] = it
                }
            }
            dev *= 10
            mod *= 10
        }
        val end = System.currentTimeMillis()
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_jishu.text = "" + sortList.size + "个数的" + "基数算法耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_jishu.text = "" + sortList.size + "个数的" + "基数算法耗时：" + (end - start) / 1000 + "秒，计算失败！" }
        }
        makeLog("排序是否成功： " + result)
    }

    private fun loopForDui(list: IntArray, start: Int, end: Int) {
        if (start >= end) {
            return
        }
        var dad = start
        var son = dad * 2 + 1
        if (son >= end) {
            //儿子索引已经超过数组最大索引
            return
        }
        if (son + 1 < end && list[son] < list[son + 1]) {
            //说明两个儿子之间右边的更大
            son++
        }
        if (list[dad] <= list[son]) {
            //交换父子
            swapByIndex(list, dad, son)
            //因为父子交换了，因此该分支下面所有堆都要重排
            //并且索引从当前换位的son开始直到该分支最后一位元素
            loopForDui(list, son, end)
        }
    }

    private fun dui() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("Before" + sortList.contentToString())
        //Todo
        var len = sortList.size
        for (i in len / 2 - 1 downTo 0) {
            //这一步的作用就是建立最大堆模型
            //这里选出来的i，就是当前堆的最后一个三角的单位中的爸爸，也就是说从最后一个单元开始向上递增构建最大堆
            loopForDui(sortList, i, len)

        }
        for (i in len - 1 downTo 1) {
            //依次拿出堆顶元素放在数列最后
            swapByIndex(sortList, 0, i)
            //对剩余的0-->i的堆重拍，即可找到剩余数列中的最大值
            loopForDui(sortList, 0, i)
        }
        val end = System.currentTimeMillis()
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_dui.text = "" + sortList.size + "个数的" + "堆算法耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_dui.text = "计算失败！" }
        }
    }

    /*
    快速排序的循环
     */
    fun loopForKuaipai(list: IntArray, start: Int, end: Int) {
        if (start >= end) {
            return
        }
        var mid = list[end]
        var left = start
        var right = end - 1
        while (left < right) {
            //比较左右两边的值，让其小于参考值的放在列表左边，大于参考值的放在列表右边
            while (list[left] <= mid && left < right) {
                left++
            }
            while (list[right] >= mid && left < right) {
                right--
            }
            //此时遇到了 list[left]>mid>list[right]，需要交换两侧
            swapByIndex(list, left, right)
        }
        if (list[left] > list[end]) {
            swapByIndex(list, left, end)
        } else {
            left++
        }
        //递归拆分列表
        loopForKuaipai(list, start, left - 1)
        loopForKuaipai(list, left + 1, end)
    }

    /*
    快速排序
     */
    private fun kuaipai() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("Before" + sortList.contentToString())
        //Todo
        loopForKuaipai(sortList, 0, sortList.size - 1)
        val end = System.currentTimeMillis()
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_kuaipai.text = "" + sortList.size + "个数的" + "快速排序耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_kuaipai.text = "计算失败！" }
        }
    }

    private fun getSortList(): IntArray {
        if (et_count.text.toString().isNotEmpty()) {
            ARRAY_COUNT = et_count.text.toString().toInt()
        }
        if (ARRAY_COUNT < 0) {
            Toast.makeText(this, "数字太小", Toast.LENGTH_SHORT).show()
        }
        var sortList = IntArray(ARRAY_COUNT)
        var ra = Random()
        for (i in sortList.indices) {
            sortList[i] = ra.nextInt(ARRAY_COUNT * 10)
        }
        return sortList
    }

    private fun swapByIndex(list: IntArray, x: Int, y: Int) {
        var temp = list[x]
        list[x] = list[y]
        list[y] = temp
    }

    /*
    冒泡算法
     */
    private fun maoPao() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("Before" + sortList.contentToString())
        var didSwap = false
        for (i in 0 until sortList.size - 1) {
            for (j in 0 until sortList.size - 1 - i) {
                if (sortList[j] > sortList[j + 1]) {
                    swapByIndex(sortList, j, j + 1)
                    didSwap = true
                }
            }
            if (!didSwap) {
                return
            }
        }
        val end = System.currentTimeMillis()
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_maopao.text = "" + sortList.size + "个数的" + "冒泡算法耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_maopao.text = "计算失败！" }
        }
    }

    /*
    插入算法
     */
    private fun chaRu() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("Before" + sortList.contentToString())
        //Todo
        for (i in 1 until sortList.size) {
            for (j in i downTo 1) {
                if (sortList[j] > sortList[j - 1]) {
                    //已经找到当前元素要插入的位置
                    break
                }
                swapByIndex(sortList, j - 1, j)
            }
        }
        val end = System.currentTimeMillis()
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_charu.text = "" + sortList.size + "个数的" + "插入算法耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_charu.text = "计算失败！" }
        }
    }

    /*
    选择算法
     */
    private fun xuanZe() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("Before" + sortList.contentToString())
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
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_xuanze.text = "" + sortList.size + "个数的" + "选择算法耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_xuanze.text = "计算失败！" }
        }
    }

    /*
    希尔排序
    希尔排序是在插入排序基础上优化而来，因为插入排序最好情况是O(n)，最坏是O(n*n)，所以我们可以先优化列表，使其尽可能靠近理想状态，然后再采取O(n)进行排序
     */
    private fun xier() {
        var sortList = getSortList()
        val start = System.currentTimeMillis()
        makeLog("希尔排序 Before" + sortList.contentToString())
        //Todo
        var gap = 1
        while (gap < sortList.size / 3) {
            //寻找合适的步长
            gap = gap * 3 + 1
        }
        while (gap > 0) {
            for (i in gap until sortList.size) {
                var temp = sortList[i]
                var j = i - gap
                while (j >= 0) {
                    //将以temp为元素起点，以gap为步进长度构成的list进行插入运算
                    if (sortList[j] <= temp) {
                        break
                    }
                    sortList[j + gap] = sortList[j]
                    j -= gap
                }
                sortList[j + gap] = temp
            }
            gap /= 3
        }
        val end = System.currentTimeMillis()
        makeLog("After" + sortList.contentToString())
        var result = true
        for (i in 0..sortList.size - 2) {
            if (sortList[i] > sortList[i + 1]) {
                result = false
                break
            }
        }
        if (result) {
            //排序成功
            runOnUiThread { bt_xier.text = "" + sortList.size + "个数的" + "希尔算法耗时：" + (end - start) / 1000 + "秒" + (end - start) % 1000 + "毫秒" }
        } else {
            runOnUiThread { bt_xier.text = "计算失败！" }
        }
    }

    fun makeLog(message: String, tag: String = "ssss") {
        if (DEBUG) {
            Log.d(tag, message)
        }
    }
}
