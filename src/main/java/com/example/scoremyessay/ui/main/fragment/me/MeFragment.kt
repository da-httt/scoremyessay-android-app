package com.example.scoremyessay.ui.main.fragment.me

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentMeBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.me.adapter.AccountFragmentPagerAdapter
import com.example.scoremyessay.ui.main.fragment.viewDetail.adapter.DetailEssayPagerAdapter
import com.example.scoremyessay.utils.extension.convertBase64ToBitmap
import com.google.android.material.tabs.TabLayoutMediator


class MeFragment : BaseFragmentWithViewBinding<FragmentMeBinding>() {

    val mActivity by lazy { activity as ActivityMain }
    val mViewModel by lazy { mActivity.viewModel }

    override fun handleTask() {
        initView()
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding.showInformation.setOnClickListener {
            binding.viewPager.setCurrentItem(1, true)
        }
    }

    private fun initView() {
        setupTabs()
    }

    private fun setupTabs() {
        val adapter = AccountFragmentPagerAdapter(childFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Hoạt động của tôi"
                1 -> tab.text = "Thông tin tài khoản"
            }
        }.attach()
    }

    private fun initObserver() {
        mViewModel.mMyInformation.observe(viewLifecycleOwner, {
            binding.txtName.text = it.info.name
            binding.imgAvatar.setImageBitmap(it.avatar?.image_base64?.convertBase64ToBitmap())
        })
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentMeBinding = FragmentMeBinding.inflate(inflater, container, false)
}