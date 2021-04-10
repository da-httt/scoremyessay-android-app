package com.example.scoremyessay

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.scoremyessay.IntroSlider.IntroSliderAdapter
import com.example.scoremyessay.Model.IntroSliderModel
import com.example.scoremyessay.databinding.ActivityIntroSliderBinding
import com.example.scoremyessay.databinding.ActivityLoginBinding

class ActivityIntroSlider : AppCompatActivity() {
    private var _binding: ActivityIntroSliderBinding?= null
    private val binding get() = _binding!!

    private lateinit var textIntro: TextView
    private lateinit var text: ViewPager2
    private lateinit var linearLayout: LinearLayout

    private val introSliderAdapter = IntroSliderAdapter(
            listOf(
                    IntroSliderModel(
                            "sunline",
                            "Sunline is the light and energy that comes from sun",
                            R.drawable.anh1     ),
                    IntroSliderModel(
                            "a",
                            "b",
                            R.drawable.anh2
                    ),
                    IntroSliderModel(
                            "c",
                            "d",
                            R.drawable.anh3
                    )


            )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityIntroSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.introSliderViewPager.adapter = introSliderAdapter
    }
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    }
}