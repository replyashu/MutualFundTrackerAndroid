package com.ashu.mftracker.global.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Provides
//    fun providesGsonConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @Provides
//    fun provideBaseUrl() = if (BuildConfig.BUILD_TYPE == "emulator") {
//        Constant.BASE_URL_EMULATOR
//    } else {
//        Constant.BASE_URL
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addNetworkInterceptor(loggingInterceptor)
//            .addInterceptor(CurlLoggerInterceptor())
////            .addInterceptor()
//            .build()
//    } else {
//        OkHttpClient
//            .Builder()
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
//        Retrofit.Builder()
//            .addConverterFactory(providesGsonConverterFactory())
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .build()
}