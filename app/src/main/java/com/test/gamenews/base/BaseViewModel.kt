package com.test.gamenews.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.gamenews.data.*
import com.test.gamenews.network.ApiInterface
import com.test.gamenews.network.RetrofitClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named


open class BaseViewModel(
    private val context: Context
) : ViewModel(), KoinComponent {

    @LayoutRes
    var parentLayoutId: Int = 0

    val data: MutableLiveData<Any> by inject()
    val news: MutableLiveData<NewsList> by inject(named("newsList"))

    private val api = RetrofitClient
        .getRetrofit(Constants.BASE_URL, context)
        .create(ApiInterface::class.java)

    private val compositeDisposable = CompositeDisposable()

    private var newsList = arrayListOf<News>()
    fun getNews(page: Int){
        compositeDisposable.add(
                api.getNews(page).observeAndSubscribe()
                .subscribe({
                    val pagination: Pagination =
                        if (it.count() == 5){
                            Pagination(page, page+2)
                        } else {
                            Pagination(page, page+1)
                        }
                    if (page == 0){
                        newsList = it
                    } else {
                        newsList.addAll(it)
                    }
                    news.value = NewsList (newsList, pagination)
                },{
                    //todo
                })
        )
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}

fun <T> Single<T>.observeAndSubscribe() =
    subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())