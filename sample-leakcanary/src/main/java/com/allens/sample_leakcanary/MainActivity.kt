package com.allens.sample_leakcanary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.SystemClock.sleep
import android.util.Log
import com.allens.sample_leakcanary.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linear.addView(MaterialButton(this).apply {
            text = "直接finish()"
            setOnClickListener {
                finish()
            }
        })

        binding.linear.addView(MaterialButton(this).apply {
            text = "模拟内存泄露"
            setOnClickListener {
                Test(this).test()
                finish()
            }
        })
    }

    protected fun finalize() {
        log("Activity 被回收了")

    }


    private fun testGC() {
        thread {
            log("准备触发GC")
            sleep(5000)
            log("触发GC 观察Activity是否被回收")
            Runtime.getRuntime().gc()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
        testGC()
    }
}


class Test(var any: Any?) : Thread() {

    override fun run() {
        super.run()
        //将Activity赋值给局部变量
        val local = any
        any = null

        SystemClock.sleep(10000)
        log("local is ${local.toString()}")
    }

    fun test() {
        start()
    }
}

fun log(info: String) {
    Log.i("allens-tag", info)
}