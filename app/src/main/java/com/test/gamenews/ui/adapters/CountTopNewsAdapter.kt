package com.test.gamenews.ui.adapters

import com.test.gamenews.R
import com.test.gamenews.base.BaseAdapter
import com.test.gamenews.data.CountPointTopNews
import com.test.gamenews.utils.common.ViewHolder
import com.test.gamenews.utils.extensions.loadFromResources
import kotlinx.android.synthetic.main.item_count_bar_top_news.view.*

class CountTopNewsAdapter :
    BaseAdapter<CountPointTopNews>(R.layout.item_count_bar_top_news) {

    override fun bindViewHolder(holder: ViewHolder, data: CountPointTopNews) {
        holder.itemView.apply {
            data.apply {
                if (focus) point.loadFromResources(R.drawable.ic_pint_blue_24)
                else point.loadFromResources(R.drawable.ic_point_24)
            }
        }
    }
}