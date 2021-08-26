package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.databinding.FragmentDetailEssayGradeAndJudgeBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter.ShowCriteriaAdapter
import com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter.ShowExtraResultAdapter

class GradeAndJudgeFragment :
    BaseFragmentWithViewBinding<FragmentDetailEssayGradeAndJudgeBinding>() {

    private val mActivity by lazy {
        activity as ActivityMain
    }

    private val mViewModel by lazy{
        mActivity.viewModel
    }

    private val mAdapterCriteria by lazy { ShowCriteriaAdapter() }
    private val mExtraResult by lazy { ShowExtraResultAdapter() }

    private var a = 0

    override fun handleTask() {
        initView()
    }

    private fun initView() {
        binding.recyclerTieuChi.apply {
            adapter = mAdapterCriteria
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        binding.recyclerKetQuaThem.apply {
            adapter = mExtraResult
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        firstSetup()
    }

    private fun firstSetup() {
        getCurrentEssayByClick().result?.let {
            binding.txtGrade.text = it.grade.toString()
            binding.txtReview.text = it.review
            binding.txtComment.text = it.comment

            it.criteria_results?.let {
                mAdapterCriteria.updateData(it)
            }

            it.extra_results?.let {
                mExtraResult.updateData(it)
            }
        }

//        getCurrentEssayByClick().result?.extra_results

//        binding.cardView6.setOnClickListener {
//            Log.e("TAG", "firstSetup: click")
//            binding.cardView6.resize(20, 30)
//        }

    }

    private fun getCurrentEssayByClick(): OrderItem {
        return mViewModel.getCurrentEssayByClick()
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentDetailEssayGradeAndJudgeBinding =
        FragmentDetailEssayGradeAndJudgeBinding.inflate(
            inflater, container, false
        )
}
