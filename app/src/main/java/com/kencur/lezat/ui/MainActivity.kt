package com.kencur.lezat.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kencur.lezat.R
import com.kencur.lezat.adapter.MealAdapter
import com.kencur.lezat.databinding.ActivityMainBinding
import com.kencur.lezat.utils.MealContent
import com.kencur.lezat.utils.hide
import com.kencur.lezat.utils.show

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MealAdapter
    private var isClickValid = false
    private var actionDownXY = IntArray(2)
    private var categoryState = CategoryState.NONE

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mealContent = MealContent.MEAL.shuffled()

        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_about -> {
                    Toast.makeText(this, getString(R.string.action_about), Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                else -> false
            }
        }

        adapter = MealAdapter(mealContent)
        binding.list.adapter = adapter
        filterList()

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
                    if (isClickValid)
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

        categoryState = if (categoryState == newState) {
            bNew.hide()
            CategoryState.NONE
        } else {
            if (categoryState != CategoryState.NONE) {
                val bOld = getCategoryBulletViews()[categoryState.ordinal]

                bOld.hide()
            }
            bNew.show()
            newState
        }

        filterList()
    }

    private fun filterList() {
        if (categoryState != CategoryState.NONE) {
            adapter.filter.filter(getCategoryViews()[categoryState.ordinal].text.toString())
        } else
            adapter.filter.filter("")
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