package com.example.scoremyessay


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.scoremyessay.Fragments.Adapter.LoginFragment
import com.example.scoremyessay.Fragments.Adapter.SignUpFragment
import com.example.scoremyessay.Fragments.LoginPagerAdapter
import com.example.scoremyessay.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout

class ActivityLogin : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
//


//    private val tabLayout: TabLayout = findViewById(R.id.tab_layout)
//    private val viewPager: ViewPager = findViewById(R.id.view_pager)

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager


        setUpTabs()

    }

    private fun setUpTabs(){
        val adapter = LoginPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(),"Login")
        adapter.addFragment(SignUpFragment(),"SignUp")
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
}