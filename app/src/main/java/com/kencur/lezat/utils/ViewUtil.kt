package com.kencur.lezat.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView

object ViewUtil {

    fun setMealTitle(txt: String, tvTitle: TextView) {
        if (txt.trim().contains(' ')) {
            val spannable: Spannable = SpannableString(txt)
            val iSpace = txt.indexOf(' ')
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                iSpace,
                txt.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            tvTitle.text = spannable
        } else
            tvTitle.text = txt
    }
}