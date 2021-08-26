package com.example.scoremyessay.data.model.orderAttribute.result

data class OrderResultAPI(
    val comment: String,
    val criteria_results: List<CriteriaResult>?,
    val extra_results: List<ExtraResult>?,
    val grade: Float,
    val grade_comment: String,
    val isCriteria: Boolean,
    val isExtra: Boolean,
    val order_status_id: Int,
    val result_id: Int,
    val review: String
)