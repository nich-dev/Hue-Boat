package com.nicholasholley.dev.hueboat.ui.common

import android.os.Bundle
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.ui.common.BaseFragment
import paperparcel.PaperParcelable

abstract class BaseKey: PaperParcelable {
    val fragmentTag: String
        get() = toString()

    fun newFragment(): BaseFragment {
        val fragment = createFragment()
        var bundle: Bundle? = fragment.arguments
        if (bundle == null) {
            bundle = Bundle()
        }
        bundle.putParcelable(fragment.resources.getString(R.string.navigation_key), this)
        fragment.arguments = bundle
        return fragment
    }

    protected abstract fun createFragment(): BaseFragment
}