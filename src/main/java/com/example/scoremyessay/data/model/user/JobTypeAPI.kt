package com.example.scoremyessay.data.model.user

data class JobTypeAPI(
    val `data`: List<JobType>,
    val status: String,
    val totalCount: Int
)