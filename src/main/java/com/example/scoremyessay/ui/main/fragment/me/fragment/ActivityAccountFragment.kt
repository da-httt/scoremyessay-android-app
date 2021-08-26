package com.example.scoremyessay.ui.main.fragment.me.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentActivityAccountBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.utils.extension.convertPriceToUI
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToLong

class ActivityAccountFragment : BaseFragmentWithViewBinding<FragmentActivityAccountBinding>() {

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy{
        mActivity.viewModel
    }

    override fun handleTask() {
        initObserver()
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() {
        mViewModel.mMyStatic.observe(viewLifecycleOwner,{
            binding.txtMonthlyOrder.text = it.monthly_orders.toString()
            binding.txtMonthlyPayment.text = it.monthly_payment.toString().convertPriceToUI()
            binding.txtTotalOrders.text = it.total_orders.toString()
            binding.txtGross.text = BigDecimal(it.gross).setScale(2, RoundingMode.HALF_EVEN).toString() + " %"
            binding.txtTotalDone.text = it.total_done.toString()
            binding.txtTotalPayment.text = it.total_payment.toString().convertPriceToUI()
        } )
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentActivityAccountBinding = FragmentActivityAccountBinding.inflate(inflater, container, false)
}