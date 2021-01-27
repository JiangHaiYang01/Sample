package com.allens.sample_view.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class CustomViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {


    private fun getMaxWidth(): Int {
        val count = childCount
        var maxWidth = 0
        for (i in 0 until count) {
            val currentWidth = getChildAt(i).measuredWidth
            if (maxWidth < currentWidth) {
                maxWidth = currentWidth
            }
        }
        return maxWidth
    }

    private fun getTotalHeight(): Int {
        val count = childCount
        var totalHeight = 0
        for (i in 0 until count) {
            totalHeight += getChildAt(i).measuredHeight
        }
        return totalHeight
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            val groupWidth = getMaxWidth()
            val groupHeight = getTotalHeight()
            setMeasuredDimension(groupWidth, groupHeight)
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(getMaxWidth(), height)
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, getTotalHeight())
        }
    }

    override fun onLayout(
        changed: Boolean,
        l: Int,
        t: Int,
        r: Int,
        b: Int
    ) {
        val count = childCount
        var currentHeight = 0
        for (i in 0 until count) {
            val view: View = getChildAt(i)
            val height: Int = view.measuredHeight
            val width: Int = view.measuredWidth
            view.layout(l, currentHeight, l + width, currentHeight + height)
            currentHeight += height
        }
    }
}