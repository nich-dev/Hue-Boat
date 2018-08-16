package com.nicholasholley.dev.hueboat.util.ext

import android.os.Handler
import android.os.Looper
import com.nicholasholley.dev.hueboat.util.Constants

internal fun delay(delayMillis: Long = Constants.STANDARD_ANIMATION_TIME, func: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        func.invoke()
    }, delayMillis)
}