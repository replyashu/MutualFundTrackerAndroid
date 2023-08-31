package com.ashu.mftracker.api.funds

import com.ashu.mftracker.data.response.MutualFundNav
import com.ashu.mftracker.data.response.MutualFundWithoutNav
import com.ashu.mftracker.data.response.MutualFundsWithNav
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface FundsApiService {

    @GET("fetch-mutual-funds")
    suspend fun fetchMutualFundsWithNav(): Response<MutualFundsWithNav>

    @GET("fetch-mutual-funds-names")
    suspend fun fetchMutualFundsWithoutNave(): Response<ArrayList<MutualFundWithoutNav>>

    @GET("find-mutual-fund-nav")
    suspend fun fetchMutualFundNav(@Query("mutualFundSchemeId") schemeId: String ): Response<List<MutualFundNav>>
}