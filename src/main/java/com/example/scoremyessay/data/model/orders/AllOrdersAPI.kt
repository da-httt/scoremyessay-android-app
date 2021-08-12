package com.example.scoremyessay.data.model.orders

data class AllOrdersAPI(
        val currentPage: Int,
        val `data`: List<OrderItem>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)