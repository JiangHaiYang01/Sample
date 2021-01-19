package com.allens.sample_okhttp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_request_okhttp).setOnClickListener {
            doOkHttp()
        }
    }

    private fun doOkHttp() {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("https://www.wanandroid.com")
            .build()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                }
            })
    }
}