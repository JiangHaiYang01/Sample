package com.allens.sample_thread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allens.sample_thread.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import java.util.concurrent.locks.ReentrantLock

class MainActivity : AppCompatActivity() {
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        title = "线程&线程池"

        viewBinding.linear.addView(MaterialButton(this).apply {
            text = "text"
            setOnClickListener {
                testBlocked()
            }
        })

    }

    class User  {
        var amount: Int = 100

        fun addMoney(money: Int) {
            amount += money
        }

    }

    private fun testBlocked() {
        val newCondition = ReentrantLock().newCondition()
        var user = User()
        val thread1 = Thread({
            newCondition.await()
        }, "存钱")
        thread1.start()
    }

    @Throws
    private fun testInterrupt() {
        val thread = Thread({
            println("${Thread.currentThread().name} :run...")
            Thread.sleep(1000)
            println("${Thread.currentThread().name} :end")
        }, "线程1")
        println("prepare start")
        thread.start()
//        println("prepare interrupt")
//        thread.interrupt()
        thread.join()
        println("end")
        thread.destroy()

        println("thread:${thread.state}}")

    }

    private fun testWait() {
        val thread1 = Thread({
            println("${Thread.currentThread().name} :run...")
            Thread.sleep(1000)
            println("${Thread.currentThread().name} :end")
        }, "线程1")
        thread1.start()


        val thread2 = Thread({
            println("${Thread.currentThread().name} :run...")
            println("${Thread.currentThread().name} :end")
        }, "线程2")
        thread2.start()
    }


    private fun newThread() {
        val runnable = Runnable {
            println("thread run... in ${Thread.currentThread().name}")
            Thread.sleep(1000)
            println("thread end in ${Thread.currentThread().name}")
        }
        val thread = Thread(runnable)
        println("thread prepare start in ${Thread.currentThread().name}")
        thread.start()
        thread.join()
        println("finish in ${Thread.currentThread().name}")


    }


}