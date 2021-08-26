package com.example.scoremyessay.data.model.orders

import com.example.scoremyessay.data.model.orderAttribute.result.CommentResult
import com.example.scoremyessay.data.model.orderAttribute.result.OrderResultAPI
import com.example.scoremyessay.data.model.orderAttribute.result.ai.AIResponse
import com.example.scoremyessay.data.model.orderAttribute.result.ai.SpellingError
import com.example.scoremyessay.data.model.orderAttribute.result.rating.OrderRating
import com.example.scoremyessay.data.model.user.UserInfo

data class OrderItem(
    val deadline: String,
    val essay: Essay,
    val option_list: List<Int>,
    val order_id: Int,
    val sent_date: String,
    val status_id: Int,
    val student_id: Int,
    val teacher_id: Int?,
    val time_left: Int,
    val topic_id: Int,
    val topic_name: String,
    val total_price: Int,
    val updated_by: Int,
    val updated_date: String,

    var result: OrderResultAPI?,
    var teacherInformation: UserInfo?,
    var comment: CommentResult?,
    var spellError: AIResponse?,
    var rating: OrderRating?
)
