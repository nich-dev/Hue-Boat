package com.nicholasholley.dev.hueboat.ui.discovery

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import com.github.florent37.kotlin.pleaseanimate.please
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.di.MarkForInjection
import com.nicholasholley.dev.hueboat.ui.common.BaseFragment
import com.nicholasholley.dev.hueboat.util.Constants
import com.nicholasholley.dev.hueboat.util.ext.observe
import com.nicholasholley.dev.hueboatsdk.util.d
import javax.inject.Inject

/**
 * A fragment that shows discovery Bridge options
 */

class DiscoveryFragment: BaseFragment(), MarkForInjection {
    //injection
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    val vm: DiscoveryVM by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(
                DiscoveryVM::class.java
        )
    }

    lateinit var uPnPAdapter: UPnPDeviceAdapter
    lateinit var list: RecyclerView
    lateinit var spinnyBoi: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(R.layout.fragment_discovery, container, false)
        uPnPAdapter = UPnPDeviceAdapter(context!!)
        list = parent.findViewById<RecyclerView>(R.id.list).apply {
            adapter = uPnPAdapter
            layoutManager = LinearLayoutManager(context)
        }
        spinnyBoi = parent.findViewById<ProgressBar>(R.id.spinner)
        return parent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindToVM()
    }

    override fun bindToVM() {
        vm.uPnPDevices.observe(this) {
            it?.let { uPnPDataList ->
                if (uPnPDataList.size > 0) {
                    vm.fragmentState.value = State.SELECT
                    uPnPDataList.last().let { uPnPAdapter.add(it) }
                } else {
                    vm.fragmentState.value = State.SEARCH
                }
            }
        }
        vm.fragmentState.observe(this) {
            when (it) {
                State.SELECT -> animateSelect()
                else -> animateInitial()
            }
        }
    }

    private fun animateSelect() {
        please(duration = Constants.STANDARD_ANIMATION_TIME) {
            animate(spinnyBoi) toBe {
                alpha(0f)
                scale(0.5f, 0.5f)
            }
        }.thenCouldYou(duration = Constants.STANDARD_ANIMATION_TIME) {
            animate(list) toBe {
                alpha(1f)
            }
        }.start()
    }

    private fun animateInitial() {
        please {
            animate(spinnyBoi) toBe {
                alpha(1f)
                scale(1f, 1f)
            }
            animate(list) toBe {
                alpha(0f)
            }
        }.now()
    }

    enum class State { SEARCH, SELECT, LINK, SUCCESS, FAIL }

    companion object {
        fun newInstance(): DiscoveryFragment = DiscoveryFragment()
    }
}