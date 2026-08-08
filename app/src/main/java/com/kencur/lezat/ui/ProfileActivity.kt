package com.kencur.lezat.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.kencur.lezat.databinding.ActivityProfileBinding
import com.kencur.lezat.utils.ShimmerUtil

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, 0, 0, systemBars.bottom)
            binding.toolbar.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top
            }
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        Glide.with(this)
            .load("https://s.gravatar.com/avatar/ef3f66323aaa34bb52ba371c41ae037f?s=80")
            .placeholder(ShimmerUtil.getShimmerDrawable())
            .into(binding.imgAvatar)

        binding.card1.setOnClickListener {
            startActivity(
                newInstagramProfileIntent(
                    packageManager,
                    "https://www.instagram.com/rahman_julio"
                )
            )
        }

        binding.card2.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://github.com/JulioRahman/lezat-android")
                }
            )
        }

        binding.card3.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://www.themealdb.com/")
                }
            )
        }
    }

    private fun newInstagramProfileIntent(pm: PackageManager, url: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                val username = url.substring(url.lastIndexOf("/") + 1)
                intent.data = Uri.parse("http://instagram.com/_u/$username")
                intent.setPackage("com.instagram.android")
                return intent
            }
        } catch (ignored: NameNotFoundException) {}
        intent.data = Uri.parse(url)
        return intent
    }
}