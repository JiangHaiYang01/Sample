package com.allens.sample_handler

import android.annotation.SuppressLint
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.annotation.RequiresApi
import com.allens.sample_handler.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "handler-tag"
        const val MESSAGE_TYPE_SYNC = 0x01
        const val MESSAGE_TYPE_ASYN = 0x02
    }

    private var index = 0

    private lateinit var handler : Handler

    private var token: Int? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Handler"
        initHandler()

        binding.linear.addView(MaterialButton(this).apply {
            text = "插入同步屏障"
            setOnClickListener {
                sendSyncBarrier()
            }
        })

        binding.linear.addView(MaterialButton(this).apply {
            text = "移除屏障"
            setOnClickListener {
                removeSyncBarrier()
            }
        })


        binding.linear.addView(MaterialButton(this).apply {
            text = "发送同步消息"
            setOnClickListener {
                sendSyncMessage()
            }
        })

        binding.linear.addView(MaterialButton(this).apply {
            text = "发送异步消息"
            setOnClickListener {
                sendAsynMessage()
            }
        })
    }

    private fun initHandler() {
        Thread {
            Looper.prepare()
            handler = Handler(Looper.getMainLooper()){
                when(it.what){
                    MESSAGE_TYPE_SYNC -> {
                        Log.i(TAG, "收到同步消息<========== index:${it.arg1}")
                    }
                    MESSAGE_TYPE_ASYN -> {
                        Log.i(TAG, "收到异步消息<========== index:${it.arg1}")
                    }
                }
                true
            }
            Looper.loop()
        }.start()
    }

    private fun sendSyncMessage() {
        index++
        Log.i(TAG, "插入同步消息==========> index:$index")
        val message = Message.obtain()
        message.what = MESSAGE_TYPE_SYNC
        message.arg1 = index
        handler.sendMessageDelayed(message, 1000)
    }

    //往消息队列插入异步消息
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun sendAsynMessage() {
        index++
        Log.i(TAG, "插入异步消息==========> index:$index")
        val message = Message.obtain()
        message.what = MESSAGE_TYPE_ASYN
        message.arg1 = index
        message.isAsynchronous = true
        handler.sendMessageDelayed(message, 1000)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("DiscouragedPrivateApi")
    fun sendSyncBarrier() {
        try {
            Log.d(TAG, "插入同步屏障")
            val queue: MessageQueue = handler.looper.queue
            val method: Method = MessageQueue::class.java.getDeclaredMethod("postSyncBarrier")
            method.isAccessible = true
            token = method.invoke(queue) as Int
            Log.d(TAG, "token:$token")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //移除屏障
    @SuppressLint("DiscouragedPrivateApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    fun removeSyncBarrier() {
        Log.i(TAG, "移除屏障")
        try {
            val queue: MessageQueue = handler.looper.queue
            val method: Method = MessageQueue::class.java.getDeclaredMethod(
                "removeSyncBarrier",
                Int::class.javaPrimitiveType
            )
            method.isAccessible = true
            method.invoke(queue, token)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}