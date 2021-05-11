package com.example.scoreMyEssay.data.model.order

data class Order(
    val essay: Essay,
    val option_list: List<Int>,
    val order_id: Int,
    val sent_date: String,
    val status_id: Int,
    val student_id: Int,
    val teacher_id: Int,
    val total_price: Int,
    val updated_by: Int,
    val updated_date: String
)