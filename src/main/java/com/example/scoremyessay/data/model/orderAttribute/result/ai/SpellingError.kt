package com.example.scoremyessay.data.model.orderAttribute.result.ai

data class SpellingError(
    val index: Int,
    val sentence: String,
    val sentence_index: Int,
    val suggested_word: String,
    val word: String
)