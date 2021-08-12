package com.example.scoremyessay.data.network.iNetwork

import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevelAPI
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOptionAPI
import com.example.scoremyessay.data.model.orderAttribute.result.OrderResultAPI
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatusAPI
import com.example.scoremyessay.data.model.orderAttribute.type.OrderTypeAPI
import com.example.scoremyessay.data.model.orders.AllOrdersAPI
import com.example.scoremyessay.data.model.user.AllUserAPI
import com.example.scoremyessay.data.model.user.UserInfo
import retrofit2.http.*

interface IOrderApi {

    @GET("status")
    suspend fun getOrderStatus(): OrderStatusAPI
    @GET("options")
    suspend fun getOrderOptions(): OrderOptionAPI
    @GET("levels")
    suspend fun getOrderLevels(): OrderLevelAPI
    @GET("types")
    suspend fun getOrderTypes(): OrderTypeAPI


    @GET("orders")
    suspend fun getAllOrders(): AllOrdersAPI
    @GET("users")
    suspend fun getAllUsers(): AllUserAPI
    @GET("results/{orders_id}")
    suspend fun getResultByOrderId(@Path("orders_id") orderID: Int): OrderResultAPI

    @GET("users/{user_id}")
    suspend fun getUserInformationByUserId(@Path("user_id") userID: Int): UserInfo
}