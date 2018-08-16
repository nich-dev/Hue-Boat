package com.nicholasholley.dev.hueboat.ui.discovery

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboatsdk.data.network.upnp.UPnPData
import com.nicholasholley.dev.hueboatsdk.data.network.upnp.UPnPDeviceComparator
import java.util.*


/**
 * Created by nhunc on 3/11/2018.
 */
class UPnPDeviceAdapter(
        val context: Context
): RecyclerView.Adapter<UPnPDeviceAdapter.ViewHolder>() {
    interface ItemClickListener {
        fun onClick(item: UPnPData, position: Int)
    }

    private val mComparator = UPnPDeviceComparator()

    private var inflater: LayoutInflater? = null
    private var mItems: ArrayList<UPnPData> = ArrayList()
    private var mListener: ItemClickListener? = null

    fun setItemClickListener(listener: ItemClickListener) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun getItem(position: Int): UPnPData {
        return mItems[position]
    }

    fun clear() {
        val count = mItems.size
        mItems.clear()
        notifyItemRangeRemoved(0, count)
    }

    fun add(item: UPnPData) {
        val index = Collections.binarySearch(mItems, item, mComparator)
        if (index < 0) {
            val position = -index - 1
            mItems.add(position, item)
            notifyItemInserted(position)
        } else {
            mItems[index] = item
            notifyItemChanged(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        LayoutInflater.from(parent.context).inflate(R.layout.item_upnp_device_card, parent, false)!!.let {
            ViewHolder(it)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initialize(getItem(position))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var friendlyName: TextView = view.findViewById(R.id.friendly_name) as TextView
        var location: TextView = view.findViewById(R.id.location) as TextView
        lateinit var data: UPnPData

        fun initialize(item: UPnPData) {
            data = item
            friendlyName.text = item.scrubbedFriendlyName?.let {
                if (TextUtils.isEmpty(it)) "[unnamed]" else it.split("(")[0]
            }
            location.text = item.host
        }

        fun click(view: View) {
            val position = adapterPosition
            if (mListener != null) {
                mListener!!.onClick(mItems[position], position)
                notifyItemChanged(position)
            }
        }
    }
}