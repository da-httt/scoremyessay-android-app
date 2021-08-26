package com.example.scoremyessay.utils

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
import com.example.scoremyessay.databinding.DialogThongBaoBinding
import com.example.scoremyessay.ui.main.ActivityMain

class CustomDialog: DialogFragment() {
    private lateinit var binding : DialogThongBaoBinding

    private val mActivity by lazy {
        activity as ActivityMain
    }
    private val mViewModel by lazy {
        mActivity.viewModel
    }

    private var isError: Boolean? = null
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isError = it.getBoolean(ARG_IS_ERROR)
            message = it.getString(ARG_MESSAGE)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogThongBaoBinding.inflate(layoutInflater)

        handleTask()

        return AlertDialog.Builder(mActivity).apply {
            setView(binding.root)
        }.create()
    }

    private fun handleTask() {
        if(isError == true){
            binding.imgIcon.setImageResource(R.drawable.ic_error)
//            binding.txtMessage.settext
        }

        binding.btnOk.setOnClickListener {
            dismiss()
        }

        binding.txtMessage.text = message
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

    companion object{
        const val TAG = "myDialog"
        private const val ARG_IS_ERROR = "arg_title"
        private const val ARG_MESSAGE = "arg_message"

        fun newInstance( message: String, isError: Boolean = false) = CustomDialog().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_IS_ERROR, isError)
                putString(ARG_MESSAGE, message)
            }
        }
    }
}