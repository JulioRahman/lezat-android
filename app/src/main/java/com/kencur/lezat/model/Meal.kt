package com.kencur.lezat.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Meal(

    val strMeal: String,

    val strCategory: String,

    val objArea: Area,

    val strInstructions: List<String>,

    val strMealThumb: String
) : Parcelable
