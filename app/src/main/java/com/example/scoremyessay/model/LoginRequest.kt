package com.example.scoremyessay.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class LoginRequest(
    @SerializedName("username")
    @Expose
    val username:String? = null,
    @SerializedName("password")
    @Expose
    val passWord: String? = null
)
