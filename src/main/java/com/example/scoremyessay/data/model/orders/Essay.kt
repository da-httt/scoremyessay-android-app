package com.example.scoremyessay.data.model.orders

data class Essay(
    var content: String = "",
    val essay_id: Int = 0,
    var title: String = "",
    var type_id: Int = 0,
    var type_name: String = ""
)