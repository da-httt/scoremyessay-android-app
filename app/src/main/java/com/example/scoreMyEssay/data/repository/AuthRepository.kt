package com.example.scoreMyEssay.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.scoreMyEssay.data.api.retrofit.ApiService
import com.example.scoreMyEssay.data.model.LoginRequest
import com.example.scoreMyEssay.data.model.LoginResponse
import io.reactivex.Single

public class AuthRepository (private val apiService: ApiService){

    var cachedLoginResponse : MutableList<LoginResponse> = mutableListOf()
    val loginResponse = MutableLiveData<String>()
    fun getLoginResponse(loginRequest: LoginRequest) : Single<LoginResponse>{
        return apiService.loginUser(loginRequest)}

   }
