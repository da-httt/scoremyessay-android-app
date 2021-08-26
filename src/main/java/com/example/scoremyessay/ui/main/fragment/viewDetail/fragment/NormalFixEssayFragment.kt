package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentNormalFixEssayBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter.ShowCriteriaAdapter
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter.ShowExtraResultAdapter
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter.SpellErrorRecyclerViewAdapter

class NormalFixEssayFragment : BaseFragmentWithViewBinding<FragmentNormalFixEssayBinding>() {

    private val mActivity by lazy { activity as ActivityMain }

    private val mViewModel by lazy { mActivity.viewModel}

    override fun handleTask() {
        init()
    }

    private fun init() {
        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener{
            mViewModel.getBackComment()
        }
        binding.btnNext.setOnClickListener{
            mViewModel.getNextComment()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() {
        mViewModel.mCurrentComment.observe(viewLifecycleOwner, {
            binding.txtErrorSentence.text = "#${it.sentence_index}: ${it.sentence}"
            binding.txtFixSentence.text = it.comment
        })
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentNormalFixEssayBinding = FragmentNormalFixEssayBinding.inflate(
        inflater, container, false
    )
}