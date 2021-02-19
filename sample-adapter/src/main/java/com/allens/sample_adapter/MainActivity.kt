package com.allens.sample_adapter

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.google.android.material.button.MaterialButton

/**
 * Android 版本适配
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linear = findViewById<LinearLayout>(R.id.linear)
        linear.addView(MaterialButton(this).apply {
            text = "dataDir.path"
            setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    println(dataDir?.path)
                }
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "filesDir.path"
            setOnClickListener {
                println(filesDir.path)
            }
        })
        linear.addView(MaterialButton(this).apply {
            text = "filesDir.absolutePath"
            setOnClickListener {
                println(filesDir.absolutePath)
            }
        })
     linear.addView(MaterialButton(this).apply {
            text = "filesDir.canonicalPath"
            setOnClickListener {
                println(filesDir.canonicalPath)
            }
        })

//        println(cacheDir.path)
//        println(externalCacheDir?.path)
//        println()
//        externalMediaDirs.forEach {
//
//        }
    }
}