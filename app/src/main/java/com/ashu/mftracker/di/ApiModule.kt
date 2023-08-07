package com.ashu.mftracker.di

import com.ashu.mftracker.api.user.UserAPiHelper
import com.ashu.mftracker.api.user.UserApiHelperImp
import com.ashu.mftracker.api.user.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideUserApiService(retrofit: Retrofit) = retrofit.create(UserApiService::class.java)

    @Singleton
    @Provides
    fun provideUserApiHelper(userAPiHelper: UserApiHelperImp): UserAPiHelper = userAPiHelper

}