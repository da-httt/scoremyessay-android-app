package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentAIFixEssayBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter.SpellErrorRecyclerViewAdapter
import java.math.BigDecimal
import java.math.RoundingMode

class AIFixEssayFragment : BaseFragmentWithViewBinding<FragmentAIFixEssayBinding>() {

    private val mActivity by lazy { activity as ActivityMain }

    private val mViewModel by lazy { mActivity.viewModel}
    private val mAdapter by lazy { SpellErrorRecyclerViewAdapter()}

    override fun handleTask() {
        init()
    }

    private fun init() {
        initView()
        initObserver()
    }

    private fun initView() {
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() {
        mViewModel.mSpellError.observe(viewLifecycleOwner, {

            binding.txtAverageOfSentence.text = BigDecimal(it.average_sentence_length).setScale(2, RoundingMode.HALF_EVEN).toString()
            binding.txtNumError.text = it.num_errors.toString()
            binding.txtNumOfSentence.text = it.number_of_sentences.toString()

            mAdapter.updateData(it.spelling_errors)
        })
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentAIFixEssayBinding = FragmentAIFixEssayBinding.inflate(
        inflater, container, false
    )
}