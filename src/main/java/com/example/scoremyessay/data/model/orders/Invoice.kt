package com.example.scoremyessay.data.model.orders

data class Invoice(
    val level_id: Int,
    val option_list: List<Int>,
    val order_id: Int,
    val payment_date: String,
    val payment_message: String,
    val payment_status: String,
    val payment_type: String,
    val total_price: Int,
    val type_id: Int,
    val user_id: Int
)