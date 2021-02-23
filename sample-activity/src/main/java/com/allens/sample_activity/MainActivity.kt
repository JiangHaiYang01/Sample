package com.allens.sample_activity

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linear = findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text = "startActivityForResult"
            setOnClickListener {
                startActivityForResult(Intent(this@MainActivity, SecondAct::class.java),REQUEST_CODE)
            }
        })
    }
}