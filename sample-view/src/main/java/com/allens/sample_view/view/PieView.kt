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


//半径
private val RADIUS = 150f.px

private var startAngle = 0f

private const val OFFSET_LENGTH = 50f

private val OFFSET_INDEX = 1

private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f)
private val COLORS = listOf(Color.RED, Color.BLACK, Color.YELLOW, Color.BLUE)


class PieView(
    context: Context?,
    attrs: AttributeSet? = null
) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {


        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == OFFSET_INDEX) {
                canvas.save()
                canvas.translate(
                    OFFSET_LENGTH * cos(Math.toRadians((startAngle + angle / 2f).toDouble())).toFloat(),
                    OFFSET_LENGTH * sin(Math.toRadians((startAngle + angle / 2f).toDouble())).toFloat()
                )
            }
            //画弧
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                //起始角度
                (startAngle),
                //运动角度
                (angle),
                true,
                paint
            )
            startAngle += angle

            if (index == OFFSET_INDEX) {
                canvas.restore()
            }
        }


    }

    //尺寸改变的时候触发
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {


    }
}