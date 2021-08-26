package com.example.scoremyessay.ui.main.fragment.showEssay


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentViewEssayNaviBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.showEssay.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class EssayFragment : BaseFragmentWithViewBinding<FragmentViewEssayNaviBinding>(), View.OnTouchListener {

    private val mActivity by lazy {
        activity as ActivityMain
    }

    override fun handleTask() {
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Waiting"
                1 -> tab.text = "Ongoing"
                2 -> tab.text = "Done"
                3 -> tab.text = "Cancel"
            }
        }.attach()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentViewEssayNaviBinding =
        FragmentViewEssayNaviBinding.inflate(inflater, container, false)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        return when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e("TAG", "onTouch: xiong" )
                mActivity.binding.fabButton.visibility = View.INVISIBLE
                mActivity.binding.bottomAppBar.visibility = View.INVISIBLE
                true
            }
            MotionEvent.ACTION_UP ->{
                mActivity.binding.fabButton.visibility = View.VISIBLE
                mActivity.binding.bottomAppBar.visibility = View.VISIBLE
                true
            }
            else -> true
        }
    }
}
