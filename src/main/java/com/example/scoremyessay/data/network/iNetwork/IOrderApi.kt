package com.example.scoremyessay.data.network.iNetwork

import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevelAPI
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOptionAPI
import com.example.scoremyessay.data.model.orderAttribute.result.CommentResult
import com.example.scoremyessay.data.model.orderAttribute.result.OrderResultAPI
import com.example.scoremyessay.data.model.orderAttribute.result.ai.AIResponse
import com.example.scoremyessay.data.model.orderAttribute.result.rating.OrderRating
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatusAPI
import com.example.scoremyessay.data.model.orderAttribute.type.OrderTypeAPI
import com.example.scoremyessay.data.model.orders.AllOrdersAPI
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.data.model.orders.OrderRequest
import com.example.scoremyessay.data.model.orders.PaymentStatus
import com.example.scoremyessay.data.model.user.*
import com.example.scoremyessay.data.model.user.avatar.UserAvatar
import com.example.scoremyessay.data.model.user.credit_card.CreditCardUser
import com.example.scoremyessay.data.model.user.statistic.UserStatistic
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

    @GET("essay_comments/{orders_id}")
    suspend fun getEssayCommentsByOrderId(@Path("orders_id") orderID: Int): CommentResult
    @GET("spelling_errors/{orders_id}")
    suspend fun getSpellErrorOfEssayById(@Path("orders_id") orderID: Int): AIResponse

    @GET("users/{user_id}")
    suspend fun getUserInformationByUserId(@Path("user_id") userID: Int): AccountData
    @GET("avatars/{user_id}")
    suspend fun getUserAvatarByUserId(@Path("user_id") userID: Int): UserAvatar

    @GET("ratings/{order_id}")
    suspend fun getRatingByOrderId(@Path("order_id") userID: Int): OrderRating
    @POST("ratings/{order_id}")
    suspend fun postRatingByOrderID(@Path("order_id") orderID: Int, @Body orderRating: OrderRating  )

    @POST("orders")
    suspend fun postOrders(@Body order: OrderRequest): OrderItem

    @POST("orders/payment/{order_id}")
    suspend fun payOrder(@Path("order_id") orderID: Int, @Query("payment_type") credit_type: String = "CREDIT_CARD"): PaymentStatus

    @GET("credit_card/me")
    suspend fun getMyCreditCard(): CreditCardUser
    @GET("users/me")
    suspend fun getMyAccountInformation(): AccountDataResponse
    @GET("jobs")
    suspend fun getAllJobType(): JobTypeAPI

    @GET("statistics/me")
    suspend fun getMyStatistics(): UserStatistic


}