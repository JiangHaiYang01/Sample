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
            text = "启动模式 DEFAULT 执行Start"
            setOnClickListener {
                todoModuleDefault(isStart = true)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 DEFAULT 不执行Start"
            setOnClickListener {
                todoModuleDefault(isStart = false)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 LAZY 执行Start"
            setOnClickListener {
                todoModuleLazy(isStart = true)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 LAZY 不执行Start"
            setOnClickListener {
                todoModuleLazy(isStart = false)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 LAZY 调度前被取消"
            setOnClickListener {
                todoModuleLazyCancel()
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 ATOMIC 执行Start"
            setOnClickListener {
                todoModuleAtomic(isStart = true)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 ATOMIC 不执行Start"
            setOnClickListener {
                todoModuleAtomic(isStart = false)
            }
        })

        linear.addView(MaterialButton(this).apply {
            text = "启动模式 UNDISPATCHED 执行Start"
            setOnClickListener {
                todoModuleUnDispatched(isStart = true)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "启动模式 UNDISPATCHED 不执行Start"
            setOnClickListener {
                todoModuleUnDispatched(isStart = false)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "LAZY对比UNDISPATCHED"
            setOnClickListener {
                todoModuleUnDispatched(start = CoroutineStart.LAZY,isStart = true)
            }
        })
    }

    private fun todoModuleAtomic(
        start: CoroutineStart = CoroutineStart.ATOMIC,
        isStart: Boolean = false
    ) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(start = start) {
                buffer.append("->执行协程")
                delay(100)
                buffer.append("->协程执行完成")
            }
            buffer.append("->启动协程")
            //是否start 不会影响结果
            if (isStart) {
                job.start()
            }
            //确保一定执行了 start()
            sleep(20)
            buffer.append("->取消协程")
            job.cancel()
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }

    private fun todoModuleUnDispatched(
        start: CoroutineStart = CoroutineStart.UNDISPATCHED,
        isStart: Boolean = false
    ) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(start = start) {
                buffer.append("->执行协程A")
                buffer.append("[${Thread.currentThread().name}]")
                delay(20)
                buffer.append("->执行协程B")
                buffer.append("[${Thread.currentThread().name}]")
                delay(100)
                buffer.append("->协程执行完成")
            }
            buffer.append("->启动协程")
            //是否start 不会影响结果
            if (isStart) {
                job.start()
            }
            //确保一定执行了 start()
            sleep(50)
            buffer.append("->取消协程")
            job.cancel()
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }
    private fun todoModuleDefault(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        isStart: Boolean = false
    ) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(start = start) {
                buffer.append("->执行协程")
                delay(100)
                buffer.append("->协程执行完成")
            }
            buffer.append("->启动协程")
            //是否start 不会影响结果
            if (isStart) {
                job.start()
            }
            //确保一定执行了 start()
            sleep(20)
            buffer.append("->取消协程")
            job.cancel()
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }

    private fun todoModuleLazy(
        start: CoroutineStart = CoroutineStart.LAZY,
        isStart: Boolean = false
    ) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(start = start) {
                buffer.append("->执行协程")
                delay(100)
                buffer.append("->协程执行完成")
            }
            buffer.append("->启动协程")
            //是否start 不会影响结果
            if (isStart) {
                job.start()
            }
            //确保一定执行了 start()
            sleep(20)
            buffer.append("->取消协程")
            job.cancel()
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }

    private fun todoModuleLazyCancel(
        start: CoroutineStart = CoroutineStart.LAZY
    ) {
        for (i in 1..100) {
            val buffer = StringBuffer()
            buffer.append("start")
            val job = GlobalScope.launch(start = start) {
                buffer.append("->执行协程")
                delay(100)
                buffer.append("->协程执行完成")
            }
            buffer.append("->取消协程")
            job.cancel()
            //确保一定执行了 cancel()
            sleep(20)
            buffer.append("->启动协程")
            job.start()
            buffer.append("->end")
            println("第${i}次 $buffer")
        }
    }

}