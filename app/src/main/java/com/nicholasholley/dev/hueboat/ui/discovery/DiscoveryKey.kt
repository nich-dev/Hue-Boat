package com.nicholasholley.dev.hueboat.ui.discovery

import android.os.Parcelable
import com.nicholasholley.dev.hueboat.ui.common.CoroutineFragment
import com.nicholasholley.dev.hueboat.ui.common.BaseKey
import paperparcel.PaperParcel

@PaperParcel
object DiscoveryKey: BaseKey() {
    override fun createFragment(): CoroutineFragment = DiscoveryFragment.newInstance()
    @JvmField val CREATOR: Parcelable.Creator<DiscoveryKey> = PaperParcelDiscoveryKey.CREATOR
    override fun toString(): String = this::class.java.name // you NEED to implement this in an `object` because of Kotlin!

}