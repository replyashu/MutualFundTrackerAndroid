package com.ashu.mftracker.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MutualFundNav(
    val date: String,
    val nav: String
) : Parcelable
