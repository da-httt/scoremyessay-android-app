package com.example.scoremyessay.ui.main.fragment.createOrder.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.scoremyessay.R
import com.example.scoremyessay.databinding.DialogPaymentAccountBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.utils.Resource
import com.example.scoremyessay.utils.extension.convertPriceToUI

class ShowThanhToanDialog :DialogFragment(){
    private lateinit var binding : DialogPaymentAccountBinding

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy {
        mActivity.viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogPaymentAccountBinding.inflate(layoutInflater)

        handleTask()
        return AlertDialog.Builder(mActivity).apply {
            setView(binding.root)
        }.create()
    }

    override fun onStart() {
        super.onStart()
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog?.window
        val wlp = window?.attributes
        wlp?.let {
            it.gravity = Gravity.CENTER
            it.width = WindowManager.LayoutParams.MATCH_PARENT
            it.height = WindowManager.LayoutParams.WRAP_CONTENT
            it.windowAnimations = R.style.dialogAnimation
        }
        val inset = InsetDrawable(ColorDrawable(Color.TRANSPARENT), 90)
        window?.attributes = wlp
        window?.setBackgroundDrawable(inset)
    }

    private fun handleTask() {
        requestToServer()

        initView()
        initListener()
        initObserve()
    }

    private fun initObserve() {
        mViewModel.mMyInformation.observe( mActivity, {
            it.creditCard?.let { creditCard ->
                binding.txtSoTK.text = creditCard.account_no
                binding.txtNameTK.text = creditCard.provider
                binding.txtBalance.text = creditCard.balance.toString().convertPriceToUI()
                binding.txtPrice.text = mViewModel.getPriceOfPostOrder().toString().convertPriceToUI()
                binding.txtTotal.text = (creditCard.balance - mViewModel.getPriceOfPostOrder()).toString().convertPriceToUI()
            }
        })
    }

    private fun requestToServer() {
        mViewModel.fetchCreditCardAPI()
    }

    private fun initView() {
    }

    private fun initListener() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnOk.setOnClickListener {
            mViewModel.postOrderToServer(true)
            dismiss()
        }
    }

    companion object{
        fun newInstance(): DialogFragment{
            return ShowThanhToanDialog()
        }
    }
}