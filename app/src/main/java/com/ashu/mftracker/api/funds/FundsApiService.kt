package com.ashu.mftracker.api.funds

import com.ashu.mftracker.data.response.MutualFundWithoutNav
import com.ashu.mftracker.data.response.MutualFundsWithNav
import retrofit2.Response
import retrofit2.http.GET

interface FundsApiService {

    @GET("fetch-mutual-funds")
    suspend fun fetchMutualFundsWithNav(): Response<MutualFundsWithNav>

    @GET("fetch-mutual-funds-names")
    suspend fun fetchMutualFundsWithoutNave(): Response<MutualFundWithoutNav>
}