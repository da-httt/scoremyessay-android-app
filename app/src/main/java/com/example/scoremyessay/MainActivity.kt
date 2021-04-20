package com.example.scoremyessay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scoremyessay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ScoreMyEssay)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}