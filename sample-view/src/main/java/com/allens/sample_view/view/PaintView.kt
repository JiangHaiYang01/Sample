package com.allens.sample_view.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.allens.sample_view.R

import com.allens.sample_view.eumes.PaintEnum
import com.allens.sample_view.utils.px
import kotlin.math.cos
import kotlin.math.sin


private val textSize = 20f.px

class PaintView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0

) : View(context, attrs, defStyleAttr) {


    private var type: PaintEnum = PaintEnum.breakText

    private var x = 0
    private var y = 0

    private var mPaint: Paint = Paint().apply {
        this.color = Color.RED
        this.strokeWidth = 10f
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        when (type) {
            PaintEnum.breakText -> {
                val imageSize = 500f
                val imagePadding = 100f + y
                canvas.drawBitmap(
                    getBitmap(imageSize.toInt()),
                    width - imageSize,
                    imagePadding,
                    mPaint
                )

                val text =
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."


                val fontMeasure = Paint.FontMetrics()

                mPaint.getFontMetrics(fontMeasure)
                var start = 0
                var count = 0
                var verticalOffset = -fontMeasure.top

                var maxWidth: Float

                val measuredWidth = floatArrayOf(0f)

                mPaint.textSize = 22f.px
                while (start < text.length) {

                    maxWidth = if (verticalOffset + fontMeasure.bottom < imagePadding
                        || verticalOffset + fontMeasure.top > imagePadding + imageSize
                    ) {
                        width.toFloat()
                    } else {
                        width - imageSize
                    }
                    count = mPaint.breakText(
                        text,
                        start,
                        text.length,
                        true,
                        maxWidth,
                        measuredWidth
                    )
                    canvas.drawText(text, start, start + count, 0f, verticalOffset, mPaint)
                    start += count
                    verticalOffset += mPaint.fontSpacing
                }


            }
            PaintEnum.staticLayout -> {
                val text =
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                val staticLayout = StaticLayout(
                    text,
                    TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
                        textSize = 16f.px
                    },
                    width,
                    Layout.Alignment.ALIGN_NORMAL,
                    1f,
                    0f,
                    false
                )
                staticLayout.draw(canvas)
            }
            PaintEnum.xfermode_src_OVER -> {
                setXfermode(canvas, PorterDuff.Mode.SRC_OVER)
            }
            PaintEnum.xfermode_SRC_ATOP -> {
                setXfermode(canvas, PorterDuff.Mode.SRC_ATOP)
            }
            PaintEnum.xfermode_MULTIPLY -> {
                setXfermode(canvas, PorterDuff.Mode.MULTIPLY)
            }
            PaintEnum.xfermode_DARKEN -> {
                setXfermode(canvas, PorterDuff.Mode.DARKEN)
            }
            PaintEnum.xfermode_CLEAR -> {
                setXfermode(canvas, PorterDuff.Mode.CLEAR)
            }
            PaintEnum.xfermode_add -> {
                setXfermode(canvas, PorterDuff.Mode.ADD)
            }
            PaintEnum.xfermode_src -> {
                setXfermode(canvas, PorterDuff.Mode.SRC)
            }
            PaintEnum.xfermode_src_in -> {
                setXfermode(canvas, PorterDuff.Mode.SRC_IN)
            }
            PaintEnum.PathEffect_PathDashPathEffect -> {
                canvas.save()
                mPaint.style = Paint.Style.STROKE
                val path = Path()
                path.moveTo(100f + x, 100f + y)
                path.lineTo(300f + x, 300f + y)
                path.lineTo(500f + x, 100f + y)
                path.lineTo(700f + x, 300f + y)
                canvas.drawPath(path, mPaint)

                canvas.translate(0f, 300f)


                //通过旋转[路径点样]来过渡转角
                mPaint.pathEffect = PathDashPathEffect(
                    nStarPath(5, 16f, 8f),
                    40f,
                    10f,
                    PathDashPathEffect.Style.ROTATE
                )
                canvas.drawPath(path, mPaint)
                canvas.restore()

                setLogPaint()
                canvas.drawText("没有设置的效果", 100f + x, 350f + y, mPaint)
                canvas.drawText(
                    "设置 PathDashPathEffect 的效果",
                    100f + x,
                    700f + y,
                    mPaint
                )
            }
            PaintEnum.PathEffect_DashPathEffect -> {
                canvas.save()
                mPaint.style = Paint.Style.STROKE
                val path = Path()
                path.moveTo(100f + x, 100f + y)
                path.lineTo(300f + x, 300f + y)
                path.lineTo(500f + x, 100f + y)
                path.lineTo(700f + x, 300f + y)
                canvas.drawPath(path, mPaint)

                canvas.translate(0f, 300f)

                //第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），
                // 按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
                // 例如上面代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
                // 第二个参数 phase 是虚线的偏移量。
                mPaint.pathEffect = DashPathEffect(floatArrayOf(25f, 25f), 0f)
                canvas.drawPath(path, mPaint)
                canvas.restore()

                setLogPaint()
                canvas.drawText("没有设置的效果", 100f + x, 350f + y, mPaint)
                canvas.drawText(
                    "设置 DashPathEffect 的效果",
                    100f + x,
                    700f + y,
                    mPaint
                )
            }
            PaintEnum.PathEffect_DiscretePathEffect -> {
                canvas.save()
                mPaint.style = Paint.Style.STROKE
                val path = Path()
                path.moveTo(100f + x, 100f + y)
                path.lineTo(300f + x, 300f + y)
                path.lineTo(500f + x, 100f + y)
                path.lineTo(700f + x, 300f + y)
                canvas.drawPath(path, mPaint)

                canvas.translate(0f, 300f)

                //segmentLength 是用来拼接的每个线段的长度，
                // deviation 是偏离量
                mPaint.pathEffect = DiscretePathEffect(20f, 5f)
                canvas.drawPath(path, mPaint)
                canvas.restore()

                setLogPaint()
                canvas.drawText("没有设置的效果", 100f + x, 400f + y, mPaint)
                canvas.drawText("设置 DiscretePathEffect(20f,5f) 的效果", 100f + x, 700f + y, mPaint)
            }
            PaintEnum.PathEffect_CornerPathEffect -> {

                canvas.save()
                mPaint.style = Paint.Style.STROKE
                val path = Path()
                path.moveTo(100f + x, 100f + y)
                path.lineTo(300f + x, 300f + y)
                path.lineTo(500f + x, 100f + y)
                path.lineTo(700f + x, 300f + y)
                canvas.drawPath(path, mPaint)

                canvas.translate(0f, 300f)

                mPaint.pathEffect = CornerPathEffect(100f)//是圆角的半径
                canvas.drawPath(path, mPaint)
                canvas.restore()

                setLogPaint()
                canvas.drawText("没有设置的效果", 100f + x, 400f + y, mPaint)
                canvas.drawText("设置 CornerPathEffect(100f) 的效果", 100f + x, 700f + y, mPaint)
            }
            PaintEnum.shader_RadialGradient -> {
                canvas.save()
                mPaint.shader = createRadialGradient(Shader.TileMode.CLAMP)
                canvas.drawCircle(300f + x, 300f + y, 100f, mPaint)

                canvas.translate(0f, 400f)
                mPaint.shader = createRadialGradient(Shader.TileMode.REPEAT)
                canvas.drawCircle(300f + x, 300f + y, 100f, mPaint)

                canvas.translate(0f, 400f)
                mPaint.shader = createRadialGradient(Shader.TileMode.MIRROR)
                canvas.drawCircle(300f + x, 300f + y, 100f, mPaint)
                canvas.restore()


                setLogPaint()
                canvas.drawText("Shader.TileMode.CLAMP", 100f + x, 400f + y, mPaint)
                canvas.drawText("Shader.TileMode.REPEAT", 100f + x, 800f + y, mPaint)
                canvas.drawText("Shader.TileMode.MIRROR", 100f + x, 1200f + y, mPaint)

            }
            PaintEnum.shader_BitmapShader -> {
                canvas.save()
                mPaint.shader = createBitmapShader(Shader.TileMode.CLAMP)
                canvas.drawCircle(300f + x, 300f + y, 100f, mPaint)


                canvas.translate(0f, 400f)
                mPaint.textSize = 75f.px
                mPaint.strokeWidth = 75f
                mPaint.style = Paint.Style.FILL
                mPaint.shader = createBitmapShader(Shader.TileMode.REPEAT)
                canvas.drawText("在雨季等你", 100f + x, 400f + y, mPaint)

                canvas.restore()

                setLogPaint()
                canvas.drawText("BitmapShader + drawCircle", 100f + x, 500f + y, mPaint)
                canvas.drawText("BitmapShader + drawText", 100f + x, 900f + y, mPaint)

            }
            PaintEnum.shader_SweepGradient -> {
                canvas.save()
                mPaint.shader = createSweepGradient()
                canvas.drawCircle(300f + x, 300f + y, 100f, mPaint)
                canvas.restore()
            }
            PaintEnum.shader_LinearGradient -> {
                canvas.save()
                mPaint.strokeWidth = 50f
                mPaint.shader = createLinearGradient(Shader.TileMode.CLAMP)
                canvas.drawLine(100f + x, 100f + y, 700f + x, 100f + y, mPaint)

                canvas.translate(0f, 200f)
                mPaint.shader = createLinearGradient(Shader.TileMode.REPEAT)
                canvas.drawLine(100f + x, 100f + y, 700f + x, 100f + y, mPaint)

                canvas.translate(0f, 200f)
                mPaint.shader = createLinearGradient(Shader.TileMode.MIRROR)
                canvas.drawLine(100f + x, 100f + y, 700f + x, 100f + y, mPaint)

                canvas.restore()

                setLogPaint()
                canvas.drawText("Shader.TileMode.CLAMP", 100f + x, 200f + y, mPaint)
                canvas.drawText("Shader.TileMode.REPEAT", 100f + x, 400f + y, mPaint)
                canvas.drawText("Shader.TileMode.MIRROR", 100f + x, 600f + y, mPaint)


            }

            PaintEnum.COLOR -> {
                canvas.save()
                //颜色
                mPaint.color = Color.RED
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)

                canvas.translate(0f, 200f)

                mPaint.setARGB(255, 255, 0, 0)
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)

                canvas.restore()
                setLogPaint()
                canvas.drawText("mPaint.color = Color.RED", 100f + x, 200f + y, mPaint)
                canvas.drawText("mPaint.setARGB(255, 255, 0, 0)", 100f + x, 400f + y, mPaint)
            }
            PaintEnum.alpha -> {
                //透明度
                mPaint.alpha = 100
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)



                setLogPaint()
                canvas.drawText("mPaint.alpha = 100", 100f + x, 200f + y, mPaint)

            }
            PaintEnum.strokeWidth -> {
                //宽度
                mPaint.strokeWidth = 40f
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)



                setLogPaint()
                canvas.drawText("mPaint.strokeWidth = 20f", 100f + x, 200f + y, mPaint)

            }

            PaintEnum.style -> {

                val rect = Rect(100 + x, 100 + y, 300 + x, 300 + y)
                mPaint.strokeWidth = 50f
                canvas.save()

                //填充
                mPaint.style = Paint.Style.FILL
                canvas.drawRect(rect, mPaint)

                //空心
                mPaint.style = Paint.Style.STROKE
                canvas.translate(0f, 400f)
                canvas.drawRect(rect, mPaint)


                mPaint.style = Paint.Style.FILL_AND_STROKE
                canvas.translate(0f, 400f)
                canvas.drawRect(rect, mPaint)

                canvas.restore()


                setLogPaint()
                canvas.drawText("mPaint.style = Paint.Style.FILL", 100f + x, 350f + y, mPaint)
                canvas.drawText("mPaint.style = Paint.Style.STROKE", 100f + x, 800f + y, mPaint)
                canvas.drawText(
                    "mPaint.style = Paint.Style.FILL_AND_STROKE",
                    100f + x,
                    1200f + y,
                    mPaint
                )

            }
            PaintEnum.isAntiAlias -> {
                //设置抗锯齿
                mPaint.isAntiAlias = true
                mPaint.strokeWidth = 100f
                mPaint.style = Paint.Style.STROKE


                mPaint.color = Color.RED
                canvas.drawCircle(400f + x, 400f + y, width / 4f, mPaint)

                mPaint.isAntiAlias = false
                mPaint.color = Color.BLUE
                canvas.drawCircle(400f + x, 800f + y, width / 4f, mPaint)




                setLogPaint()
                canvas.drawText("红色:isAntiAlias = true", 100f + x, 1200f + y, mPaint)
                canvas.drawText("蓝色:isAntiAlias = false", 100f + x, 1300f + y, mPaint)
            }

            PaintEnum.strokeCap -> {
                canvas.save()
                //笔头的样式
                mPaint.strokeWidth = 50f

                mPaint.strokeCap = Paint.Cap.ROUND
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)

                canvas.translate(0f, 200f)
                mPaint.strokeCap = Paint.Cap.SQUARE
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)

                canvas.translate(0f, 200f)
                mPaint.strokeCap = Paint.Cap.BUTT
                canvas.drawLine(100f + x, 100f + y, 400f + x, 100f + y, mPaint)

                canvas.restore()


                setLogPaint()
                canvas.drawText("mPaint.strokeCap = Paint.Cap.ROUND", 100f + x, 200f + y, mPaint)
                canvas.drawText("mPaint.strokeCap = Paint.Cap.SQUARE", 100f + x, 400f + y, mPaint)
                canvas.drawText("mPaint.strokeCap = Paint.Cap.BUTT", 100f + x, 600f + y, mPaint)
            }

            PaintEnum.strokeJoin -> {

                canvas.save()
                //夹角的样式
                //只有路径绘制的线才有交角
                mPaint.strokeWidth = 50f
                mPaint.style = Paint.Style.STROKE


                val path = Path()
                path.moveTo(300f + x, 100f + y)
                path.lineTo(100f + x, 300f + y)
                path.lineTo(300f + x, 300f + y)


                mPaint.strokeJoin = Paint.Join.BEVEL//直线(默认)
                canvas.drawPath(path, mPaint)


                canvas.translate(0f, 400f)
                mPaint.strokeJoin = Paint.Join.ROUND//圆角
                canvas.drawPath(path, mPaint)


                canvas.translate(0f, 400f)
                mPaint.strokeJoin = Paint.Join.MITER//锐角
                canvas.drawPath(path, mPaint)
                canvas.restore()

                setLogPaint()
                canvas.drawText("mPaint.strokeJoin = Paint.Join.BEVEL", 100f + x, 400f + y, mPaint)
                canvas.drawText("mPaint.strokeJoin = Paint.Join.ROUND", 100f + x, 800f + y, mPaint)
                canvas.drawText("mPaint.strokeCap = Paint.Cap.MITER", 100f + x, 1200f + y, mPaint)

            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setXfermode(canvas: Canvas, model: PorterDuff.Mode) {
        canvas.save()
        drawRect(canvas, model, false)
        canvas.translate(0f, 700f)
        drawRect(canvas, model, true)
        canvas.restore()
        setLogPaint()
        canvas.drawText(
            model.toString(),
            100f + x,
            1300f + y,
            mPaint
        )
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun drawRect(canvas: Canvas, model: PorterDuff.Mode, layer: Boolean) {

        var count = 0
        if (layer) {
            val bounds = RectF(100f + x, 100f + y, +500f + x, 500f + y)
            count = canvas.saveLayer(bounds, null)
        }
        val circleBitmap = Bitmap.createBitmap(
            (400f).toInt(),
            (400f).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val circleCanvas = Canvas(circleBitmap)
//        circleCanvas.drawColor(Color.YELLOW)
        mPaint.color = Color.RED
//        circleCanvas.setBitmap(circleBitmap)
        circleCanvas.drawOval(100f, 0f, 400f, 300f, mPaint)
        canvas.drawBitmap(circleBitmap, 100f + x, 100f + y, mPaint)

        if (layer) {
            mPaint.xfermode = PorterDuffXfermode(model)
        }


        val squareBitmap = Bitmap.createBitmap(
            (400f).toInt(),
            (400f).toInt(),
            Bitmap.Config.ARGB_8888
        )
        val squareCanvas = Canvas(squareBitmap)
//        squareCanvas.drawColor(Color.GREEN)
        mPaint.color = Color.BLUE
//        squareCanvas.setBitmap(squareBitmap)
        squareCanvas.drawRect(0f, 150f, 250f, 400f, mPaint)
        canvas.drawBitmap(squareBitmap, 100f + x, 100f + y, mPaint)

        if (layer) {
            mPaint.xfermode = null
            canvas.restoreToCount(count)
        }
    }

    private fun createLinearGradient(tile: Shader.TileMode = Shader.TileMode.CLAMP): Shader {
        return LinearGradient(
            //渐变的两个端点的位置
            100f + x, 100f + y, 350f + x, 100f + y,
            //端点的颜色
            Color.RED,
            Color.BLUE,
            //着色规则
            tile
        )
    }

    private fun createRadialGradient(tile: Shader.TileMode = Shader.TileMode.CLAMP): Shader {
        return RadialGradient(
            //辐射中心的坐标
            300f + x, 300f + y,
            50f,
            //端点的颜色
            Color.RED,
            Color.BLUE,
            //着色规则
            tile
        )
    }

    private fun createSweepGradient(): Shader {
        return SweepGradient(
            //扫描的中心
            300f + x, 300f + y,
            //扫描的起始颜色
            Color.RED,
            //扫描的终止颜色
            Color.BLUE
        )
    }

    private fun createBitmapShader(tile: Shader.TileMode): Shader {
        return BitmapShader(
            BitmapFactory.decodeResource(resources, R.drawable.image),
            tile,
            tile
        )
    }

    private fun setLogPaint() {
        mPaint.shader = null
        mPaint.pathEffect = null
        mPaint.alpha = 255
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 14f.px
        mPaint.color = Color.BLUE
        mPaint.textSize = textSize
    }


    /**
     * n角星路径
     *
     * @param num 几角星
     * @param R   外接圆半径
     * @param r   内接圆半径
     * @return n角星路径
     */
    fun nStarPath(num: Int, R: Float, r: Float): Path? {
        val path = Path()
        val perDeg = 360 / num.toFloat()
        val degA = perDeg / 2 / 2
        val degB = 360 / (num - 1) / 2 - degA / 2 + degA
        path.moveTo(
            (cos(rad(degA + perDeg * 0).toDouble()) * R + R * Math.cos(
                rad(
                    degA
                ).toDouble()
            )).toFloat(),
            (-Math.sin(rad(degA + perDeg * 0).toDouble()) * R + R).toFloat()
        )
        for (i in 0 until num) {
            path.lineTo(
                (cos(rad(degA + perDeg * i).toDouble()) * R + R * Math.cos(
                    rad(degA).toDouble()
                )).toFloat(),
                (-sin(rad(degA + perDeg * i).toDouble()) * R + R).toFloat()
            )
            path.lineTo(
                (cos(rad(degB + perDeg * i).toDouble()) * r + R * Math.cos(
                    rad(degA).toDouble()
                )).toFloat(),
                (-sin(rad(degB + perDeg * i).toDouble()) * r + R).toFloat()
            )
        }
        path.close()
        return path
    }

    /**
     * 角度制化为弧度制
     *
     * @param deg 角度
     * @return 弧度
     */
    private fun rad(deg: Float): Float {
        return (deg * Math.PI / 180).toFloat()
    }

    fun setType(type: PaintEnum?, x: Int, y: Int) {
        this.type = type ?: PaintEnum.xfermode_src_in
        this.x = x
        this.y = y
        invalidate()
    }


    private fun getBitmap(width: Int): Bitmap {

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.image, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.image, options)
    }


}