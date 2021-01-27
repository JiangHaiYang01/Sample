package com.allens.sample_view.utils

import android.content.res.Resources
import android.util.TypedValue

/***
 * dp to px
 */
val Float.px: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )