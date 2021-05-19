package com.kencur.lezat

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Meal(
    val strMeal: String,

    val strCategory: String,

    val strArea: String,

    val strInstructions: List<String>,

    val strMealThumb: String

) : Parcelable
