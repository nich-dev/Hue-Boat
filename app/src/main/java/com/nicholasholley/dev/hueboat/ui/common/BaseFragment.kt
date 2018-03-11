package com.nicholasholley.dev.hueboat.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.util.Constants

abstract class BaseFragment : Fragment() {
    fun <T : BaseKey> getKey(): T? = arguments?.getParcelable<T>(Constants.NAV_KEY)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindToVM()
    }
    abstract fun bindToVM()
}