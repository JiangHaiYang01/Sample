package com.allens.sample_view.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.allens.sample_view.eumes.CustomViewEnum
import com.allens.sample_view.view.DashboardView
import com.allens.sample_view.view.PieView
import com.allens.sample_view.R
import kotlinx.android.synthetic.main.activity_test.*

class CustomViewAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = intent.getStringExtra("title")

        when (intent.extras?.getSerializable("type") as CustomViewEnum) {
            CustomViewEnum.DashboardView -> {
                linear.addView(DashboardView(this))
            }
            CustomViewEnum.PieView -> {
                linear.addView(PieView(this))
            }
        }

    }


}