package com.sun.qrcodedemo.di

import com.sun.qrcodedemo.ui.screen.blank.BlankViewModel
import com.sun.qrcodedemo.ui.screen.main.MainViewModel
import org.koin.dsl.module

/**
 * Created by Duong Tuan on 23/09/2019.
 */

val viewModelModule = module {
    single { MainViewModel() }
    single { BlankViewModel() }
}