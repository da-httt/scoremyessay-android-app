package com.example.scoremyessay.data.model.authentication.register

data class ApiRegisterResponse(
    val account_id: Int,
    val disabled: Boolean,
    val email: String,
    val role_id: Int,
    val user_id: Int
)