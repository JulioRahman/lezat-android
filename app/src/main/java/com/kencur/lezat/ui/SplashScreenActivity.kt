package com.kencur.lezat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.rive.runtime.kotlin.RiveDrawable
import app.rive.runtime.kotlin.core.PlayableInstance
import com.kencur.lezat.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lezatAnimation.registerListener(object : RiveDrawable.Listener {
            override fun notifyLoop(animation: PlayableInstance) {}

            override fun notifyPause(animation: PlayableInstance) {}

            override fun notifyPlay(animation: PlayableInstance) {}

            override fun notifyStateChanged(stateMachineName: String, stateName: String) {}

            override fun notifyStop(animation: PlayableInstance) {
                startActivity(
                    Intent(this@SplashScreenActivity, MainActivity::class.java)
                )
                finish()
            }
        })
    }
}