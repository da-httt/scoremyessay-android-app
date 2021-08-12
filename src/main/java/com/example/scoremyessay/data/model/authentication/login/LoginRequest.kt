package com.example.scoremyessay.data.model

import android.util.Patterns
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
fun LoginRequest.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this.username.toString()).matches()
fun LoginRequest.isPassWordGreaterThan5() = this.passWord?.length!! > 5
fun LoginRequest.isEmailEmpty()= this.username.isNullOrEmpty()
fun LoginRequest.isPasswordEmpty() = this.passWord.isNullOrEmpty()



