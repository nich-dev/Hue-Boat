package com.nicholasholley.dev.hueboat.ui.discovery

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.github.florent37.kotlin.pleaseanimate.PleaseAnim
import com.github.florent37.kotlin.pleaseanimate.please
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.di.MarkForInjection
import com.nicholasholley.dev.hueboat.ui.common.BaseFragment
import com.nicholasholley.dev.hueboat.util.Constants
import com.nicholasholley.dev.hueboat.util.ext.delay
import com.nicholasholley.dev.hueboat.util.ext.observe
import kotlinx.android.synthetic.main.fragment_discovery.*
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
    private var animateState: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        animateState = false
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
        delay(5000L) {
            vm.fragmentState.value = State.SELECT
        }
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
                State.SELECT -> stateSelect(animateState)
                else -> stateInitial(false)
            }
            animateState = true
        }
    }

    private fun stateSelect(animate: Boolean = true) {
        please(duration = Constants.STANDARD_ANIMATION_TIME) {
            animate(spinnyBoi) toBe {
                alpha(0f)
                scale(0.5f, 0.5f)
            }
            animate(body) toBe {
                alpha(0f)
            }
            animate(title) toBe {
                alpha(0f)
            }
        }.thenCouldYou(duration = Constants.STANDARD_ANIMATION_TIME) {
            animate(list) toBe {
                alpha(1f)
            }
            animate(body) toBe {
                alpha(1f)
            }
            animate(title) toBe {
                alpha(1f)
            }
        }.let {
            doAnimate(it, State.SELECT, animate)
        }
    }

    private fun stateInitial(animate: Boolean = true) {
        please {
            animate(spinnyBoi) toBe {
                alpha(1f)
                scale(1f, 1f)
            }
            animate(list) toBe {
                alpha(0f)
            }
            animate(body) toBe {
                alpha(1f)
            }
            animate(title) toBe {
                alpha(1f)
            }
        }.let {
            doAnimate(it, State.SEARCH, animate)
        }
    }

    private fun doAnimate(anim: PleaseAnim, state: State, animate: Boolean = true) {
        if (animate){
            anim.start()
            delay { setText(state) }
        } else {
            anim.now()
            setText(state)
        }
    }

    private fun setText(state: State) {
        when (state) {
            State.SELECT -> setSelectText()
            else -> setInitialText()
        }
    }

    private fun setInitialText() {
        title.setText(R.string.bridge_search)
        body.setText(R.string.searching)
    }

    private fun setSelectText() {
        title.setText(R.string.bridge_selection)
        body.setText(R.string.choose_bridge)
    }

    enum class State { SEARCH, SELECT, LINK, SUCCESS, FAIL }

    companion object {
        fun newInstance(): DiscoveryFragment = DiscoveryFragment()
    }
}