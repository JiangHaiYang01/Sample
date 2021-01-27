package com.allens.sample_view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.allens.sample_view.eumes.PaintEnum
import com.allens.sample_view.R
import com.allens.sample_view.utils.createPaintButton
import kotlinx.android.synthetic.main.activity_main.*

class PaintPathEffectItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        title = intent.getStringExtra("title")
        val list = arrayListOf(
            createPaintButton(
                this,
                PaintEnum.PathEffect_CornerPathEffect,
                "CornerPathEffect 把所有拐角变成圆角",
                PaintAct::class.java
            ),
            createPaintButton(
                this,
                PaintEnum.PathEffect_DashPathEffect,
                "DashPathEffect 使用虚线来绘制线条",
                PaintAct::class.java
            ),
            createPaintButton(
                this,
                PaintEnum.PathEffect_DiscretePathEffect,
                "DiscretePathEffect 随机的偏离",
                PaintAct::class.java
            ),
            createPaintButton(
                this,
                PaintEnum.PathEffect_PathDashPathEffect,
                "PathDashPathEffect 路径点样",
                PaintAct::class.java
            )
        )


        for (button in list) {
            linear.addView(button)
        }
    }

}