package com.nicholasholley.dev.hueboat.ui

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.nicholasholley.dev.hueboat.ui.common.BaseKey
import com.nicholasholley.dev.hueboat.R
import com.zhuinden.simplestack.StateChange
import javax.inject.Inject

class NavigationController @Inject constructor(
        val fm: FragmentManager
) {
    val fragmentContainerId: Int by lazy {
        R.id.frame
    }

    @SuppressLint("CommitTransaction") // lint does not detect it in the apply block.
    fun handleStateChange(stateChange: StateChange) {
        fm.beginTransaction().apply {
            val previousState = stateChange.getPreviousState<BaseKey>()
            val newState = stateChange.getNewState<BaseKey>()
            for (oldKey in previousState) {
                val fragment = fm.findFragmentByTag(oldKey.fragmentTag)
                if (fragment != null) {
                    if (!newState.contains(oldKey)) {
                        remove(fragment)
                    } else if (!fragment.isDetached) {
                        detach(fragment)
                    }
                }
            }
            for (newKey in newState) {
                var fragment: Fragment? = fm.findFragmentByTag(newKey.fragmentTag)
                if (newKey == stateChange.topNewState<Any>()) {
                    when (stateChange.direction) {
                        StateChange.FORWARD -> {
                            setCustomAnimations(
                                    R.anim.slide_in_from_bottom,
                                    R.anim.slide_out_to_top_light,
                                    R.anim.slide_in_from_bottom,
                                    R.anim.slide_out_to_top_light
                            )
                        }
                        StateChange.BACKWARD -> {
                            setCustomAnimations(
                                    R.anim.slide_in_from_bottom,
                                    R.anim.slide_out_to_top_light,
                                    R.anim.slide_in_from_bottom,
                                    R.anim.slide_out_to_top_light
                            )
                        }
                    }
                    if (fragment != null) {
                        if (fragment.isDetached) {
                            attach(fragment)
                        }
                    } else {
                        fragment = newKey.newFragment()
                        add(fragmentContainerId, fragment, newKey.fragmentTag)
                    }
                } else {
                    if (fragment != null && !fragment.isDetached) {
                        detach(fragment)
                    }
                }
            }
            commitNow()
        }
    }
}