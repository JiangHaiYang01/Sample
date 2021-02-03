package com.allens.sample_view.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.allens.sample_view.R

import com.allens.sample_view.eumes.CanvasEnum
import com.allens.sample_view.utils.px


class CanvasView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var type = CanvasEnum.drawArc

    private var x = 0
    private var y = 0

    private var mPaint: Paint = Paint().apply {
        this.color = Color.RED
        this.strokeWidth = 10f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        when (type) {
            CanvasEnum.clipPath -> {
                canvas.save();
                val path =  Path().apply {
                    addOval(100f + x, 100f + x, 600f + x, 600f + y, Path.Direction.CCW)
                }

                canvas.clipPath(path)

                canvas.drawBitmap(
                    getBitmap(600),
                    100f + x,
                    100f + y,
                    mPaint
                );
                canvas.restore();
            }
            CanvasEnum.clipRect -> {
                canvas.save();
                canvas.clipRect(100f + x, 100f + y, 500f + x, 500f + y)
                canvas.drawBitmap(
                    getBitmap(600),
                    100f + x,
                    100f + y,
                    mPaint
                );
                canvas.restore();
            }
            CanvasEnum.drawColor->{
                canvas.drawColor(Color.BLUE)
            }
            CanvasEnum.drawArc -> {
                mPaint.style = Paint.Style.STROKE
                mPaint.strokeWidth = 10f
                mPaint.color = Color.BLUE
                val rectArc2 = RectF(100f + x, 100f + y, 800f + x, 800f + y)
                canvas.drawRect(rectArc2, mPaint)
                canvas.drawPoint(450f + x, 450f + y, mPaint)
                canvas.drawLine(100f + x, 800f + y, 450f + x, 450f + y, mPaint)
                canvas.drawLine(800f + x, 800f + y, 450f + x, 450f + y, mPaint)


                mPaint.color = Color.RED

                //绘制圆弧(矩形边界,开始角度,扫过角度,使用中心?边缘两点与中心连线区域：边缘两点连线区域)
                canvas.drawArc(rectArc2, 135f, 270f, false, mPaint)



                mPaint.color = Color.BLACK
                canvas.drawArc(
                    RectF(400f + x, 400f + y, 500f + x, 500f + y),
                    135f,
                    270f,
                    false,
                    mPaint
                )
                mPaint.color = Color.GREEN
                canvas.drawArc(
                    RectF(300f + x, 300f + y, 600f + x, 600f + y),
                    0f,
                    135f,
                    false,
                    mPaint
                )

                setLogPaint()
                canvas.drawText("绿色:起始的角度 135", 100f + x, 900f + y, mPaint)
                canvas.drawText("黑色:扫过的角度 270", 100f + x, 1000f + y, mPaint)


            }
        }

//        when (type) {
//            0 -> {
//                //背景色
//                canvas.drawColor(Color.parseColor("#E0F7F5"))
////                canvas.drawARGB(255, 224, 247, 245)
////                canvas.drawRGB(224, 247, 245)
//            }
//            1 -> {
//                //线
//                canvas.drawLine(100f + x, 100f + y, 200f + x, 200f + y, mPaint)
//                //绘制一组点，坐标位置由float数组指定(必须是4的倍数个)
//                canvas.drawLines(
//                    floatArrayOf(
//                        100f + x, 200f + y, 200f + x, 300f + y,
//                        100f + x, 300f + y, 200f + x, 400f + y,
//                        100f + x, 400f + y, 200f + x, 500f + y
//                    ), mPaint
//                )
//            }
//            2 -> {
//                //绘制点
//                canvas.drawPoint(100f + x, 100f + y, mPaint)
//                //绘制一组点，坐标位置由float数组指定(必须是2的倍数个)
//                canvas.drawPoints(
//                    floatArrayOf(
//                        200f + x, 200f + y,
//                        300f + x, 300f + y,
//                        400f + x, 400f + y,
//                        500f + x, 500f + y
//                    ), mPaint
//                )
//            }
//            3 -> {
//                //绘制矩阵
//                canvas.drawRect(100f + x, 100f + y, 200f + x, 200f + y, mPaint)
//                //同上
//                val rect = Rect(100 + x, 300 + y, 200 + x, 400 + y)
//                canvas.drawRect(rect, mPaint)
//
//                //圆角
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    canvas.drawRoundRect(100f + x, 500f + y, 200f + x, 600f + y, 20f, 20f, mPaint)
//                }
//            }
//            4 -> {
//                //绘制圆形
//                canvas.drawCircle(200f + x, 100f + x, 50f, mPaint)
//                //绘制椭圆
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    canvas.drawOval(100f + x, 200f + y, 200f + x, 400f + y, mPaint)
//                }
//
//                val rect = RectF(100f + x, 500f + y, 200f + x, 600f + y)
//                canvas.drawOval(rect, mPaint)
//
//
//                //为了测试方便
//                mPaint.color = Color.GRAY
//                val rect1 = RectF(100f + x, 700f + y, 300f + x, 900f + y)
//                canvas.drawOval(rect1, mPaint)
//
//                mPaint.color = Color.RED
//                val rectArc = RectF(100f + x, 700f + y, 300f + x, 900f + y)
//                //绘制圆弧(矩形边界,开始角度,扫过角度,使用中心?边缘两点与中心连线区域：边缘两点连线区域)
//                canvas.drawArc(rectArc, 0f, 90f, true, mPaint)
//
//
//                mPaint.color = Color.GRAY
//                val rect2 = RectF(400f + x, 700f + y, 600f + x, 900f + y)
//                canvas.drawOval(rect2, mPaint)
//
//                mPaint.color = Color.RED
//                val rectArc2 = RectF(400f + x, 700f + y, 600f + x, 900f + y)
//                //绘制圆弧(矩形边界,开始角度,扫过角度,使用中心?边缘两点与中心连线区域：边缘两点连线区域)
//                canvas.drawArc(rectArc2, 0f, 90f, false, mPaint)
//
//            }
//            5 -> {
//                //绘制图片
//                //定点绘制图片
//                val bitmap = BitmapFactory.decodeResource(resources,
//                    R.drawable.image
//                )
//                canvas.drawBitmap(bitmap, 100f + x, 100f + y, mPaint)
//
//
//                //图片适用矩形区域不剪裁
//                val rectF = RectF(100f + x, 700f + y, 900f + x, 1000f + y)
//                canvas.drawBitmap(bitmap, null, rectF, mPaint)
//
//
//                //图片裁剪出的矩形区域 裁剪一半
//                val rect = Rect(0, 0, 300, 400)
//                val rectF1 = RectF(100f + x, 1100f + y, 900f + x, 1400f + y)
//                canvas.drawBitmap(bitmap, rect, rectF1, mPaint)
//
//
//                //适用变换矩阵绘制图片
//                val matrix = Matrix()
//                //设置变换矩阵:缩小3倍，斜切0.5,右移150，下移100
//                matrix.setValues(
//                    floatArrayOf(
//                        1f, 0.5f, 150f,
//                        0f, 1f, 100f,
//                        0f, 0f, 3f
//                    )
//                )
//                canvas.drawBitmap(bitmap, matrix, mPaint)
//
//            }
//            6 -> {
//                //创建Picture对象
//                val picture = Picture()
//                //确定picture产生的Canvas元件的大小，并生成Canvas元件
//                val recodingCanvas = picture.beginRecording(width, height)
//                //Canvas元件的操作
//                recodingCanvas.drawRect(100f + x, 100f + y, 200f + x, 200f + y, mPaint)
//                recodingCanvas.drawRect(0f + x, 200f + y, 100f + x, 300f + y, mPaint)
//                recodingCanvas.drawRect(200f + x, 200f + y, 300f + x, 300f + y, mPaint)
//                //Canvas元件绘制结束
//                picture.endRecording()
//
//                canvas.save()
//                canvas.drawPicture(picture);//使用picture的Canvas元件
//                canvas.translate(0f, 300f)
//                picture.draw(canvas);//同上：使用picture的Canvas元件
//                canvas.drawPicture(picture)
//                canvas.translate(350f, 0f)
//                canvas.drawPicture(picture)
//                canvas.restore()
//
//            }
//            7 -> {
//                //绘制文字
//                mPaint.textSize = 60f
//                canvas.drawText("在雨季等你", 200f + x, 300f + y, mPaint)
//            }
//            8 -> {
//                //rotate
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.save();//保存canvas状态
//                //(角度,中心点x,中心点y)
//                canvas.rotate(45f, x + 100f, y + 100f)
//                mPaint.color = Color.parseColor("#880FB5FD");
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.restore();//图层向下合并
//
//            }
//            9 -> {
//                //translate
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.save();//保存canvas状态
//                canvas.translate(100f,100f)
//                mPaint.color = Color.parseColor("#880FB5FD");
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.restore();//图层向下合并
//
//            }
//            10 -> {
//                //scale
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.save();//保存canvas状态
//                canvas.scale(2f,2f)
//                mPaint.color = Color.parseColor("#880FB5FD");
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.restore();//图层向下合并
//            }
//            11->{
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.save();//保存canvas状态
//                canvas.skew(2f,0f)
//                mPaint.color = Color.parseColor("#880FB5FD");
//                canvas.drawRect(x + 100f, x + 100f, y + 300f, y + 200f, mPaint);
//                canvas.restore();//图层向下合并
//            }
//        }
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

    private fun setLogPaint() {
        mPaint.shader = null
        mPaint.pathEffect = null
        mPaint.alpha = 255
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 14f.px
        mPaint.color = Color.BLUE
        mPaint.textSize = 20f.px
    }

    fun setType(type: CanvasEnum?, x: Int, y: Int) {
        this.type = type ?: CanvasEnum.drawArc
        this.x = x
        this.y = y
        invalidate()
    }


}