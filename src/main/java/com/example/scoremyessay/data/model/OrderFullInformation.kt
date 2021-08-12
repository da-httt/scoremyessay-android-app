package com.example.scoremyessay.data.model

import com.example.scoremyessay.data.model.orders.Essay

data class OrderFullInformation (
    val deadline: String,
    val essay: Essay,
    val is_disabled: Boolean,
    val option_list: List<Int>,
    val order_id: Int,
    val sent_date: String,
    val status_id: Int,
    val student_id: Int,
    val teacher_id: Int,
    val time_left: Int,
    val topic_id: Int,
    val topic_name: String,
    val total_price: Int,
    val updated_by: Int,
    val updated_date: String
    )

