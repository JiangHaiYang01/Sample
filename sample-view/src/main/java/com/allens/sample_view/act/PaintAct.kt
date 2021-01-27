package com.allens.sample_view.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.allens.sample_view.config.Point
import com.allens.sample_view.eumes.PaintEnum
import com.allens.sample_view.R
import kotlinx.android.synthetic.main.activity_paint.*

class PaintAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        title = intent.getStringExtra("title")
        help.isGone = intent.getBooleanExtra("isGonePath", false)

        val enumType = intent.extras?.getSerializable("type") as PaintEnum
        help.setPoint(Point.CENTER_X, Point.CENTER_Y)
        paint.setType(enumType, Point.CENTER_X, Point.CENTER_Y)
    }


}