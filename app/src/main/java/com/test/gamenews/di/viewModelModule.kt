package com.test.gamenews.di

import androidx.lifecycle.MutableLiveData
import com.test.gamenews.base.BaseViewModel
import com.test.gamenews.data.NewsList
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel


val viewModelModule = module {

    viewModel { BaseViewModel(get()) }

    single { MutableLiveData<Any>() }
    single(named("newsList")) { MutableLiveData<NewsList>() }
}