package com.example.scoremyessay.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(


    @SerializedName("access_token")
    var authToken: String,

    @SerializedName("token_type")
    var token_type: String
)
