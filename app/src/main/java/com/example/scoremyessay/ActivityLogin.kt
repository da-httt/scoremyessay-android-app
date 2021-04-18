package com.example.scoremyessay


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.scoremyessay.fragments.LoginFragment
import com.example.scoremyessay.fragments.SignUpFragment
import com.example.scoremyessay.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout

class ActivityLogin : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

//    private val tabLayout: TabLayout = findViewById(R.id.tab_layout)
//    private val viewPager: ViewPager = findViewById(R.id.view_pager)

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var imageBG : ImageView
    private lateinit var fragmentLayout : ConstraintLayout


    private val manager = supportFragmentManager
    private lateinit var transaction : FragmentTransaction
    private val loginFragment = LoginFragment()
    private val signUpFragment = SignUpFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageBG = binding.imgBG
        fragmentLayout = binding.fragmentLayout

//      show login fragment
        showFirstFragment()
//      setUpTabs()

    }
    private fun showFirstFragment()
    {
        transaction = manager.beginTransaction()
        transaction.add(R.id.fragmentLayout, loginFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun loadFragmentLogin()
    {
        transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, loginFragment).addToBackStack(null).commit()
    }
    fun loadFragmentSignUp()
    {
        transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, signUpFragment).addToBackStack(null).commit()
    }



//    private fun setUpTabs(){
//        val adapter = LoginPagerAdapter(supportFragmentManager)
//        adapter.addFragment(LoginFragment(),"Login")
//        adapter.addFragment(SignUpFragment(),"SignUp")
//        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//        viewPager.adapter = adapter
//        tabLayout.setupWithViewPager(viewPager)
//    }

}