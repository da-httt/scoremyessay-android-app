package com.example.scoremyessay.ui.main.fragment.showEssay


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentViewEssayNaviBinding
import com.example.scoremyessay.ui.main.fragment.showEssay.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class EssayFragment : BaseFragmentWithViewBinding<FragmentViewEssayNaviBinding>() {

    override fun handleTask() {
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
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
}
