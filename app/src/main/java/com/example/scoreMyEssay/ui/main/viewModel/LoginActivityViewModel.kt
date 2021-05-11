package com.example.scoreMyEssay.ui.main.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scoreMyEssay.data.api.retrofit.ApiService
import com.example.scoreMyEssay.data.model.LoginRequest
import com.example.scoreMyEssay.data.model.LoginResponse
import com.example.scoreMyEssay.data.repository.AuthRepository
import javax.inject.Inject


class LoginActivityViewModel @Inject constructor(apiService: ApiService): ViewModel() {
    private val authRepository : AuthRepository = AuthRepository(apiService)
    private val loginResponse = MutableLiveData<LoginResponse>()
    private lateinit var loginRequest: LoginRequest

//    fun onLoginButtonClick(view: View)
//    {
//        if(loginRequest.username.isNullOrEmpty() || loginRequest.passWord.isNullOrEmpty())
//        {
//            return
//        }
//    }
    private fun sendLoginRequest(loginRequest: LoginRequest)
    {
        authRepository.getLoginResponse(loginRequest).subscribe{resource -> getLoginResponse().postValue(resource)}
    }
    private fun getLoginResponse() = loginResponse
}