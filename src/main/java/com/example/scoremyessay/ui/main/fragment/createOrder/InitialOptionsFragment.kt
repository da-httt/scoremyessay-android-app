package com.example.scoremyessay.ui.main.fragment.createOrder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentCreateOrderInitialBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.createOrder.dialog.ShowThanhToanDialog
import com.example.scoremyessay.utils.extension.convertPriceToUI

// TODO: Rename parameter arguments, choose names that match

class InitialOptionsFragment : BaseFragmentWithViewBinding<FragmentCreateOrderInitialBinding>() {

    private val mActivity by lazy { activity as ActivityMain }

    private val mViewModel by lazy { mActivity.viewModel }

    private val mEmptyArray by lazy { mutableListOf("") }

    private val spinnerAdapter by lazy {

        ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            mEmptyArray
        )
    }

    override fun handleTask() {
        firstSetUp()
        initView()
        initListener()
        initObserve()
    }

    private fun firstSetUp() {
        binding.txtPrice.text = mViewModel.getPriceOfPostOrder().toString().convertPriceToUI()

        Log.e("TAG", "firstSetUp: ${mViewModel.getPriceOfPostOrder().toString().convertPriceToUI()}")
        binding.cbOp4.isChecked = true
//        mViewModel.getPriceOfPostOrder()
    }

    private fun initView() {
        Log.e("TAG", "initView: aaaaa", )
    }

    private fun initObserve() {
        mViewModel.orderTypeAPI.observe(viewLifecycleOwner, {
            mViewModel.currentLevelIndex.value = 0
            spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding.spinner.adapter = spinnerAdapter

            mViewModel.getTypeIDbyTypeName(mEmptyArray[0])
//            mViewModel.getPriceOfPostOrder()
        })

        mViewModel.currentLevelIndex.observe(viewLifecycleOwner, {
            spinnerAdapter.clear()
            spinnerAdapter.addAll(*mViewModel.getListTypeNameByLevelID()!!.toTypedArray())
            spinnerAdapter.notifyDataSetChanged()

            mViewModel.getTypeIDbyTypeName(mEmptyArray[0])
        })

        mViewModel.mPostOrderRequest.observe(viewLifecycleOwner, {
            binding.txtPrice.text = mViewModel.getPriceOfPostOrder().toString().convertPriceToUI()
            Log.e("TAG", "aa: ${mViewModel.getPriceOfPostOrder().toString().convertPriceToUI()}")
        })
    }

    private fun initListener() {

        binding.btnThanhToan.setOnClickListener {
            val action = InitialOptionsFragmentDirections.actionCreateOptionsToPaymentFragment()
            controller.navigate(action)
//            mViewModel.postOrderToServer()
        }

        binding.btnHuy.setOnClickListener {

        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mViewModel.getTypeIDbyTypeName(mEmptyArray[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }
        }

        binding.cbOp2.setOnCheckedChangeListener { view, isCheck ->
            if(view.isPressed){
                mViewModel.addOptionList(2)
            }
        }

        binding.cbOp3.setOnCheckedChangeListener {view, isCheck ->
            if(view.isPressed){
                mViewModel.addOptionList(3)
            }
        }

        binding.groupParent0.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.cbOp4.id -> {
                    mViewModel.addOptionList(4)
                    setViewWhenRadioChange(0, 4)
                }
                binding.cbOp5.id -> {
                    mViewModel.addOptionList(5)
                    setViewWhenRadioChange(0, 5)
                }
                binding.cbOp6.id -> {
                    mViewModel.addOptionList(6)
                    setViewWhenRadioChange(0, 6)
                }
                binding.cbOp7.id -> {
                    mViewModel.addOptionList(7)
                    setViewWhenRadioChange(0, 7)
                }
            }
        }

        binding.groupParent1.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.cbOp8.id -> {
                    mViewModel.addOptionList(8)
                    setViewWhenRadioChange(1, 8)
                }
                binding.cbOp9.id -> {
                    mViewModel.addOptionList(9)
                    setViewWhenRadioChange(1, 9)
                }
                binding.cbOp10.id -> {
                    mViewModel.addOptionList(10)
                    setViewWhenRadioChange(1, 10)
                }
                binding.cbOp11.id -> {
                    mViewModel.addOptionList(11)
                    setViewWhenRadioChange(1, 11)
                }
            }
        }

        binding.groupParent2.setOnCheckedChangeListener { _, id ->
            when (id) {
                binding.cbOp12.id -> {
                    mViewModel.addOptionList(12)
                    setViewWhenRadioChange(2, 12)
                }
            }
        }

        binding.groupLevel0.setOnClickListener {
            mViewModel.currentLevelIndex.value = 0
        }

        binding.groupLevel1.setOnClickListener {
            mViewModel.currentLevelIndex.value = 1
        }
    }

    private fun setViewWhenRadioChange(group: Int, itemClick: Int) {
        when (group) {
            0 -> {
                binding.groupParent1.setOnCheckedChangeListener(null)
                binding.groupParent2.setOnCheckedChangeListener(null)
                binding.groupParent1.clearCheck()
                binding.groupParent2.clearCheck()
                initListener()
            }
            1 -> {
                binding.groupParent0.setOnCheckedChangeListener(null)
                binding.groupParent2.setOnCheckedChangeListener(null)
                binding.groupParent0.clearCheck()
                binding.groupParent2.clearCheck()
                initListener()
            }
            2 -> {
                binding.groupParent1.setOnCheckedChangeListener(null)
                binding.groupParent0.setOnCheckedChangeListener(null)
                binding.groupParent0.clearCheck()
                binding.groupParent1.clearCheck()
                initListener()
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentCreateOrderInitialBinding =
        FragmentCreateOrderInitialBinding.inflate(inflater, container, false)
}