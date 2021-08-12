package com.example.scoremyessay.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.scoremyessay.ui.viewModel.MainViewModel
import com.example.scoremyessay.ui.main.fragment.showEssay.adapter.EssayAdapter
import com.example.scoremyessay.R
import com.example.scoremyessay.databinding.FragmentCartBinding


class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: MainViewModel
    lateinit var essayAdapter: EssayAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }



}