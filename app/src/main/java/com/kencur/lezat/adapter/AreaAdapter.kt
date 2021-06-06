package com.kencur.lezat.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.kencur.lezat.R
import com.kencur.lezat.databinding.ItemAreaBinding
import com.kencur.lezat.model.Area

class AreaAdapter(
    private val areaList: List<Area>,
    private val adapter: MealAdapter,
    private val defaultTextColors: ColorStateList
) :
    RecyclerView.Adapter<AreaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAreaBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        with(holder) {
            with(areaList[position]) {
                binding.tvArea.text = strName

                val shimmer = Shimmer.ColorHighlightBuilder().build()

                val shimmerDrawable = ShimmerDrawable().apply {
                    setShimmer(shimmer)
                }

                Glide.with(context)
                    .load("https://www.countryflags.io/${strCode.lowercase()}/flat/64.png")
                    .placeholder(shimmerDrawable)
                    .into(binding.ivArea)

                binding.root.setOnClickListener {
                    if (!boolSelected) {
                        areaList.filter { it.boolSelected }.forEach { it.boolSelected = false }
                        boolSelected = true
                        adapter.filterArea(strName)
                    } else {
                        boolSelected = false
                        adapter.filterArea("")
                    }

                    notifyDataSetChanged()
                }

                setSelected(context, boolSelected, binding.bgLayout, binding.tvArea)
            }
        }
    }

    private fun setSelected(
        context: Context,
        selected: Boolean,
        bg: ConstraintLayout,
        tv: TextView
    ) {
        if (selected) {
            bg.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            tv.setTextColor(ContextCompat.getColor(context, R.color.colorOnPrimary))
        } else {
            bg.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGray))
            tv.setTextColor(defaultTextColors)
        }
    }

    override fun getItemCount(): Int = areaList.size

    inner class ViewHolder(val binding: ItemAreaBinding) : RecyclerView.ViewHolder(binding.root)
}