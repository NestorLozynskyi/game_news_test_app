package com.test.gamenews.app

import androidx.multidex.MultiDexApplication
import com.test.gamenews.di.networkModule
import com.test.gamenews.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    companion object {
        private lateinit var app: App
        fun get(): App = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        initKoin()
    }

    /*private fun initFirebase() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }*/

    private fun initKoin() {

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    networkModule
                )
            )
        }
    }
}