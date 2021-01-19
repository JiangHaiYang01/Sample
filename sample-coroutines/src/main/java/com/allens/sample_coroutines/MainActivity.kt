package com.allens.sample_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linear = findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 DEFAULT"
            setOnClickListener {
                todoModule(CoroutineStart.DEFAULT)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 LAZY"
            setOnClickListener {
                todoModule(CoroutineStart.LAZY)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 ATOMIC"
            setOnClickListener {
                todoModule(CoroutineStart.ATOMIC)
            }
        })

        linear.addView(MaterialButton(this).apply {
            text = "启动模式 UNDISPATCHED"
            setOnClickListener {
                todoModule(CoroutineStart.UNDISPATCHED)
            }
        })
    }

    private fun todoModule(start: CoroutineStart) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(start = start) {
                buffer.append("->执行协程")
                delay(1000)
                buffer.append("->协程执行完成")
            }
            buffer.append("->启动协程")
            job.start()
            //确保一定执行了 start()
            sleep(200)
            buffer.append("->取消协程")
            job.cancel()
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }

}