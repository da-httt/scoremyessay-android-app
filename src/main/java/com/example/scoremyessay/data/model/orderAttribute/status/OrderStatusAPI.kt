package com.example.scoremyessay.data.model.orderAttribute.status

import com.google.gson.annotations.SerializedName

data class OrderStatusAPI(
        val currentPage: Int,
        @SerializedName("data")
        val `data`: List<OrderStatus>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)