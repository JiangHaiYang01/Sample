package com.allens.sample_view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.allens.sample_view.eumes.PaintEnum
import com.allens.sample_view.R
import com.allens.sample_view.utils.createPaintButton
import kotlinx.android.synthetic.main.activity_main.*

class PaintItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        title = intent.getStringExtra("title")

        val list = arrayListOf(
            createPaintButton(this, PaintEnum.COLOR,"设置颜色", PaintAct::class.java),
            createPaintButton(this, PaintEnum.shader,"着色器", PaintShaderItemActivity::class.java),
            createPaintButton(this, PaintEnum.PathEffect,"PathEffect", PaintPathEffectItemActivity::class.java),
            createPaintButton(this, PaintEnum.alpha,"设置透明度 alpha", PaintAct::class.java),
            createPaintButton(this, PaintEnum.strokeWidth,"设置宽度 strokeWidth", PaintAct::class.java),
            createPaintButton(this, PaintEnum.isAntiAlias,"设置抗锯齿 isAntiAlias", PaintAct::class.java),
            createPaintButton(this, PaintEnum.style,"线的样式 style", PaintAct::class.java),
            createPaintButton(this, PaintEnum.strokeJoin,"线交角 strokeJoin", PaintAct::class.java),
            createPaintButton(this, PaintEnum.staticLayout,"staticLayout", PaintAct::class.java,true),
            createPaintButton(this, PaintEnum.breakText,"breakText", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode,"Xfermode", PaintXfermodeItemActivity::class.java),
            createPaintButton(this, PaintEnum.strokeCap,"笔头的线帽样式 strokeCap", PaintAct::class.java)
        )


        for (button in list) {
            linear.addView(button)
        }
    }

}