package com.kencur.lezat.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kencur.lezat.databinding.ItemMealBinding
import com.kencur.lezat.model.Meal
import com.kencur.lezat.ui.DetailActivity
import com.kencur.lezat.utils.ShimmerUtil
import com.kencur.lezat.utils.ViewUtil
import com.kencur.lezat.utils.invisible
import com.kencur.lezat.utils.show

class MealAdapter(private val mealList: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>(), Filterable {

    private var mealFilterList: List<Meal> = mealList
    private var categoryFilterConstraint: String = ""
    private var areaFilterConstraint: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        with(holder) {
            if (mealFilterList.isEmpty()) {
                binding.itemList.invisible()
                holder.binding.itemList.setOnClickListener(null)
            } else {
                binding.itemList.show()
                with(mealFilterList[position]) {
                    ViewUtil.setMealTitle(strMeal, binding.tvTitle)
                    binding.tvCategory.text = strCategory
                    binding.tvArea.text = objArea.strName

                    Glide.with(context)
                        .load(strMealThumb)
                        .placeholder(ShimmerUtil.getShimmerDrawable())
                        .into(binding.ivMeal)

                    holder.itemView.setOnClickListener {
                        binding.itemList.callOnClick()
                    }

                    holder.binding.itemList.setOnClickListener {
                        context.startActivity(
                            Intent(context, DetailActivity::class.java)
                                .putExtra(DetailActivity.EXTRA_DATA, this)
                        )
                    }

                    if (position > 0)
                        holder.itemView.setBackgroundColor(
                            ContextCompat.getColor(
                                context,
                                android.R.color.white
                            )
                        )
                    else
                        holder.itemView.setBackgroundColor(
                            Color.TRANSPARENT
                        )
                }
            }
        }
    }

    override fun getItemCount(): Int = if (mealFilterList.isEmpty()) 1 else mealFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                mealFilterList =
                    if (categoryFilterConstraint.isNotBlank() && areaFilterConstraint.isNotBlank()) {
                        mealList.filter {
                            it.strCategory.contains(categoryFilterConstraint)
                                    && it.objArea.strName.contains(areaFilterConstraint)
                        }
                    } else if (categoryFilterConstraint.isNotBlank()) {
                        mealList.filter {
                            it.strCategory.contains(categoryFilterConstraint)
                        }
                    } else if (areaFilterConstraint.isNotBlank()) {
                        mealList.filter {
                            it.objArea.strName.contains(areaFilterConstraint)
                        }
                    } else {
                        mealList
                    }

                val filterResults = FilterResults()
                filterResults.values = mealFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mealFilterList = results?.values as List<Meal>
                notifyDataSetChanged()
            }
        }
    }

    fun filterCategory(constraint: String) {
        categoryFilterConstraint = constraint
        filter.filter(null)
    }

    fun filterArea(constraint: String) {
        areaFilterConstraint = constraint
        filter.filter(null)
    }

    inner class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)
}