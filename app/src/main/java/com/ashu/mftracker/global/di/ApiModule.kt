package com.ashu.mftracker.global.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

//    @Singleton
//    @Provides
//    fun provideUserApiService(retrofit: Retrofit) = retrofit.create(UserApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideUserApiHelper(userAPiHelper: UserApiHelperImp): UserAPiHelper = userAPiHelper
//
//    @Singleton
//    @Provides
//    fun provideSalaryApiService(retrofit: Retrofit) = retrofit.create(SalaryApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideSalaryApiHelper(salaryApiHelper: SalaryApiHelperImp): SalaryApiHelper = salaryApiHelper

}