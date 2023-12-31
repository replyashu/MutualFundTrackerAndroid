package com.ashu.mftracker.api.funds

import com.ashu.mftracker.data.response.MutualFundNav
import com.ashu.mftracker.data.response.MutualFundWithoutNav
import com.ashu.mftracker.data.response.MutualFundsWithNav
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

class FundsApiHelperImp @Inject constructor(private val fundsApiService: FundsApiService): FundsApiHelper {

    override suspend fun fetchMutualFundsWithoutNav(): Response<ArrayList<MutualFundWithoutNav>> =
        fundsApiService.fetchMutualFundsWithoutNave()

    override suspend fun fetchMutualFundsWithNav(): Response<MutualFundsWithNav> =
        fundsApiService.fetchMutualFundsWithNav()

    override suspend fun fetchMutualFundNav(schemeId: String): Response<List<MutualFundNav>> =
        fundsApiService.fetchMutualFundNav(schemeId)
}