package com.example.scoremyessay.ui.main.fragment.viewDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentViewDetailBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.viewDetail.adapter.DetailEssayPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewDetailFragment : BaseFragmentWithViewBinding<FragmentViewDetailBinding>() {

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy{
        mActivity.viewModel
    }

//    private var comment : CommentResult?= null

    override fun handleTask() {
        firstSetup()
        initView()

        initObserver()
    }

    private fun initObserver() {

    }

    private fun initView() {
        setupTabs()
    }

    private fun firstSetup() {
        binding.txtEssayContent.text = mViewModel.getCurrentEssayByClick().essay.content
        binding.txtEssayTitle.text = mViewModel.getCurrentEssayByClick().essay.title
    }

    private fun setupTabs() {
        val adapter = DetailEssayPagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Điểm số và đánh giá"
                1 -> tab.text = "Sửa lỗi tự động"
                2 -> tab.text = "Sửa lỗi"
                3 -> tab.text = "Phản hồi"
            }
        }.attach()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentViewDetailBinding = FragmentViewDetailBinding.inflate(inflater, container, false)
}