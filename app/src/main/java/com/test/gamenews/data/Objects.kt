package com.test.gamenews.data

data class Pagination(
    var page: Int,
    var totalPage: Int
)

data class News(
    val title: String,
    val type: String,
    val img: String?,
    val click_url: String?,
    val url: String?,
    val time: String,
    val top: Int,
    var topId: Int?
)

data class NewsList(
    var news: ArrayList<News>,
    var pagination: Pagination
)

data class CountPointTopNews(
    val id: Int,
    val focus: Boolean)