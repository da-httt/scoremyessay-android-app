package com.example.scoremyessay.data.model.orderAttribute.level

data class OrderLevelAPI(
        val currentPage: Int,
        val `data`: List<OrderLevel>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)