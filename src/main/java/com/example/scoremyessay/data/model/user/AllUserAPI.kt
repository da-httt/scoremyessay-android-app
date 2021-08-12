package com.example.scoremyessay.data.model.user

data class AllUserAPI(
    val currentPage: Int,
    val `data`: List<AccountData>,
    val pageCount: Int,
    val perPage: Int,
    val status: String,
    val totalCount: Int
)