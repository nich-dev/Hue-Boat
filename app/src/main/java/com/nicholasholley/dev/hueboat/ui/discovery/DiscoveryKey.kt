package com.nicholasholley.dev.hueboat.ui.discovery

import android.os.Parcelable
import com.nicholasholley.dev.hueboat.ui.common.BaseFragment
import com.nicholasholley.dev.hueboat.ui.common.BaseKey
import paperparcel.PaperParcel

@PaperParcel
object DiscoveryKey: BaseKey() {
    override fun createFragment(): BaseFragment = DiscoveryFragment.newInstance()
    @JvmField val CREATOR: Parcelable.Creator<DiscoveryKey> = PaperParcelDiscoveryKey.CREATOR
    override fun toString(): String = this::class.java.name // you NEED to implement this in an `object` because of Kotlin!

}