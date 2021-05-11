package com.example.scoreMyEssay.data.model.order

data class EssayLevelServer(
        val currentPage: Int,
        val `data`: List<EssayLevel>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)