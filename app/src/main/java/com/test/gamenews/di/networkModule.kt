package com.test.gamenews.di

import com.google.gson.GsonBuilder
import org.koin.dsl.module

val networkModule = module {

    fun provideGson() = GsonBuilder()
        .setLenient()
        .serializeSpecialFloatingPointValues()
        .serializeNulls().create()

    single { provideGson() }
}