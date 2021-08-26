package com.example.scoremyessay.ui.main.fragment.me.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentDetailAccountBinding
import com.example.scoremyessay.ui.main.ActivityMain

class DetailAccountFragment : BaseFragmentWithViewBinding<FragmentDetailAccountBinding>() {

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy{
        mActivity.viewModel
    }

    override fun handleTask() {
        initView()
        initObserve()
    }

    @SuppressLint("SetTextI18n")
    private fun initObserve() {
        mViewModel.mMyInformation.observe(viewLifecycleOwner, {
            binding.txtBirthday.text = it.info.date_of_birth
            binding.txtGmail.text = it.email
            when(it.info.gender_id){
                0 -> {
                    binding.txtMale.text = "None"
                }
                1 ->{
                    binding.txtMale.text = "Male"
                }
                2 -> {
                    binding.txtMale.text = "Female"
                }
                3 ->{
                    binding.txtMale.text = "Others"
                }
            }
        })
    }

    private fun initView() {

    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentDetailAccountBinding = FragmentDetailAccountBinding.inflate(inflater, container, false)
}