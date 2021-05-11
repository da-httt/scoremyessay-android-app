package com.example.scoreMyEssay.data.model.order

import com.google.gson.annotations.SerializedName

data class AllOrdersServer(
        val currentPage: Int,
        @SerializedName("data")
        val `data`: List<Order>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)