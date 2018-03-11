package com.nicholasholley.dev.hueboat.ui.discovery

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dukeenergy.etrac.di.MarkForInjection
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.data.network.upnp.UPnPDeviceAdapter
import com.nicholasholley.dev.hueboat.data.network.upnp.UPnPDeviceFinder
import com.nicholasholley.dev.hueboat.ui.common.BaseFragment
import com.nicholasholley.dev.hueboat.util.log.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import android.view.animation.DecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.ProgressBar
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_discovery.*


/**
 *
 * A fragment that shows discovery Bridge options
 */

class DiscoveryFragment: BaseFragment(), MarkForInjection {
    //injection
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var uPnPAdapter: UPnPDeviceAdapter
    var list: RecyclerView? = null
    var spinnyBoi: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parent = inflater.inflate(R.layout.fragment_discovery, container, false)
        uPnPAdapter = UPnPDeviceAdapter(context!!)
        list = parent.findViewById<RecyclerView>(R.id.list)?.apply {
            adapter = uPnPAdapter
            layoutManager = LinearLayoutManager(context)
            visibility = View.INVISIBLE
        }
        spinnyBoi = parent.findViewById<ProgressBar>(R.id.spinner)?.apply{
            visibility = View.VISIBLE
        }
        return parent
    }

    override fun bindToVM() {
        UPnPDeviceFinder().observe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (uPnPAdapter.getItemCount() == 0) {
                        spinnyBoi?.animate()
                                ?.alpha(0f)
                                ?.setDuration(1000)
                                ?.setInterpolator(AccelerateInterpolator())
                                ?.start()

                        list?.setAlpha(0f)
                        list?.setVisibility(View.VISIBLE)
                        list?.animate()
                                ?.alpha(1f)
                                ?.setDuration(1000)
                                ?.setStartDelay(1000)
                                ?.setInterpolator(DecelerateInterpolator())
                                ?.start()
                    }

                    uPnPAdapter.add(it)
                }
    }

    companion object {
        fun newInstance(): DiscoveryFragment = DiscoveryFragment()
    }
}