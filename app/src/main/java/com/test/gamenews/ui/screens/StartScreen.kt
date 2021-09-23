package com.test.gamenews.ui.screens

import com.test.gamenews.R
import com.test.gamenews.base.BaseFragment
import com.test.gamenews.data.Constants
import com.test.gamenews.data.CountPointTopNews
import com.test.gamenews.data.News
import com.test.gamenews.ui.adapters.CountTopNewsAdapter
import com.test.gamenews.ui.adapters.NewsAdapter
import com.test.gamenews.ui.adapters.TopNewsAdapter
import com.test.gamenews.utils.extensions.gone
import com.test.gamenews.utils.extensions.visible
import kotlinx.android.synthetic.main.content_all_news.*
import kotlinx.android.synthetic.main.content_count_bar_top_news.*
import kotlinx.android.synthetic.main.content_tab_bar.*
import kotlinx.android.synthetic.main.content_top_news.*

class StartScreen: BaseFragment(R.layout.screen_start) {

    private var filter = 0
    /** 0 - stories 1 - video 2 - favourites **/

    private var correctNewsList: Array<ArrayList<News>> = arrayOf(arrayListOf(),arrayListOf(),arrayListOf())
    private var correctTopNewsList: Array<ArrayList<News>> = arrayOf(arrayListOf(),arrayListOf(),arrayListOf())

    private lateinit var adapter: NewsAdapter
    private lateinit var adapterTop: TopNewsAdapter
    private lateinit var adapterCountTop: CountTopNewsAdapter

    override fun initialize() {

        variant_0.setOnClickListener {
            filter = 0
            variant_0_strip.visible()
            variant_1_strip.gone()
            variant_2_strip.gone()
            showInfo()
        }
        variant_1.setOnClickListener {
            filter = 1
            variant_0_strip.gone()
            variant_1_strip.visible()
            variant_2_strip.gone()
            showInfo()
        }
        variant_2.setOnClickListener {
            filter = 2
            variant_0_strip.gone()
            variant_1_strip.gone()
            variant_2_strip.visible()
            showInfo()
        }

        viewModel.getNews(0)

        adapterTop = TopNewsAdapter {
            setCount(it)
        }
        adapter = NewsAdapter()
        adapterCountTop = CountTopNewsAdapter()

        recycler_top_news.adapter = adapterTop
        recycler_all_news.adapter = adapter
        recycler_count_top_news.adapter = adapterCountTop
    }

    override fun observe() {
        viewModel.news.observe(viewLifecycleOwner, {
            if (it.pagination.totalPage > it.pagination.page+1) {
                getNextPage(it.pagination.page + 1)
            } /** else {   -> to save CPU performance, but there will be no soft display of information **/
            correctTopNewsList[0].clear()
            correctTopNewsList[1].clear()
            correctTopNewsList[2].clear()
            correctNewsList[0].clear()
            correctNewsList[1].clear()
            correctNewsList[2].clear()

            var count: Array<Int> = arrayOf(0,0,0)
            for (i in it.news){
                val arrayNum = when (i.type){
                    Constants.TYPE_STORIES -> 0
                    Constants.TYPE_VIDEO -> 1
                    Constants.TYPE_FAVOURITES -> 2
                    else -> 0
                }
                if (i.top == 1){
                    correctTopNewsList[arrayNum].add(News(i.title, i.type, i.img, i.click_url, i.url, i.time, i.top, count[arrayNum]))
                    count[arrayNum]++
                } else {
                    correctNewsList[arrayNum].add(i)
                }
            }
            showInfo()
        })
    }

    private fun getNextPage(page: Int){
        viewModel.getNews(page)
    }

    private fun showInfo(){
        setCount(0)
        adapterTop.setData(correctTopNewsList[filter])
        adapter.setData(correctNewsList[filter])

    }

    private fun setCount(id: Int){
        val itemCount = correctTopNewsList[filter].count()
        val itemCountList: ArrayList<CountPointTopNews> = arrayListOf()
        for (i in 0..itemCount){
            if (i == id) itemCountList.add(CountPointTopNews(i,true))
            else itemCountList.add(CountPointTopNews(i,false))
        }
        adapterCountTop.setData(itemCountList)
    }

}