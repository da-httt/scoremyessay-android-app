package com.example.scoremyessay.data.model.orderAttribute.result

data class CommentResult(
    val content: String,
    val essay_comments: List<EssayComment>,
    val essay_id: Int,
    val title: String,
    val type_id: Int
)