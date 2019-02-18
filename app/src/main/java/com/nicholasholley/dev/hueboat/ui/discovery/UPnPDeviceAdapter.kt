package com.nicholasholley.dev.hueboat.ui.discovery

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPData
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDeviceComparator
import java.util.*

class UPnPDeviceAdapter(
        private val listener: DeviceClickListener? = null
): androidx.recyclerview.widget.RecyclerView.Adapter<UPnPDeviceAdapter.ViewHolder>() {
    interface DeviceClickListener {
        fun onClick(item: UPnPData, position: Int)
    }

    private val comparator = UPnPDeviceComparator()
    private var items: MutableList<UPnPData> = mutableListOf()

    override fun getItemCount(): Int {
        return items.size
    }

    fun clear() {
        val count = items.size
        items.clear()
        notifyItemRangeRemoved(0, count)
    }

    fun add(item: UPnPData) {
        val index = Collections.binarySearch(items, item, comparator)
        if (index < 0) {
            val position = -index - 1
            items.add(position, item)
            notifyItemInserted(position)
        } else {
            items[index] = item
            notifyItemChanged(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_upnp_device_card, parent, false)!!.let {
            ViewHolder(it)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        private val friendlyName: TextView = view.findViewById(R.id.friendly_name) as TextView
        private val location: TextView = view.findViewById(R.id.location) as TextView
        lateinit var data: UPnPData

        fun bind(item: UPnPData) {
            data = item
            friendlyName.text = item.scrubbedFriendlyName?.let {
                if (TextUtils.isEmpty(it)) "[unnamed]" else it.split("(")[0]
            }
            location.text = item.host
        }

        fun click(view: View) {
            val position = adapterPosition
            if (listener != null) {
                listener!!.onClick(items[position], position)
                notifyItemChanged(position)
            }
        }
    }
}