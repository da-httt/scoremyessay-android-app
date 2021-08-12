package com.example.scoremyessay.utils.extension

import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatus
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.example.scoremyessay.data.model.orders.OrderItem

fun List<OrderItem>.getTeacherIdSet(): Set<Int>{
    return this.mapNotNull { it.teacher_id }.toSet()
}

fun List<OrderType>.getNameByID(id: Int): String{
    return this.find {
        it.type_id == id
    }?.type_name ?: "No Type Found"
}