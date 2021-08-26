package com.example.scoremyessay.base

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.scoremyessay.base.BaseRepository
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.network.RemoteDataSource

abstract class BaseActivity<VM: ViewModel, B: ViewBinding, R: BaseRepository> :AppCompatActivity() {
    protected lateinit var userPreferences: UserPreferences
    lateinit var binding: B
    lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    abstract fun getViewModel(): Class<VM>
    abstract fun getActivityBinding(inflater: LayoutInflater) : B
    abstract fun getActivityRepository() : R
}