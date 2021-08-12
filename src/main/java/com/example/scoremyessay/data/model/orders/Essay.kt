package com.example.scoremyessay.data.model.orders

data class Essay(
    val content: String,
    val essay_id: Int,
    val title: String,
    val type_id: Int,
    var type_name: String
)