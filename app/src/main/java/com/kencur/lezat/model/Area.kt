package com.kencur.lezat.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Area(

    val strName: String,

    val strCode: String,

    var boolSelected: Boolean
) : Parcelable