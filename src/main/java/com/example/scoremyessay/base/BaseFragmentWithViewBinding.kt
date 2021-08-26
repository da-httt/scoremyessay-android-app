package com.example.scoremyessay.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithViewBinding<B: ViewBinding>: Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected lateinit var controller: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(layoutInflater, container, false)

        controller = findNavController()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        handleTask()
    }

    abstract fun handleTask()
    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean?): B
}