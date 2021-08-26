package com.example.scoremyessay.ui.main.fragment.createOrder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.ActivityMainBinding
import com.example.scoremyessay.databinding.FragmentAddImageBinding
import com.example.scoremyessay.databinding.FragmentCreateEssayOptionBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.createOrder.dialog.ShowPickPictureDialog
import com.example.scoremyessay.utils.extension.convertBase64ToBitmap


class AddImageFragment : BaseFragmentWithViewBinding<FragmentAddImageBinding>() {

    val mActivity by lazy { activity as ActivityMain }
    val mViewModel by lazy { mActivity.viewModel }

    private var isObserve = false

    override fun handleTask() {

        init()
    }

    private fun init(){
        initListener()
        initObserve()
    }

    private fun initListener() {
        binding.btnUpload.setOnClickListener {
            isObserve = true
            ShowPickPictureDialog.newInstance().show(childFragmentManager, "Pick Picture")
        }
    }

    private fun initObserve() {
        mViewModel.mPickImage.observe(viewLifecycleOwner, {
            if(isObserve){
                binding.imgEssay.setImageBitmap(it)
            }
        })
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentAddImageBinding = FragmentAddImageBinding.inflate(inflater, container, false)
}