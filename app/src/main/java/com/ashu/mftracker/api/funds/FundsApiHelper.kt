package com.ashu.mftracker.api.funds

import com.ashu.mftracker.data.response.MutualFundNav
import com.ashu.mftracker.data.response.MutualFundWithoutNav
import com.ashu.mftracker.data.response.MutualFundsWithNav
import retrofit2.Response

interface FundsApiHelper {

    suspend fun fetchMutualFundsWithoutNav(): Response<ArrayList<MutualFundWithoutNav>>

    suspend fun fetchMutualFundsWithNav(): Response<MutualFundsWithNav>

    suspend fun fetchMutualFundNav(schemeId: String): Response<List<MutualFundNav>>
}