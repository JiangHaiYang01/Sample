package com.allens.sample_view.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.allens.sample_view.R


class MeasureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    var color: Int

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.MeasureView)
        color = ta.getColor(R.styleable.MeasureView_custom_color, Color.RED)
        ta.recycle()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        canvas.drawColor(color)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //①没必要在调用  super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //widthMeasureSpec  父类View 穿过来的宽度限制
        //heightMeasureSpec 父类View 穿过来的高度限制

        //②自己确认需要的宽高
        var measureWidth = 300
        var measureHeight = 300

        //③将 自己计算得到的 和 父类返回的view 通过 resolveSize 传入
        //返回的参数 就是 符合限制条件的 宽高尺寸
        measureWidth = resolveSize(measureWidth, widthMeasureSpec)
        measureHeight = resolveSize(measureHeight, heightMeasureSpec)

        //④将修正过的尺寸  通过 setMeasuredDimension 保存
        setMeasuredDimension(measureWidth, measureHeight)
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

    }
}