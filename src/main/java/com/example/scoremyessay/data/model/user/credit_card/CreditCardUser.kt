package com.example.scoremyessay.data.model.user.credit_card

data class CreditCardUser(
    val account_no: String,
    val balance: Int,
    val currency: String,
    val expiry_date: String,
    val provider: String,
    val user_id: Int
)