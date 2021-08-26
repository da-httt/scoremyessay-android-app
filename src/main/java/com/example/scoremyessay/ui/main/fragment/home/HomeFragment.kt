package com.example.scoremyessay.ui.main.fragment.home

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragment
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentHomeBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.utils.extension.convertPriceToUI
import java.math.BigDecimal
import java.math.RoundingMode


class HomeFragment : BaseFragmentWithViewBinding<FragmentHomeBinding>() {

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy{
        mActivity.viewModel
    }

    override fun handleTask() {
        initView()
        initObserver()
    }

    private fun initView() {
        (binding.imgLogo.background as AnimationDrawable).start()
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() {
        mViewModel.mMyStatic.observe(viewLifecycleOwner,{
            if(it.gross < 0 ){
                binding.imgUpDown.setImageResource(R.drawable.ic_drop_down)
            }
            binding.txtTotalOrder.text = it.total_orders.toString()
            binding.txtTotalDone.text = it.total_done.toString()
            binding.txtMonthlyPayment.text = "Mức chi trung bình: ${it.monthly_payment.toString().convertPriceToUI()}"
            binding.txtGross.text = "So với tháng trước: ${BigDecimal(it.gross).setScale(2, RoundingMode.HALF_EVEN)}"
            binding.txtTotalPayment.text = it.total_payment.toString().convertPriceToUI()
        } )
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
}