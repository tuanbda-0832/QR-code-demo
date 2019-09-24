package com.sun.qrcodedemo.di

import android.content.Context
import com.sun.qrcodedemo.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

/**
 * Created by Duong Tuan on 23/09/2019.
 */

val networkModule = module {
    single { createOkHttpCache(get()) }
    single(named("logging")) { createLoggingInterceptor() }
//    single(named("header")) { createHeaderInterceptor() }
    single { createOkHttpClient(get(named("logging")), get(named("header"))) }
//    single { createAppRetrofit(get()) }
//    single { createApiService(get(), get(), get()) }
}

const val TIMEOUT = 10

fun createOkHttpCache(context: Context): Cache =
    Cache(context.cacheDir, (10 * 1024 * 1024).toLong())

fun createLoggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

//fun createHeaderInterceptor(): Interceptor =
//    Interceptor { chain ->
//        val request = chain.request()
//        val newUrl = request.url().newBuilder()
//            .addQueryParameter("api_key", BuildConfig.TMBD_API_KEY)
//            .build()
//        val newRequest = request.newBuilder()
//            .url(newUrl)
////            .header("Content-Type", "application/json")
////            .header("X-App-Secret", "1234567890")
////            .header("Authorization", userRepositoryImpl.getAccessToken())
//            .method(request.method(), request.body())
//            .build()
//        chain.proceed(newRequest)
//    }

fun createOkHttpClient(
//    cache: Cache,
    logging: Interceptor,
    header: Interceptor
): OkHttpClient =
    OkHttpClient.Builder()
//        .cache(cache)
        .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(header)
        .addInterceptor(logging)
        .build()
//
//fun createAppRetrofit(
//    okHttpClient: OkHttpClient
////    rxErrorHandlingFactory: RxErrorHandlingFactory,
//): Retrofit =
//    Retrofit.Builder()
////        .addCallAdapterFactory(rxErrorHandlingFactory)
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BuildConfig.BASE_URL)
//        .client(okHttpClient)
//        .build()
