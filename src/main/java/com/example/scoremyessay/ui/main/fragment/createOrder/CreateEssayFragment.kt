package com.example.scoremyessay.ui.main.fragment.createOrder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentCreateEssayOptionBinding
import com.example.scoremyessay.ui.main.ActivityMain


class CreateEssayFragment : BaseFragmentWithViewBinding<FragmentCreateEssayOptionBinding>() {

    private val mActivity by lazy { activity as ActivityMain }
    override fun handleTask() {
        initListener()
    }

    private fun initListener() {
        binding.btnEnterEssay.setOnClickListener {
//            mActivity.navigateFragment(R.layout.fragment_create_essay_enter)
            Navigation.findNavController(binding.root).navigate(R.id.actionEnterEssay)
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentCreateEssayOptionBinding =
        FragmentCreateEssayOptionBinding.inflate(
        inflater,
        container,
        false)
}