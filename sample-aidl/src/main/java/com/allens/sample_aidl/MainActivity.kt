package com.allens.sample_aidl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.staort.sample_aidl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.linear.addView(MaterialButton(this).apply {
//            text = "绑定服务"
//            setOnClickListener {
//                println("客户端 准备绑定服务")
//                val intent = Intent()
//                intent.component =
//                    ComponentName("com.allens.intent", "com.starot.lib_intent.aidl.RemoteService")
//                bindService(intent, connect, BIND_AUTO_CREATE)
//            }
//        })
    }
}