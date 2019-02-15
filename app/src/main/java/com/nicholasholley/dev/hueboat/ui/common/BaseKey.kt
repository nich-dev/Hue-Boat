package com.nicholasholley.dev.hueboat.ui.common

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment

abstract class BaseKey : Parcelable {
    val fragmentTag: String
        get() = toString()

    fun newFragment(): Fragment = createFragment().apply {
        arguments = (arguments ?: Bundle()).also { bundle ->
            bundle.putParcelable("KEY", this@BaseKey)
        }
    }

    protected abstract fun createFragment(): Fragment
}