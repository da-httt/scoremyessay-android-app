package com.example.scoremyessay.data.model.orders

data class PaymentStatus(
    val invoice: Invoice,
    val status: Boolean
)