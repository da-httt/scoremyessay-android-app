package com.example.scoremyessay.data.model.user

data class AccountData(
    val account_id: Int,
    val disabled: Boolean,
    val email: String,
    val userInfo: UserInfo,
    val role_id: Int
)