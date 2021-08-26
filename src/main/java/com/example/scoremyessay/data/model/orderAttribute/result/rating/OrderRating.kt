package com.example.scoremyessay.data.model.orderAttribute.result.rating

data class OrderRating(
    val comment: String,
    val order_id: Int? = null,
    val rating_id: Int? = null,
    var stars: Int,
    val student_id: Int?= null,
    val teacher_id: Int?= null
)