package com.allens.sample_view.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.allens.sample_view.eumes.CanvasEnum
import com.allens.sample_view.eumes.CustomViewEnum
import com.allens.sample_view.eumes.PaintEnum


fun <T> createButton(context: Context, title: String, clazz: Class<T>): Button {
    return Button(context)
        .apply {
            text = title
            isAllCaps = false
            setOnClickListener {
                val intent = Intent(it?.context, clazz)
                intent.putExtra("title", title)
                context.startActivity(intent)
            }
        }
}

fun <T> createCanvasButton(
    context: Context,
    type: CanvasEnum,
    title: String,
    clazz: Class<T>
): Button {
    return Button(context)
        .apply {
            text = title
            isAllCaps = false
            setOnClickListener {
                val intent = Intent(it?.context, clazz)
                intent.putExtra("title", title)
                val bundle = Bundle()
                bundle.putSerializable("type", type)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
}


fun <T> createPaintButton(
    context: Context,
    type: PaintEnum,
    title: String,
    clazz: Class<T>,
    isGonePath: Boolean = false
): Button {
    return Button(context)
        .apply {
            text = title
            isAllCaps = false
            setOnClickListener {
                val intent = Intent(it?.context, clazz)
                intent.putExtra("title", title)
                intent.putExtra("isGonePath", isGonePath)
                val bundle = Bundle()
                bundle.putSerializable("type", type)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
}

fun <T> createCustomButton(
    context: Context,
    type: CustomViewEnum,
    title: String,
    clazz: Class<T>
): Button {
    return Button(context)
        .apply {
            text = title
            isAllCaps = false
            setOnClickListener {
                val intent = Intent(it?.context, clazz)
                intent.putExtra("title", title)
                val bundle = Bundle()
                bundle.putSerializable("type", type)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
}

