package com.kencur.lezat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kencur.lezat.databinding.FragmentDetailBinding
import com.kencur.lezat.utils.ViewUtil
import com.kencur.lezat.utils.hide

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewUtil.setMealTitle(args.mealArg.strMeal, binding.tvTitle)
        binding.tvCategory.text = args.mealArg.strCategory
        binding.tvArea.text = args.mealArg.strArea
        setInstructions(args.mealArg.strInstructions)

        val bsBehavior = BottomSheetBehavior.from(binding.bsInstructions.bottomSheet)
        binding.barrier.viewTreeObserver.addOnGlobalLayoutListener {
            _binding?.let {
                val peekHeight = binding.root.height - binding.barrier.top - 128
                if (peekHeight - 72 > 72)
                    bsBehavior.setPeekHeight(peekHeight, true)
                bsBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        binding.cardImg.viewTreeObserver.addOnGlobalLayoutListener {
            _binding?.let {
                val height = binding.root.height - binding.cardImg.top
                if (height - 256 > 256)
                    binding.bsInstructions.bottomSheet.layoutParams.height = height
            }
        }

        Glide.with(this)
            .load(args.mealArg.strMealThumb)
            .into(binding.ivMeal)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            binding.bsInstructions.tvInstructions.t13
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
            binding.bsInstructions.tvInstructions.b13
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
}