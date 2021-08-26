package com.example.scoremyessay.utils

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.agrawalsuneet.dotsloader.loaders.AllianceLoader
import com.example.scoremyessay.R
import com.example.scoremyessay.databinding.ItemLoadingBinding
import com.example.scoremyessay.ui.main.ActivityMain

class MyCustomDialog : DialogFragment() {
    private lateinit var binding: ItemLoadingBinding

    private val mActivity by lazy {
        activity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = ItemLoadingBinding.inflate(layoutInflater)

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

    }

    companion object{
        val TAG = "Loading"
        fun newInstance() = MyCustomDialog()
    }
}