package com.example.scoreMyEssay.data.model.order

data class EssayTypeServer(
        val currentPage: Int,
        val `data`: List<EssayType>,
        val pageCount: Int,
        val perPage: Int,
        val status: String,
        val totalCount: Int
)