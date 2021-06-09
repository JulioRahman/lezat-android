package com.kencur.lezat.ui

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kencur.lezat.R
import com.kencur.lezat.adapter.AreaAdapter
import com.kencur.lezat.adapter.MealAdapter
import com.kencur.lezat.databinding.ActivityMainBinding
import com.kencur.lezat.utils.MealContent
import com.kencur.lezat.utils.hide
import com.kencur.lezat.utils.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mealAdapter: MealAdapter
    private lateinit var areaAdapter: AreaAdapter
    private lateinit var defaultTextColors: ColorStateList
    private var isClickValid = false
    private var actionDownXY = IntArray(2)
    private var categoryState = CategoryState.NONE

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mealContent = MealContent.MEAL.shuffled()
        defaultTextColors = binding.text1.textColors

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_about -> {
                    startActivity(
                        Intent(this, ProfileActivity::class.java),
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    )
                    true
                }
                else -> false
            }
        }

        mealAdapter = MealAdapter(mealContent)
        binding.list.adapter = mealAdapter

        areaAdapter = AreaAdapter(mealContent.map { it.objArea }.groupBy { it.strName }
            .map { it.value.first() }, mealAdapter, defaultTextColors)
        binding.listArea.adapter = areaAdapter

        // Dispatch click trough view behind the recyclerview
        binding.list.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    actionDownXY[0] = event.rawX.toInt()
                    actionDownXY[1] = event.rawY.toInt()
                    isClickValid = true
                }
                MotionEvent.ACTION_MOVE -> isClickValid = false
                MotionEvent.ACTION_UP -> {
                    if (isClickValid) {
                        getCategoryViews().forEach { tv ->
                            val tvLoc = IntArray(2)
                            tv.getLocationOnScreen(tvLoc)
                            val left = tvLoc[0]
                            val top = tvLoc[1]

                            if (actionDownXY[0] > left && actionDownXY[0] < (left + tv.width)
                                && actionDownXY[1] > top && actionDownXY[1] < (top + tv.height)
                            ) {
                                tv.performClick()
                                return@setOnTouchListener false
                            }
                        }
                    }
                }
            }

            false
        }

        getCategoryViews().forEachIndexed { i, tv ->
            tv.setOnClickListener {
                changeCategoryState(
                    CategoryState.values()[i]
                )
            }
        }

        mealContent.groupBy { it.strCategory }.keys.forEachIndexed { i, s ->
            getCategoryViews()[i].text = s
        }
    }

    private fun changeCategoryState(newState: CategoryState) {
        val bNew = getCategoryBulletViews()[newState.ordinal]
        val tvNew = getCategoryViews()[newState.ordinal]

        categoryState = if (categoryState == newState) {
            bNew.hide()
            tvNew.setTextColor(defaultTextColors)
            CategoryState.NONE
        } else {
            if (categoryState != CategoryState.NONE) {
                val bOld = getCategoryBulletViews()[categoryState.ordinal]
                val tvOld = getCategoryViews()[categoryState.ordinal]

                bOld.hide()
                tvOld.setTextColor(defaultTextColors)
            }
            bNew.show()
            tvNew.setTextColor(Color.BLACK)
            newState
        }

        filterList()
    }

    private fun filterList() {
        if (categoryState != CategoryState.NONE) {
            mealAdapter.filterCategory(getCategoryViews()[categoryState.ordinal].text.toString())
        } else
            mealAdapter.filterCategory("")
    }

    private fun getCategoryViews(): List<TextView> {
        return arrayListOf(
            binding.text1,
            binding.text2,
            binding.text3,
            binding.text4,
            binding.text5
        )
    }

    private fun getCategoryBulletViews(): List<TextView> {
        return arrayListOf(
            binding.b1,
            binding.b2,
            binding.b3,
            binding.b4,
            binding.b5
        )
    }

    private enum class CategoryState {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        NONE
    }
}