package com.test.gamenews.network

import com.test.gamenews.data.News
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface ApiInterface {

    @GET("/")
    fun getNews(
        @Query("page") page: Int
    ) : Single<ArrayList<News>>
}