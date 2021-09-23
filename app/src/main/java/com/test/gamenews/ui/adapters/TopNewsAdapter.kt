package com.test.gamenews.ui.adapters


import com.test.gamenews.R
import com.test.gamenews.base.BaseAdapter
import com.test.gamenews.data.News
import com.test.gamenews.utils.common.ViewHolder
import com.test.gamenews.utils.extensions.gone
import com.test.gamenews.utils.extensions.loadImage
import com.test.gamenews.utils.extensions.visible
import kotlinx.android.synthetic.main.item_all_news.view.creation_time
import kotlinx.android.synthetic.main.item_all_news.view.description
import kotlinx.android.synthetic.main.item_all_news.view.image
import kotlinx.android.synthetic.main.item_all_news.view.link
import java.util.*

class TopNewsAdapter(private val listener: (Int) -> Unit) :
    BaseAdapter<News>(R.layout.item_top_news) {

    override fun bindViewHolder(holder: ViewHolder, data: News) {
        holder.itemView.apply {
            data.apply {
                description.text = title

                if (!click_url.isNullOrEmpty()) {   //todo wrong from backend
                    val nameLink = click_url.split("//")
                    link.text = if (nameLink[1].isNotEmpty()) nameLink[1].replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }
                    else click_url
                } else if (!url.isNullOrEmpty()){
                    val nameLink = url.split("//")
                    link.text = if (nameLink[1].isNotEmpty()) nameLink[1].replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    }
                    else url
                } else link.text = resources.getString(R.string.invalid_link)

                creation_time.text = time
                if (img != null){
                    image.visible()
                    image.loadImage(img)
                } else image.gone()

                setOnFocusChangeListener { v, hasFocus ->
                    topId?.let { listener.invoke(it) }
                }
            }
        }
    }
}