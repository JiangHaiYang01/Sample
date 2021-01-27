package com.allens.sample_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.allens.sample_view.act.*
import com.allens.sample_view.act.CanvasItemActivity
import com.allens.sample_view.utils.createButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val list = arrayListOf(
            createButton(this,"Paint 画笔属性", PaintItemActivity::class.java),
            createButton(this,"Canvas", CanvasItemActivity::class.java),
            createButton(this,"实战", CustomViewItemActivity::class.java)

        )


        for (button in list) {
            linear.addView(button)
        }
    }

}