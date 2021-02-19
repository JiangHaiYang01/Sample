package com.allens.sample_proxy

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

abstract class BaseActivity : AppCompatActivity() {
    lateinit var linear: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linear = findViewById(R.id.linear)
        doCreate()
    }

    abstract fun doCreate()

    fun addButton(info: String, block: () -> Unit) {
        linear.addView(MaterialButton(this).apply {
            text = info
            setOnClickListener {
                block()
            }
        })
    }

    inline fun <reified T : AppCompatActivity> intentAct(info: String) {
        linear.addView(MaterialButton(this).apply {
            text = info
            setOnClickListener {
                startActivity(Intent(this@BaseActivity, T::class.java))
            }
        })
    }
}