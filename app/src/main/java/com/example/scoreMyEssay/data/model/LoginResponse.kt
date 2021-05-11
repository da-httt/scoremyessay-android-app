package com.example.scoreMyEssay.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(


    @SerializedName("access_token")
    var authToken: String,

    @SerializedName("token_type")
    var token_type: String
): Serializable
