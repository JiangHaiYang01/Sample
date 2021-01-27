package com.allens.sample_view.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.allens.sample_view.utils.px
import kotlin.math.cos
import kotlin.math.sin


//开口角度
private const val OPEN_ANGLE = 120

//刻度的宽高
private val DASH_WIDTH = 2f.px
private val DASH_HEIGHT = 10f.px

//半径
private val RADIUS = 150f.px

//指针的线
private val LENGTH = 120f.px

//刻度总数
private const val SIZE = 10

//第5个刻度线
private var index = 5

private lateinit var pathMeasure: PathMeasure
private lateinit var pathDashPathEffect: PathDashPathEffect

class DashboardView(
    context: Context?,
    attrs: AttributeSet? = null
) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dash = Path()
    private val path = Path()


    init {
        //线的宽度
        paint.strokeWidth = 3f.px
        //画笔的样式，设置成
        paint.style = Paint.Style.STROKE
        //添加矩阵
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_HEIGHT, Path.Direction.CCW)
    }


    override fun onDraw(canvas: Canvas) {
        paint.pathEffect = null
        //画弧
        canvas.drawPath(path, paint)
        //设置  pathEffect
        paint.pathEffect = pathDashPathEffect
        //刻度
        canvas.drawPath(path, paint)


        paint.pathEffect = null
        //指针
        // x = 长边 * 正弦
        // y = 长边 * 余弦
        // 计算出角度  =  起始角度 + （运动角度/总数 == 每一个刻度需要的角度）
        val needRadius = (90f + OPEN_ANGLE / 2f) + ((360 - OPEN_ANGLE) / SIZE * index)
        //角度转成弧度
        val toRadians = Math.toRadians(needRadius.toDouble())
        val x = (LENGTH * cos(toRadians))
        val y = (LENGTH * sin(toRadians))
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (width / 2f + x).toFloat(),
            (height / 2f + y).toFloat(),
            paint
        )
    }

    //尺寸改变的时候触发
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        //设置path
        path.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            //起始角度
            (90f + OPEN_ANGLE / 2f),
            //运动角度
            (360 - OPEN_ANGLE).toFloat()
        )

        pathMeasure = PathMeasure(path, false)
        pathDashPathEffect = PathDashPathEffect(
            dash,
            //减去一个刻度的宽度
            (pathMeasure.length - DASH_WIDTH) / SIZE,
            0f,
            PathDashPathEffect.Style.ROTATE
        )

    }
}