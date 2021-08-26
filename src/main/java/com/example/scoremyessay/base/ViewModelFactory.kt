package com.example.scoremyessay.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scoremyessay.data.repository.AuthRepository
import com.example.scoremyessay.data.repository.OrdersRepository
import com.example.scoremyessay.viewModel.LoginViewModel
import com.example.scoremyessay.viewModel.MainViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository as OrdersRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not found")
        }
    }
}