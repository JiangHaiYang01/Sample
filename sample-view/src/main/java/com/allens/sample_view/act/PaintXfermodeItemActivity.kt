package com.allens.sample_view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.allens.sample_view.eumes.PaintEnum
import com.allens.sample_view.R
import com.allens.sample_view.utils.createPaintButton
import kotlinx.android.synthetic.main.activity_main.*

class PaintXfermodeItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        title = intent.getStringExtra("title")
        val list = arrayListOf(
            createPaintButton(this, PaintEnum.xfermode_src,"src", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode_src_OVER,"SRC_OVER", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode_src_in,"SRC_IN", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode_SRC_ATOP,"SRC_ATOP", PaintAct::class.java),

            createPaintButton(this, PaintEnum.xfermode_CLEAR,"Clear", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode_DARKEN,"DARKEN", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode_MULTIPLY,"MULTIPLY", PaintAct::class.java),
            createPaintButton(this, PaintEnum.xfermode_src_in,"src in", PaintAct::class.java)
        )


        for (button in list) {
            linear.addView(button)
        }
    }

}