package com.allens.sample_view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.allens.sample_view.eumes.CustomViewEnum
import com.allens.sample_view.R
import com.allens.sample_view.utils.createCustomButton
import kotlinx.android.synthetic.main.activity_main.*

class CustomViewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val list = arrayListOf(
            createCustomButton(this, CustomViewEnum.DashboardView,"仪表盘", CustomViewAct::class.java),
            createCustomButton(this, CustomViewEnum.PieView,"饼图", CustomViewAct::class.java)
        )


        for (button in list) {
            linear.addView(button)
        }
    }

}