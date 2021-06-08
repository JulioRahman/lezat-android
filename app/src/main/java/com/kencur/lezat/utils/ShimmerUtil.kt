package com.kencur.lezat.utils

import android.graphics.Color
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

object ShimmerUtil {

    fun getShimmerDrawable(): ShimmerDrawable {
        val shimmer = Shimmer.ColorHighlightBuilder()
            .setBaseColor(Color.parseColor("#DDDDDD"))
            .build()

        return ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
    }
}