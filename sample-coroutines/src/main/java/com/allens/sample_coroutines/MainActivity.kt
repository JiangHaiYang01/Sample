package com.allens.sample_coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("->onCreate")
        val linear = findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text = "启动模式"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ModuleAct::class.java))
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "调度器"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, DispatchersAct::class.java))
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "作用域"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, ScopeAct::class.java))
            }
        })
    }


}

class MainViewModel : ViewModel() {
    init {
        viewModelScope.launch {

        }

    }
}