package com.ashu.mftracker.repository

import com.ashu.mftracker.api.funds.FundsApiHelper
import javax.inject.Inject

class FundsRepository @Inject constructor(private val fundsApiHelper: FundsApiHelper) {

    suspend fun fetchMutualFundsWithNav() = fundsApiHelper.fetchMutualFundsWithNav()

    suspend fun fetchMutualFundsWithoutNav() = fundsApiHelper.fetchMutualFundsWithoutNav()

    suspend fun fetchMutualFundNav(schemeId: String) = fundsApiHelper.fetchMutualFundNav(schemeId)

}