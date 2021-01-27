package com.allens.sample_view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.allens.sample_view.eumes.CanvasEnum
import com.allens.sample_view.R
import com.allens.sample_view.utils.createCanvasButton
import kotlinx.android.synthetic.main.activity_main.*

class CanvasItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val list = arrayListOf(
            createCanvasButton(this, CanvasEnum.drawArc,"画弧线 drawArc", CanvasAct::class.java),
            createCanvasButton(this, CanvasEnum.clipPath,"范围裁切 clipPath", CanvasAct::class.java),
            createCanvasButton(this, CanvasEnum.clipRect,"范围裁切 clipRect", CanvasAct::class.java)
        )


        for (button in list) {
            linear.addView(button)
        }
    }

}