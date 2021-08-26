package com.example.scoremyessay.data.model.orders

data class OrderRequest(
    val essay: Essay= Essay(),
    var option_list: List<Int> = listOf(0,1,4)
)