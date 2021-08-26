package com.example.scoremyessay.ui.main.fragment.showEssay.doneEssay

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseFragmentWithViewBinding
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.databinding.FragmentViewEssayDoneBinding
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.ui.main.fragment.showEssay.EssayFragmentDirections
import com.example.scoremyessay.viewModel.MainViewModel
import com.example.scoremyessay.ui.main.fragment.showEssay.adapter.EssayAdapter
import com.example.scoremyessay.utils.Const

class DoneEssayFragment : BaseFragmentWithViewBinding<FragmentViewEssayDoneBinding>() {

    private val mActivity by lazy {
        activity as ActivityMain
    }
    lateinit var viewModel: MainViewModel

    private val mAdapter: EssayAdapter by lazy { EssayAdapter() }

    private fun initObserver(){
        viewModel.allEssay.observe(requireActivity(), {
            val listDoneEssay = it.filter { item ->
                item.status_id == Const.STATUS_DONE}

            mAdapter.updateData(listDoneEssay)
        })

        viewModel.liTeacher.observe(requireActivity(), {
            mAdapter.updateTeacher(it)
//            viewModel.getTeacherAvatar()
        })

        viewModel.liOrderResult.observe(requireActivity(), {
            mAdapter.updateResultOrder(it)
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun handleTask() {
        initViewModel()

        initView()
        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    search(newText)
                }
                return true
            }

        })

        mAdapter.setOnItemClick {
            viewModel.handleItemClick(orderItem = it)

            val action = EssayFragmentDirections.actionActionEssayToViewDetailFragment()
            controller.navigate(action)
        }
    }

    private var matchEssay = listOf<OrderItem>()

    private fun search(text: String){
        val liEssay = viewModel.allEssay.value?.filter { it.status_id == Const.STATUS_DONE }

        if(liEssay?.isEmpty() == true){
            Toast.makeText(requireContext(), "Không có bài viết nào hiện tại",Toast.LENGTH_SHORT).show()
        }else{
            liEssay?.filter {
                it.essay.title.lowercase().contains(text) || it.essay.type_name.lowercase().contains(text)
            }?.let {
                matchEssay = it
            }
            if(matchEssay.isEmpty()){
                Toast.makeText(requireContext(), "Không tìm thấy bài viết trừng khớp",Toast.LENGTH_SHORT).show()
            }

            mAdapter.updateData(matchEssay)
        }
    }

    private fun initView() {
//        mUserPreferences.orderType.asLiveData().observe(viewLifecycleOwner,{
//            it?.let {
//                val liTypes = Gson().fromJson(it, Array<OrderType>::class.java).asList()
//                Log.e("TAG", "initView: ${liTypes.toString()}", )
//                mAdapter.updateTypes(liTypes)
//            }
//        })

        viewModel.orderTypeAPI.observe(viewLifecycleOwner,{
            mAdapter.updateTypes(it)
        })

        binding.recyclerViewDoneEssay.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            binding.recyclerViewDoneEssay.adapter = mAdapter
        }
    }

    private fun initViewModel() {
        viewModel = (activity as ActivityMain).viewModel
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean?
    ): FragmentViewEssayDoneBinding = FragmentViewEssayDoneBinding.inflate(inflater,container, false)
}