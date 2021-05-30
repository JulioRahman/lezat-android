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
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.kencur.lezat.R
import com.kencur.lezat.databinding.ItemMealBinding
import com.kencur.lezat.model.Meal
import com.kencur.lezat.ui.DetailActivity
import com.kencur.lezat.utils.ViewUtil

class MealAdapter(private val mealList: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>(), Filterable {

    private var mealFilterList: List<Meal> = mealList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        with(holder) {
            with(mealFilterList[position]) {
                ViewUtil.setMealTitle(strMeal, binding.tvTitle)
                binding.tvCategory.text = strCategory
                binding.tvArea.text = strArea

                val shimmer = Shimmer.ColorHighlightBuilder().build()

                val shimmerDrawable = ShimmerDrawable().apply {
                    setShimmer(shimmer)
                }

                Glide.with(context)
                    .load(strMealThumb)
                    .placeholder(shimmerDrawable)
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
                            R.color.colorBackground
                        )
                    )
                else
                    holder.itemView.setBackgroundColor(
                        Color.TRANSPARENT
                    )
            }
        }
    }

    override fun getItemCount(): Int = mealFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val str = constraint.toString()
                val resultList = ArrayList<Meal>()
                for (row in mealList) {
                    if (row.strCategory.contains(str) || row.strArea.contains(str))
                        resultList.add(row)
                }
                mealFilterList = resultList.toList()
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

    inner class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)
}