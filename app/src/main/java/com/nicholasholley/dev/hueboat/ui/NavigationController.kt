package com.nicholasholley.dev.hueboat.ui

import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.transition.Fade
import androidx.transition.Slide
import com.nicholasholley.dev.hueboat.ui.common.BaseKey
import com.nicholasholley.dev.hueboat.ui.discovery.DiscoveryKey
import com.zhuinden.simplestack.StateChange
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class NavigationController @Inject constructor(
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) {
    private var job: Job? = null
    fun handleStateChange(stateChange: StateChange) {
        job?.cancel()
        val previousKey = stateChange.topPreviousState<BaseKey>()
        val newKey = stateChange.topNewState<BaseKey>()
        val fragmentTransaction = fragmentManager.beginTransaction()
        job = GlobalScope.launch {
            val fragment =
                    whatFragmentChange(previousKey, newKey).let { change ->
                        when (change) {
                            is FragmentChange.FromDiscovery -> {
                                val newFragment: Fragment = change.nextFragment ?: newKey.newFragment()
                                // EXIT
                                change.previousFragment?.exitTransition = Fade().apply { duration = TRANSITION_TIME / 2 }
                                // ENTER
                                newFragment.enterTransition = Slide(GravityCompat.END).apply { duration = TRANSITION_TIME }
                                return@let newFragment
                            }
                            is FragmentChange.ToDiscovery -> {
                                val newFragment: Fragment = change.nextFragment ?: newKey.newFragment()
                                // EXIT
                                change.previousFragment?.exitTransition = Slide(GravityCompat.END).apply { duration = TRANSITION_TIME }
                                return@let newFragment
                            }
                            is FragmentChange.Other -> {
                                val newFragment: Fragment = change.nextFragment ?: newKey.newFragment()
                                newFragment.enterTransition = Slide(GravityCompat.END)
                                change.previousFragment?.exitTransition = Slide(GravityCompat.START)
                                return@let newFragment
                            }
                        }
                    }
            fragmentTransaction.replace(containerId, fragment, newKey.fragmentTag)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    private fun whatFragmentChange(previous: BaseKey?, next: BaseKey): FragmentChange =
            when {
                next is DiscoveryKey -> FragmentChange.ToDiscovery(
                        fragmentManager.findFragmentByTag(previous?.fragmentTag),
                        fragmentManager.findFragmentByTag(next.fragmentTag)
                )
                previous is DiscoveryKey -> FragmentChange.FromDiscovery(
                        fragmentManager.findFragmentByTag(previous.fragmentTag),
                        fragmentManager.findFragmentByTag(next.fragmentTag)
                )
                else -> FragmentChange.Other(
                        if (previous != null) fragmentManager.findFragmentByTag(previous.fragmentTag) else null,
                        fragmentManager.findFragmentByTag(next.fragmentTag)
                )
            }

    private sealed class FragmentChange {
        data class FromDiscovery(val previousFragment: Fragment?, val nextFragment: Fragment?) : FragmentChange()
        data class ToDiscovery(val previousFragment: Fragment?, val nextFragment: Fragment?) : FragmentChange()
        data class Other(val previousFragment: Fragment?, val nextFragment: Fragment?) : FragmentChange()
    }

    companion object {
        private const val TRANSITION_TIME = 250L
    }
}