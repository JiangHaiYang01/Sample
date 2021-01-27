package com.allens.sample_view.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min


class SquareImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //之前原来的测量算法
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //获取原先的测量宽高
        var width = measuredWidth
        var height = measuredHeight

        //利用原先的测量宽高 计算 自己需要的 尺寸
        if (width > height) {
            if (height > 200) {
                height = 200
            }
            width = height
        } else {
            if (width > 200) {
                width = 200
            }
            height = width
        }
        //保存计算之后的结果
        setMeasuredDimension(width, height)
    }
}