package com.example.scoremyessay.ui.viewModel


import android.util.Log
import androidx.lifecycle.*
import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevel
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOption
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatus
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.data.model.user.AccountData
import com.example.scoremyessay.data.model.user.UserInfo
import com.example.scoremyessay.data.repository.OrdersRepository
import com.example.scoremyessay.utils.Resource
import com.example.scoremyessay.utils.extension.getTeacherIdSet
import kotlinx.coroutines.*
import java.util.ArrayList

//import com.example.scoremyessay.data.repository.OrdersRepository

class MainViewModel(private val repository: OrdersRepository): ViewModel() {
    private val _orderStatusAPI = MutableLiveData<Resource<List<OrderStatus>>>()
    private val _orderOptionAPI = MutableLiveData<Resource<List<OrderOption>>>()
    private val _orderTypeAPI = MutableLiveData<Resource<List<OrderType>>>()
    private val _orderLevelAPI = MutableLiveData<Resource<List<OrderLevel>>>()

    private val _allEssay = MutableLiveData<List<OrderItem>>()
    private val _allUser = MutableLiveData<List<AccountData>>()

    val allEssay: LiveData<List<OrderItem>>
        get() {return _allEssay}
    val allUser: LiveData<List<AccountData>>
        get() {return _allUser}

    fun getAllEssay() = viewModelScope.launch {
        val allEssay = repository.getAllOrder()
            if (allEssay is Resource.Success) {
                allEssay.value.let {
                    _allEssay.value = it
            }
        }
    }

    fun getAllTeacherInfo() = viewModelScope.launch {
//        withContext(Dispatchers.IO){
//            val allUser = repository.getAllUser()
//            withContext(Dispatchers.Main){
//                if(allUser is Resource.Success){
//                    allUser.value.let {
//                        _allUser.value = it
//                    }
//                }
//            }
//        }
        supervisorScope {
            _allEssay.value?.let {
                val requestTeacherInfor = ArrayList<Deferred<Resource<UserInfo>>>()

                val setTeacherId = it.getTeacherIdSet()
                val mapTeacherInformation = HashMap<Int, UserInfo>()
                Log.e("TeacherID", setTeacherId.toString())

                setTeacherId.forEach { id ->
                    requestTeacherInfor.add(async { repository.getTeacherInformationByUserID(id) })
//                    mapTeacherInformation[id] =
                }
                val a = requestTeacherInfor.awaitAll()
                Log.e("aaaaa", a.toString())
            }

        }
    }

    fun getOrderStatusApi() = viewModelScope.launch {
        _orderStatusAPI.value = repository.getOrderStatuses()
        if(_orderStatusAPI.value is Resource.Success){
            repository.saveOrderStatuses((_orderStatusAPI.value as Resource.Success<List<OrderStatus>>).value)
        }
    }

    fun getOrderOptionApi() = viewModelScope.launch {
        _orderOptionAPI.value = repository.getOrderOptions()
        if(_orderOptionAPI.value is Resource.Success){
            repository.saveOrderOptions((_orderOptionAPI.value as Resource.Success<List<OrderOption>>).value)
        }
    }

    fun getOrderTypeApi() = viewModelScope.launch {
        _orderTypeAPI.value = repository.getOrderTypes()
        if(_orderTypeAPI.value is Resource.Success){
            repository.saveOrderTypes((_orderTypeAPI.value as Resource.Success<List<OrderType>>).value)
        }
    }

    fun getOrderLevelApi() = viewModelScope.launch {
        _orderLevelAPI.value = repository.getOrderLevel()
        if(_orderLevelAPI.value is Resource.Success){
            repository.saveOrderLevels((_orderLevelAPI.value as Resource.Success<List<OrderLevel>>).value)
        }
    }

//    private val api = iBasicDataApi
//    private val repos = OrdersRepository(api)
//
//    var isDataCompleteReceive = MutableLiveData<Boolean>()

//    val getIsDataCompleteReceive : LiveData<Boolean>
//        get() = isDataCompleteReceive

//     var isGetStatusSuccess = false
//     var isGetOptionSuccess = false
//     var isGetLevelSuccess = false
//     var isGetTypeSuccess = false

//
//    private var liOrderStatuses = MutableLiveData<Resource<List<OrderStatus>>>()
//
//
//    fun getLiOrderStatusesResponse(): MutableLiveData<Resource<List<OrderStatus>>> {
//        liOrderStatuses= repos.getStatusResponse()
//        return liOrderStatuses
//    }
//
//    private var liOrderOptions = MutableLiveData<Resource<List<OrderOption>>>()
//
//    fun getLiOrderOptionsResponse(): MutableLiveData<Resource<List<OrderOption>>> {
//        liOrderOptions = repos.getOptionResponse()
//        return liOrderOptions
//    }
//
//    private var liOrderLevels = MutableLiveData<Resource<List<OrderLevel>>>()
//
//    fun getLiOrderLevelsResponse(): MutableLiveData<Resource<List<OrderLevel>>> {
//        liOrderLevels = repos.getLevelResponse()
//        return liOrderLevels
//    }
//
//    private var liOrderTypes = MutableLiveData<Resource<List<OrderType>>>()
//
//    fun getLiOrderTypesResponse(): MutableLiveData<Resource<List<OrderType>>> {
//        liOrderTypes = repos.getTypeResponse()
//        return liOrderTypes
//    }
//    fun isGetAllInformation(): Boolean
//    {
//        return isGetLevelSuccess && isGetOptionSuccess && isGetStatusSuccess && isGetTypeSuccess
//    } // chinh lai cai nay
}