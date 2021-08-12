package com.example.scoremyessay.data.model.authentication.login

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("access_token")
    var authToken: String,

    @SerializedName("token_type")
    var token_type: String,
//    @SerializedName("detail")
//    var detail: String
): Serializable
