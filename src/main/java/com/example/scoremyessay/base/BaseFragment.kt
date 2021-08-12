package com.example.scoremyessay.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.scoremyessay.base.BaseRepository
import com.example.scoremyessay.base.ViewModelFactory
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.network.RemoteDataSource

abstract class BaseFragment<VM: ViewModel, B: ViewBinding, R: BaseRepository> : Fragment() {
    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater,container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this,factory).get(getViewModel() )

        initObserve()
        test()
        doingTask()

        return binding.root
    }
    abstract fun getViewModel(): Class<VM>
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B
    abstract fun getFragmentRepository() : R
    abstract fun initObserve()
    abstract fun doingTask()
    abstract fun test()

}
//abstract class BaseFragmentWithViewBinding<B: ViewBinding>: Fragment(){
//    protected lateinit var binding: B
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = getFragmentBinding(inflater,container)
//
//        doingTask()
//        return binding.root
//    }
//    abstract fun doingTask()
//    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B
//}