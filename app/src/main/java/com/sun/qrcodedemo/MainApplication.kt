package com.sun.qrcodedemo

import android.app.Application
import com.sun.qrcodedemo.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Duong Tuan on 24/09/2019.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}