package com.nicholasholley.dev.hueboat.ui.discovery

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.text.TextUtils
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.nicholasholley.dev.hueboat.R
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

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_upnp_device, parent, false) ?: View(context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder.friendlyName != null) {
            var friendlyName = item.scrubbedFriendlyName
            if (TextUtils.isEmpty(friendlyName)) {
                friendlyName = "[unnamed]"
            }
            holder.friendlyName!!.text = friendlyName
        }
        if (holder.location != null) {
            val loc = item.location!!.toExternalForm()// Uncomment to obscure actual ip addresses for screenshots
            // .replaceAll("[0-9]+\\.[0-9]+\\.[0-9]+", "192.258.1")
            linkify(holder.location, null, loc)
        }
    }

    private fun linkify(view: TextView?, str: CharSequence?, url: String) {
        var str = str
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(url)) {
            view!!.visibility = View.GONE
            return
        }

        view!!.visibility = View.VISIBLE
        if (TextUtils.isEmpty(url)) {
            view.text = str
            return
        }

        if (TextUtils.isEmpty(str)) {
            str = url
        }

        val builder = SpannableBuilder(view.context)
        builder.append(str, URLSpan(url))

        view.setText(builder.build())
        view.movementMethod = LinkMovementMethod.getInstance()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var friendlyName: TextView
        var location: TextView

        init {
            friendlyName = view.findViewById(R.id.friendly_name) as TextView
            location = view.findViewById(R.id.location) as TextView
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