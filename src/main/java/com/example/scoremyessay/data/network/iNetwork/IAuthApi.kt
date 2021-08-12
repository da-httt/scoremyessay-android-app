package com.example.scoremyessay.data.network.iNetwork

import com.example.scoremyessay.data.model.LoginRequest
import com.example.scoremyessay.data.model.authentication.login.LoginResponse
import com.example.scoremyessay.data.model.authentication.register.ApiRegisterResponse
import com.example.scoremyessay.data.model.authentication.register.RegisterRequest
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthApi {
    @POST("login")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : LoginResponse

    @POST("signup")
    fun registerUser(@Body registerRequest: RegisterRequest) : Observable<Response<ApiRegisterResponse>>
}