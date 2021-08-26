package com.example.scoremyessay.ui.main.fragment.createOrder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.databinding.FragmentCreateEssayOptionBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.utils.CustomDialog


class CreateEssayFragment : BaseFragmentWithViewBinding<FragmentCreateEssayOptionBinding>() {

    private val mActivity by lazy { activity as ActivityMain }
    private val mViewModel by lazy { mActivity.viewModel}

    private var isObserve = false
    private var isObserveImage= false

    override fun handleTask() {
        firstSetup()

        initView()
        initObserver()
        initListener()
    }

    private fun firstSetup() {
        mViewModel.mPostOrderRequest
    }

    private fun initView() {
        mViewModel.mPostOrderRequest
    }

    private fun initObserver() {
        mViewModel.mPostOrderRequest.observe(viewLifecycleOwner, {
            if(!isObserve){
                isObserve = true
                return@observe
            }
            if(it.essay.content.isNotEmpty()){
                binding.imgContent.setImageResource(R.drawable.ic_green_tick)
            }
        })

        mViewModel.mPickImage.observe(viewLifecycleOwner, {
            if(!isObserveImage){
                isObserveImage = true
                return@observe
            }
            it?.let {
                binding.imgImage.setImageResource(R.drawable.ic_green_tick)
            }
        })
    }

    private fun initListener() {
        binding.btnEnterEssay.setOnClickListener {
            mActivity.navigateFragment(R.id.actionEnterEssay)
        }
        binding.imgBack.setOnClickListener{
            Navigation.findNavController(binding.root).popBackStack()
        }
        binding.btnAddImage.setOnClickListener {
            isObserveImage = true
            val action = CreateEssayFragmentDirections.actionActionCreateEssayToAddImageFragment()
            controller.navigate(action)
        }
        binding.btnContinue.setOnClickListener {
            val title = binding.txtTitle.text?.trim()

            if (title.isNullOrEmpty()){
                binding.txtTitle.error = "Tên đề bài không được rỗng"
                binding.txtTitle.requestFocus()
            }else if(mViewModel.mPostOrderRequest.value?.essay?.content.isNullOrEmpty()){
                CustomDialog.newInstance( "Bạn phải nhập nội dung cho bài viết", true).show(childFragmentManager, CustomDialog.TAG)
                binding.imgContent.setImageResource(R.drawable.ic_infor_red)
            }
            else{
                mViewModel.mPostOrderRequest.value?.essay?.title = title.toString()

                val action = CreateEssayFragmentDirections.actionActionCreateEssayToCreateOptions()
                controller.navigate(action)
            }
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