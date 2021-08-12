package com.example.scoremyessay.data.model.orderAttribute.type

data class OrderTypeAPI(
        val currentPage: Int,
        val `data`: List<OrderType>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)