package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.data.model.orderAttribute.result.rating.OrderRating
import com.example.scoremyessay.databinding.FragmentDetailEssayFeedBackBinding
import com.example.scoremyessay.ui.main.ActivityMain
import java.util.*


class FeedBackFragment : BaseFragmentWithViewBinding<FragmentDetailEssayFeedBackBinding>(),
    View.OnClickListener {

    private val mActivity by lazy { activity as ActivityMain }
    private var mStarNumber = -1

    private val mViewModel by lazy {
        mActivity.viewModel
    }

    override fun handleTask() {
        init()
    }

    private fun init() {
        initListener()
        initObserve()
    }

    override fun onStart() {
        Log.e("TAG", "onStart:123 ", )
        super.onStart()
        enableView(true)
    }

    override fun onResume() {
        super.onResume()
        Log.e("TAG", "onStart:12344455 ", )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "onStart:123444 ", )
    }

    private fun initObserve() {
        mViewModel.mTeacherAvatar.observe(viewLifecycleOwner, { userAvatar ->

            userAvatar.image_base64?.let{
                val byteString = Base64.decode(it, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(byteString,0, byteString.size)

                binding.imgAvatar.setImageBitmap(bitmap)
            }
        })

        mViewModel.mOrderRating.observe(viewLifecycleOwner, {
            if(it.stars == 0){
                changeStarFromWhiteToYellow(0)
                binding.txtFeedBack.setText("")

                enableView(true)
            }else{
                changeStarFromWhiteToYellow(it.stars)
                binding.txtFeedBack.setText(it.comment)

                enableView(false)
            }
        })

        mViewModel.mPostStatus.observe(viewLifecycleOwner, {
            if(it.getContentIfNotHandled() == true){
                //show dialog update thanh cong
                Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show()
                enableView(false)
            }
        })
    }

    private fun enableView(isEnable : Boolean){
        binding.imgStar1.isEnabled = isEnable
        binding.imgStar2.isEnabled = isEnable
        binding.imgStar3.isEnabled = isEnable
        binding.imgStar4.isEnabled = isEnable
        binding.imgStar5.isEnabled = isEnable

        binding.txtFeedBack.isEnabled = isEnable
        binding.btnSendFeedBack.isEnabled = isEnable
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListener() {
        binding.txtFeedBack.setOnTouchListener { v, event ->
            if (binding.txtFeedBack.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        return@setOnTouchListener true
                    }
                }
            }
            return@setOnTouchListener false
        }

        binding.imgStar1.setOnClickListener(this)
        binding.imgStar2.setOnClickListener(this)
        binding.imgStar3.setOnClickListener(this)
        binding.imgStar4.setOnClickListener(this)
        binding.imgStar5.setOnClickListener(this)

        binding.btnSendFeedBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgStar1 -> {
                changeStarFromWhiteToYellow(1)
            }
            binding.imgStar2 -> {
                changeStarFromWhiteToYellow(2)
            }
            binding.imgStar3 -> {
                changeStarFromWhiteToYellow(3)
            }
            binding.imgStar4 -> {
                changeStarFromWhiteToYellow(4)
            }
            binding.imgStar5 -> {
                changeStarFromWhiteToYellow(5)
            }
            binding.btnSendFeedBack ->{
                if(mStarNumber == -1){
//                    Toast.makeText(context, "Mời bạn ")
                    return
                }
                mViewModel.postOrderRatingByOrderID(OrderRating(
                    comment = binding.txtFeedBack.text.toString(),
                    stars = mStarNumber
                ))
            }
        }
    }

    private fun changeStarFromWhiteToYellow(numberOfStar: Int){
        mStarNumber = numberOfStar
        when(numberOfStar){
            0 ->{
                binding.imgStar1.setImageResource(R.drawable.ic_star_white)
                binding.imgStar2.setImageResource(R.drawable.ic_star_white)
                binding.imgStar3.setImageResource(R.drawable.ic_star_white)
                binding.imgStar4.setImageResource(R.drawable.ic_star_white)
                binding.imgStar5.setImageResource(R.drawable.ic_star_white)
            }
            1 -> {
                binding.imgStar1.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar2.setImageResource(R.drawable.ic_star_white)
                binding.imgStar3.setImageResource(R.drawable.ic_star_white)
                binding.imgStar4.setImageResource(R.drawable.ic_star_white)
                binding.imgStar5.setImageResource(R.drawable.ic_star_white)
            }

            2 -> {
                binding.imgStar1.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar2.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar3.setImageResource(R.drawable.ic_star_white)
                binding.imgStar4.setImageResource(R.drawable.ic_star_white)
                binding.imgStar5.setImageResource(R.drawable.ic_star_white)
            }

            3 -> {
                binding.imgStar1.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar2.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar3.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar4.setImageResource(R.drawable.ic_star_white)
                binding.imgStar5.setImageResource(R.drawable.ic_star_white)
            }

            4 -> {
                binding.imgStar1.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar2.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar3.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar4.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar5.setImageResource(R.drawable.ic_star_white)
            }

            5 -> {
                binding.imgStar1.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar2.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar3.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar4.setImageResource(R.drawable.ic_star_yellow)
                binding.imgStar5.setImageResource(R.drawable.ic_star_yellow)
            }
        }
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentDetailEssayFeedBackBinding = FragmentDetailEssayFeedBackBinding.inflate(
        inflater, container, false
    )
}