package com.kencur.lezat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kencur.lezat.databinding.ActivityDetailBinding
import com.kencur.lezat.model.Meal
import com.kencur.lezat.utils.ViewUtil
import com.kencur.lezat.utils.hide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val meal = intent.getParcelableExtra<Meal>(EXTRA_DATA)
        meal?.let {
            ViewUtil.setMealTitle(meal.strMeal, binding.tvTitle)
            binding.tvCategory.text = meal.strCategory
            binding.tvArea.text = meal.strArea
            setInstructions(meal.strInstructions)

            val bsBehavior = BottomSheetBehavior.from(binding.bsInstructions.bottomSheet)
            binding.barrier.viewTreeObserver.addOnGlobalLayoutListener {
                val peekHeight = binding.nsv.height - binding.barrier.top - 128
                if (peekHeight - 72 > 72)
                    bsBehavior.setPeekHeight(peekHeight, true)
                bsBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }

            binding.cardImg.viewTreeObserver.addOnGlobalLayoutListener {
                val height = binding.nsv.height - binding.cardImg.top
                if (height - 256 > 256)
                    binding.bsInstructions.bottomSheet.layoutParams.height = height
            }

            val shimmer = Shimmer.ColorHighlightBuilder().build()

            val shimmerDrawable = ShimmerDrawable().apply {
                setShimmer(shimmer)
            }

            Glide.with(this)
                .load(meal.strMealThumb)
                .placeholder(shimmerDrawable)
                .into(binding.ivMeal)
        } ?: run {
            onBackPressed()
        }
    }

    private fun setInstructions(list: List<String>) {
        val views = arrayOf(
            binding.bsInstructions.tvInstructions.t1,
            binding.bsInstructions.tvInstructions.t2,
            binding.bsInstructions.tvInstructions.t3,
            binding.bsInstructions.tvInstructions.t4,
            binding.bsInstructions.tvInstructions.t5,
            binding.bsInstructions.tvInstructions.t6,
            binding.bsInstructions.tvInstructions.t7,
            binding.bsInstructions.tvInstructions.t8,
            binding.bsInstructions.tvInstructions.t9,
            binding.bsInstructions.tvInstructions.t10,
            binding.bsInstructions.tvInstructions.t11,
            binding.bsInstructions.tvInstructions.t12,
            binding.bsInstructions.tvInstructions.t13,
            binding.bsInstructions.tvInstructions.t14,
            binding.bsInstructions.tvInstructions.t15,
            binding.bsInstructions.tvInstructions.t16,
            binding.bsInstructions.tvInstructions.t17,
            binding.bsInstructions.tvInstructions.t18
        )

        val views2 = arrayOf(
            binding.bsInstructions.tvInstructions.b1,
            binding.bsInstructions.tvInstructions.b2,
            binding.bsInstructions.tvInstructions.b3,
            binding.bsInstructions.tvInstructions.b4,
            binding.bsInstructions.tvInstructions.b5,
            binding.bsInstructions.tvInstructions.b6,
            binding.bsInstructions.tvInstructions.b7,
            binding.bsInstructions.tvInstructions.b8,
            binding.bsInstructions.tvInstructions.b9,
            binding.bsInstructions.tvInstructions.b10,
            binding.bsInstructions.tvInstructions.b11,
            binding.bsInstructions.tvInstructions.b12,
            binding.bsInstructions.tvInstructions.b13,
            binding.bsInstructions.tvInstructions.b14,
            binding.bsInstructions.tvInstructions.b15,
            binding.bsInstructions.tvInstructions.b16,
            binding.bsInstructions.tvInstructions.b17,
            binding.bsInstructions.tvInstructions.b18
        )

        views.forEachIndexed { i, v ->
            if (list.size > i)
                v.text = list[i]
            else {
                v.hide()
                views2[i].hide()
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}