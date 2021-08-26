package com.example.scoremyessay.ui.main.fragment.viewDetail.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.DialogFragment
import com.example.scoremyessay.data.model.user.AccountData
import com.example.scoremyessay.databinding.DialogShowTeacherInforBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.utils.extension.convertBase64ToBitmap

class ShowTeacherDialog: DialogFragment() {

    private lateinit var binding : DialogShowTeacherInforBinding
    private val mActivity by lazy {
        activity as ActivityMain
    }
    private val mViewModel by lazy {
        mActivity.viewModel
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogShowTeacherInforBinding.inflate(layoutInflater)

        handleTask()

        return AlertDialog.Builder(mActivity).apply {
            setView(binding.root)
        }.create()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    private fun handleTask() {
        initView()
        initObserve()
    }

    private fun initObserve() {
        mViewModel.mTeacherAvatar.observe(viewLifecycleOwner, { userAvatar ->
            userAvatar.image_base64?.let{
                binding.circleImageView.setImageBitmap(it.convertBase64ToBitmap())
            }
        })
    }

    private fun initView() {
        mViewModel.getTeacherInformation()?.let {
            binding.txtBirthday.text = it.date_of_birth
            binding.txtGmail.text = it.email
            //binding.txtJob.text =
            binding.txtName.text = it.name
        }
    }

    companion object{
        fun newInstance(): DialogFragment{
            return ShowTeacherDialog()
        }
    }
}