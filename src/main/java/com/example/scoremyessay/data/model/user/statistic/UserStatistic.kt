package com.example.scoremyessay.data.model.user.statistic

data class UserStatistic(
    val gross: Double,
    val monthly_orders: Int,
    val monthly_payment: Int,
    val role_id: Int,
    val total_done: Int,
    val total_orders: Int,
    val total_payment: Int,
    val user_id: Int
)