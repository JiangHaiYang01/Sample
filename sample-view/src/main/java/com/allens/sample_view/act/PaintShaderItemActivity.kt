package com.allens.sample_view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.allens.sample_view.eumes.PaintEnum
import com.allens.sample_view.R
import com.allens.sample_view.utils.createPaintButton
import kotlinx.android.synthetic.main.activity_main.*

class PaintShaderItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        title = intent.getStringExtra("title")
        val list = arrayListOf(
            createPaintButton(this, PaintEnum.shader_LinearGradient,"LinearGradient 线性渐变", PaintAct::class.java),
            createPaintButton(this, PaintEnum.shader_RadialGradient,"RadialGradient 辐射渐变", PaintAct::class.java),
            createPaintButton(this, PaintEnum.shader_SweepGradient,"SweepGradient 扫描渐变", PaintAct::class.java),
            createPaintButton(this, PaintEnum.shader_BitmapShader,"BitmapShader", PaintAct::class.java)
        )


        for (button in list) {
            linear.addView(button)
        }
    }

}