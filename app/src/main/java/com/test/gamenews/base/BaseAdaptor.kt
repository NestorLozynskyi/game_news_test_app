package com.test.gamenews.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.test.gamenews.utils.common.ViewHolder
import com.test.gamenews.utils.extensions.inflate

abstract class BaseAdapter<T>(@LayoutRes val layoutID: Int) : RecyclerView.Adapter<ViewHolder>() {

    private var items = arrayListOf<T>()

    open fun setData(data: ArrayList<T>) {
        items = data
        notifyDataSetChanged()
    }

    open fun getData() = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(layoutID))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bindViewHolder(this, items[holder.adapterPosition])
        }
    }

    abstract fun bindViewHolder(holder: ViewHolder, data: T)

}