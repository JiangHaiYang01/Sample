package com.allens.sample_view.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.allens.sample_view.R

import kotlinx.android.synthetic.main.activity_measure.*

class MeasureAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure)

        help.setPoint(100,100)
    }


}