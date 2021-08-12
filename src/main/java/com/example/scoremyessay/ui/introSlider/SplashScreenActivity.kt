package com.example.scoremyessay.ui.introSlider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.scoremyessay.R
import com.example.scoremyessay.databinding.ActivitySplashScreenBinding
import com.example.scoremyessay.ui.auth.ActivityLogin

class SplashScreenActivity : AppCompatActivity() {
    lateinit var topAnim: Animation
    lateinit var bottomAnim: Animation
    lateinit var binding: ActivitySplashScreenBinding

    val SPLASH_SCREEN_TIMER = 5000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        topAnim = AnimationUtils.loadAnimation(this,R.anim.splash_top_anim)
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.splash_bottom_anim)

        binding.imgLogo.animation = topAnim
        binding.txtSlogan.animation = bottomAnim

        Handler().postDelayed(Runnable { val intent = Intent(this, ActivityLogin::class.java)
        startActivity(intent)
        finish()}, SPLASH_SCREEN_TIMER.toLong())
    }
}