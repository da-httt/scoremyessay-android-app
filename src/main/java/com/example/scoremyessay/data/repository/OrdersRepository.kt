        package com.example.scoremyessay.data.repository

import android.util.Log
import com.example.scoremyessay.base.BaseRepository
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevel
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOption
import com.example.scoremyessay.data.model.orderAttribute.result.OrderResultAPI
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatus
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.data.model.user.UserInfo
import com.example.scoremyessay.data.network.iNetwork.IOrderApi
import com.example.scoremyessay.utils.Resource

        class OrdersRepository(private val api: IOrderApi,
                       private val userPreferences: UserPreferences
) : BaseRepository(){

    private suspend fun getEssayResultByID(id : Int): OrderResultAPI? {
        val resourceRemote = safeApiCall { api.getResultByOrderId(id) }
        if(resourceRemote is Resource.Success)
        {
            return resourceRemote.value
        }
        return null
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