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
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueLightWrapper
import com.nicholasholley.dev.hueboat.data.network.api.LightsApi
import kotlinx.android.synthetic.main.fragment_discovery.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 *
 * A fragment that shows discovery Bridge options
 */

class DiscoveryFragment: BaseFragment(), MarkForInjection {
    //injection
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var lightsApi: LightsApi
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
        parent.findViewById<Button>(R.id.test_button)?.setOnClickListener {
            testApi()
        }
        return parent
    }

    private fun testApi() {
        lightsApi.getAll("username").enqueue(object: Callback<HueLightWrapper>{
            override fun onFailure(call: Call<HueLightWrapper>?, t: Throwable?) {
                Log.d(t.toString())
            }

            override fun onResponse(call: Call<HueLightWrapper>?, response: Response<HueLightWrapper>?) {
                response?.body()?.let {
                    Log.d("Found ${it.lights?.size} lights")
                    for (lightEntry in it.lights ?: hashMapOf()){
                        Log.d(lightEntry.toString())
                    }
                }
            }
        })
    }

    override fun bindToVM() {
        UPnPDeviceFinder().observe()
                .subscribeOn(Schedulers.io())
                .filter {
                    try {
                        it.downloadSpecs()
                        Log.d(it.mProperties?.toString() ?: "blegh")
                    } catch (e: Exception) {
                        Log.d(e.message ?: "Didnt dl specs")
                    }
                    true
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("doin a subscribe-boi")
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