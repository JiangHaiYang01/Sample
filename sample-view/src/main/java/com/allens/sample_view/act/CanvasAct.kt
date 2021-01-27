package com.allens.sample_view.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allens.sample_view.config.Point
import com.allens.sample_view.eumes.CanvasEnum
import com.allens.sample_view.R
import kotlinx.android.synthetic.main.activity_canvas.*
import kotlinx.android.synthetic.main.activity_canvas.help
import kotlinx.android.synthetic.main.activity_paint.*

class CanvasAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)

        title = intent.getStringExtra("title")
        val enumType = intent.extras?.getSerializable("type") as CanvasEnum
        help.setPoint(Point.CENTER_X, Point.CENTER_Y)
        canvas.setType(enumType, Point.CENTER_X, Point.CENTER_Y)
    }


}