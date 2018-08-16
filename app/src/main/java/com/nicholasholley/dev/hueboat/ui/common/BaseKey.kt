package com.nicholasholley.dev.hueboat.ui.common

import android.os.Bundle
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.ui.common.BaseFragment
import com.nicholasholley.dev.hueboat.util.Constants
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
        bundle.putParcelable(Constants.NAV_KEY, this)
        fragment.arguments = bundle
        return fragment
    }

    protected abstract fun createFragment(): BaseFragment
}