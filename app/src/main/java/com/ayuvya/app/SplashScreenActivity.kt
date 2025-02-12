package com.ayuvya.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.ayuvya.app.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding layout
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply the fade-in animation to the logo and tagline
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.logo.startAnimation(fadeInAnimation)
        binding.tagline.startAnimation(fadeInAnimation)

        // Wait for the splash screen to finish and navigate to the main activity
        Handler().postDelayed({
            // Start MainActivity after 2.5 seconds
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the splash screen activity
        }, 2500) // Delay time before transitioning
    }
}
