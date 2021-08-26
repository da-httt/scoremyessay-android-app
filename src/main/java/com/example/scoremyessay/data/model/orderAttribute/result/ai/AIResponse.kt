package com.example.scoremyessay.data.model.orderAttribute.result.ai

data class AIResponse(
    val average_sentence_length: Double,
//    val essay_info_id: Int,
//    val keywords: List<String>,
    val num_errors: Int,
    val number_of_sentences: Int,
    val number_of_words: Int,
    val spelling_errors: List<SpellingError>
)