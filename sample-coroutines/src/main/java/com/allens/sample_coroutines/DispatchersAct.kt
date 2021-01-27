package com.allens.sample_coroutines

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class DispatchersAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linear = findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text = "Empty"
            setOnClickListener {
                todoModuleDefault()
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "Default"
            setOnClickListener {
                todoModuleDefault(Dispatchers.Default)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "IO"
            setOnClickListener {
                todoModuleDefault(Dispatchers.IO)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "Unconfined"
            setOnClickListener {
                todoModuleDefault(Dispatchers.Unconfined)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "Main"
            setOnClickListener {
                todoModuleDefault(Dispatchers.Main)
            }
        })
    }

    private fun todoModuleDefault(context: CoroutineContext = EmptyCoroutineContext) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(context,start = CoroutineStart.LAZY) {
                buffer.append("->执行协程A")
                buffer.append("[${Thread.currentThread().name}]")
                delay(50)
                buffer.append("->执行协程B")
                buffer.append("[${Thread.currentThread().name}]")
                delay(50)
                buffer.append("->finish")
            }
            buffer.append("->启动协程")
            job.start()
            Thread.sleep(200)
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }
}