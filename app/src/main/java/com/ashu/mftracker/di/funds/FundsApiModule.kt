package com.ashu.mftracker.di.funds

import com.ashu.mftracker.api.funds.FundsApiHelper
import com.ashu.mftracker.api.funds.FundsApiHelperImp
import com.ashu.mftracker.api.funds.FundsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FundsApiModule {

    @Singleton
    @Provides
    fun provideFundsApiService(retrofit: Retrofit) = retrofit.create(FundsApiService::class.java)

    @Singleton
    @Provides
    fun provideFundsApiHelper(fundsAPiHelper: FundsApiHelperImp): FundsApiHelper = fundsAPiHelper
}