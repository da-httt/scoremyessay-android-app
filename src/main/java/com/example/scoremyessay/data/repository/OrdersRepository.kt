        package com.example.scoremyessay.data.repository

import android.util.Log
import com.example.scoremyessay.base.BaseRepository
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevel
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOption
import com.example.scoremyessay.data.model.orderAttribute.result.rating.OrderRating
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatus
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.example.scoremyessay.data.model.orders.OrderRequest
import com.example.scoremyessay.data.network.iNetwork.IOrderApi

        class OrdersRepository(private val api: IOrderApi,
                       private val userPreferences: UserPreferences
) : BaseRepository(){

    suspend fun getMyStatistic() = safeApiCall {
        api.getMyStatistics()
    }

    suspend fun getAllJobType() = safeApiCall {
        api.getAllJobType().data
    }

    suspend fun getMyAccountInformation() = safeApiCall {
        api.getMyAccountInformation()
    }

    suspend fun getCreditCard() = safeApiCall {
        api.getMyCreditCard()
    }

    suspend fun payOrder(orderID: Int) = safeApiCall {
        api.payOrder(orderID)
    }

    suspend fun postOrder(orderRequest: OrderRequest) = safeApiCall {
        api.postOrders(orderRequest)
    }

    suspend fun postRatingOrder(orderId: Int, ratingOrder: OrderRating) = safeApiCall {
        api.postRatingByOrderID(orderId, ratingOrder)
    }

    suspend fun getOrderRatingAPI(orderID: Int) = safeApiCall {
        api.getRatingByOrderId(orderID)
    }

    suspend fun getUserAvatar(userId: Int) = safeApiCall {
        api.getUserAvatarByUserId(userId)
    }

    suspend fun getSpellErrorByOrderID(userId: Int) = safeApiCall {
        api.getSpellErrorOfEssayById(userId)
    }

    suspend fun getTeacherInformationByUserID(userId: Int) = safeApiCall {
         api.getUserInformationByUserId(userId)
    }

    suspend fun getOrderStatuses() = safeApiCall {
        api.getOrderStatus().data
    }

    suspend fun getOrderOptions() = safeApiCall {
        api.getOrderOptions().data
    }

    suspend fun getOrderTypes() = safeApiCall {
        api.getOrderTypes().data
    }

    suspend fun getOrderLevel() = safeApiCall {
        api.getOrderLevels().data
    }

    suspend fun getAllOrder() = safeApiCall {
        api.getAllOrders().data
    }

    suspend fun getAllUser()= safeApiCall {
        api.getAllUsers().data
    }

    suspend fun getOrderResultById(orderId: Int) = safeApiCall{
        api.getResultByOrderId(orderId)
    }

    suspend fun getCommentResultById(orderId: Int) = safeApiCall {
        api.getEssayCommentsByOrderId(orderId)
    }

    suspend fun saveOrderStatuses(liOrderStatus: List<OrderStatus>)
    {
        Log.e("luu",liOrderStatus.toString())
        userPreferences.saveOrderStatus(liOrderStatus)
    }
    suspend fun saveOrderOptions(liOrderOption: List<OrderOption>)
    {
        Log.e("luu",liOrderOption.toString())
        userPreferences.saveOrderOption(liOrderOption)
    }
    suspend fun saveOrderTypes(liOrderType: List<OrderType>)
    {
        Log.e("luu",liOrderType.toString())
        userPreferences.saveOrderType(liOrderType)
    }
    suspend fun saveOrderLevels(liOrderLevel: List<OrderLevel>)
    {
        Log.e("luu",liOrderLevel.toString())
        userPreferences.saveOrderLevel(liOrderLevel)
    }
//    }
}