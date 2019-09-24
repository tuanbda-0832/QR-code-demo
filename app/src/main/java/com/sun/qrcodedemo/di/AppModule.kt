package com.sun.qrcodedemo.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Duong Tuan on 23/09/2019.
 */
val appModule = module {
    single { androidApplication().resources }
}

val appModules = listOf(appModule, networkModule, repositoryModule, viewModelModule)