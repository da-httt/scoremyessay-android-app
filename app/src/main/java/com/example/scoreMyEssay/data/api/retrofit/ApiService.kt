package com.example.scoreMyEssay.data.api.retrofit

import com.example.scoreMyEssay.data.model.LoginRequest
import com.example.scoreMyEssay.data.model.LoginResponse
import com.example.scoreMyEssay.data.model.order.AllOrdersServer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    // @FormUrlEncoded
    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest) : Single<LoginResponse>
    @GET("orders")
    fun getAllOrder(): Call<AllOrdersServer>

}