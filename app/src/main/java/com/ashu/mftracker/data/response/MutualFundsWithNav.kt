package com.ashu.mftracker.data.response

data class MutualFundsWithNav(
    val schemeCode: String,
    val schemeName: String,
    val mutualFundNav: List<MutualFundNav>
)
