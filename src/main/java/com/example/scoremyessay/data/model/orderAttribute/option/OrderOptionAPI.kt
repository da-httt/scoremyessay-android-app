package com.example.scoremyessay.data.model.orderAttribute.option

data class OrderOptionAPI(
        val currentPage: Int,
        val `data`: List<OrderOption>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)