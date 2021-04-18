package com.example.scoremyessay

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.get
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
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var introSliderViewPager: ViewPager2
    private lateinit var btnNext : Button
    private lateinit var textNextIntro : TextView

    private val introSliderAdapter = IntroSliderAdapter(
            listOf(
                    IntroSliderModel(
                            "Nhanh chóng",
                            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                            R.drawable.image1     ),
                    IntroSliderModel(
                            "Ðáng tin cậy",
                            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                            R.drawable.image2
                    ),
                    IntroSliderModel(
                            "Tiết kiệm chi phí",
                            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                            R.drawable.image3
                    ),
                    IntroSliderModel(
                            "Đội ngũ giáo viên chất lượng",
                            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                            R.drawable.image4
                    )


            )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityIntroSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.introSliderViewPager.adapter = introSliderAdapter

        indicatorsContainer = binding.indicatorsContainers
        introSliderViewPager = binding.introSliderViewPager
        btnNext = binding.btnNext
        textNextIntro = binding.textNextIntro

        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        btnNext.setOnClickListener{
            if(introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount){
                introSliderViewPager.currentItem += 1
            }else{
                //intent chuyen activity
            }
        }
        textNextIntro.setOnClickListener {
            //Intent
        }

    }
    private fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,8,8,8)

        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.indicator_inactive
                        )
                )
                this?.layoutParams = layoutParams
            }
            indicatorsContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index :Int){
        val childCount: Int = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}