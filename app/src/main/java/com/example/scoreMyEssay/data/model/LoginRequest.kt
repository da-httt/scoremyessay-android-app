package com.example.scoreMyEssay.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("username")
    @Expose
    val username:String? = null,
    @SerializedName("password")
    @Expose
    val passWord: String? = null
)
