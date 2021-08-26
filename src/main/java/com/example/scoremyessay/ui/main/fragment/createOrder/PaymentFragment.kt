package com.example.scoremyessay.ui.main.fragment.createOrder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentPaymentBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.createOrder.adapter.RecyclerThanhToanAdapter
import com.example.scoremyessay.ui.main.fragment.createOrder.dialog.ShowThanhToanDialog
import com.example.scoremyessay.utils.CustomDialog
import com.example.scoremyessay.utils.Resource
import com.example.scoremyessay.utils.extension.convertPriceToUI

class PaymentFragment : BaseFragmentWithViewBinding<FragmentPaymentBinding>() {

    private val mAdapter by lazy { RecyclerThanhToanAdapter() }

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy {
        mActivity.viewModel
    }

    override fun handleTask() {
        initView()
        initListener()
        initObserver()
    }

    private fun initObserver() {
        mViewModel.mPaymentStatus.observe(mActivity, {
            when(it){
                is Resource.Success -> {
                    CustomDialog.newInstance("Bài viết của bạn đã thanh toán thành công và đang tìm giáo viên chấm !!").show(childFragmentManager, CustomDialog.TAG)
                    val action =  PaymentFragmentDirections.actionPaymentFragmentToActionHome()
                    controller.navigate(action)
                }
            }
        })
    }

    private fun initListener() {

        binding.btnCreditCard.setOnClickListener {
            ShowThanhToanDialog.newInstance().show(childFragmentManager, "Thanh toán")
        }

        binding.btnMoMo.setOnClickListener {

        }
    }

    private fun initView() {
        binding.recyclerOptions.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        mAdapter.updateData(mViewModel.getListThanhToanOption())

        binding.txtPrice.text = mViewModel.getPriceOfPostOrder().toString().convertPriceToUI()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentPaymentBinding = FragmentPaymentBinding.inflate(inflater, container, false)
}