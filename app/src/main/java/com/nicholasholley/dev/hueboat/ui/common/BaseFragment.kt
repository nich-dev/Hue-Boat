package com.nicholasholley.dev.hueboat.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import com.nicholasholley.dev.hueboat.R

abstract class BaseFragment : Fragment() {
    fun <T : BaseKey> getKey(): T? = arguments?.getParcelable<T>(resources.getString(R.string.navigation_key))
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeToObservables()
        logContentView(savedInstanceState)
    }
    abstract fun logContentView(savedInstanceState: Bundle? = null)
    abstract fun subscribeToObservables()
}