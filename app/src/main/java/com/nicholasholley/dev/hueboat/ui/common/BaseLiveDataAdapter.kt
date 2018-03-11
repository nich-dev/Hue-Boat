package com.nicholasholley.dev.hueboat.ui.common

import android.support.v7.widget.RecyclerView


abstract class BaseLiveDataAdapter<T, VH: RecyclerView.ViewHolder> (): RecyclerView.Adapter<VH>(){
    var items: List<T>? = null

    open fun setItemsList(
        items: List<T>
    ) {
        if (this.items == null) {
            this.items = items
            notifyItemRangeChanged(0, items.size)
        } else {
            this.items = items
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        if (items == null) return 0
        return items!!.size
    }
}