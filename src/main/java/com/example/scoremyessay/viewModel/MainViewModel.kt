package com.example.scoremyessay.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.example.scoremyessay.base.BaseViewModel
import com.example.scoremyessay.base.Event
import com.example.scoremyessay.data.model.orderAttribute.level.OrderLevel
import com.example.scoremyessay.data.model.orderAttribute.option.OrderOption
import com.example.scoremyessay.data.model.orderAttribute.result.EssayComment
import com.example.scoremyessay.data.model.orderAttribute.result.OrderResultAPI
import com.example.scoremyessay.data.model.orderAttribute.result.ai.AIResponse
import com.example.scoremyessay.data.model.orderAttribute.result.rating.OrderRating
import com.example.scoremyessay.data.model.orderAttribute.status.OrderStatus
import com.example.scoremyessay.data.model.orderAttribute.type.OrderType
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.data.model.orders.OrderRequest
import com.example.scoremyessay.data.model.orders.PaymentStatus
import com.example.scoremyessay.data.model.ui.ThanhToanOption
import com.example.scoremyessay.data.model.user.AccountData
import com.example.scoremyessay.data.model.user.AccountDataResponse
import com.example.scoremyessay.data.model.user.JobType
import com.example.scoremyessay.data.model.user.avatar.UserAvatar
import com.example.scoremyessay.data.model.user.statistic.UserStatistic
import com.example.scoremyessay.data.repository.OrdersRepository
import com.example.scoremyessay.utils.Resource
import com.example.scoremyessay.utils.extension.getIndexByOrderID
import com.example.scoremyessay.utils.extension.getOrderIdSet
import com.example.scoremyessay.utils.extension.getTeacherIdSet
import kotlinx.coroutines.*
import java.util.ArrayList

class MainViewModel(private val repository: OrdersRepository) : BaseViewModel() {

    var orderStatusAPI = MutableLiveData<List<OrderStatus>>()
        private set
    var orderOptionAPI = MutableLiveData<List<OrderOption>>()
        private set
    var orderTypeAPI = MutableLiveData<List<OrderType>>()
        private set
    var orderLevelAPI = MutableLiveData<List<OrderLevel>>()
        private set
    var jobTypeAPI = MutableLiveData<List<JobType>>()
        private set

    //    private val allEssay = MutableLiveData<MutableList<OrderItem>>()
//    private val _allUser = MutableLiveData<List<AccountData>>()

    val mPickImage = MutableLiveData<Bitmap>()
    val mPostOrderRequest = MutableLiveData<OrderRequest>()

    init {
        mPostOrderRequest.value = OrderRequest()
    }
    //Thông tin giáo viên
    var liTeacher = MutableLiveData<List<AccountData>>()
        private set
    var liOrderResult = MutableLiveData<List<OrderResultAPI>>()
        private set
    //Thong tin bản thân
    var mMyInformation = MutableLiveData<AccountDataResponse>()


    var allEssay = MutableLiveData<MutableList<OrderItem>>()
        private set
//    val allUser: LiveData<List<AccountData>>
//        get() {
//            return _allUser
//        }

    var indexItemClick = 0

    var currentCommentIndex = -1

    var currentOrderIDClick = -1

    var teacherIndexItemClick: Int? = null

    var teacherIDClick: Int? = null

    var mPostStatus = MutableLiveData<Event<Boolean>>()
        private set

    var mTeacherAvatar = MutableLiveData<UserAvatar>()
        private set

    var mCurrentComment = MutableLiveData<EssayComment>()
        private set


    var mLiCommentResult = listOf<EssayComment>()
        private set

    var mSpellError = MutableLiveData<AIResponse>()
        private set

    var mOrderRating = MutableLiveData<OrderRating>()
        private set

    var currentLevelIndex = MutableLiveData<Int>()

    var mMyStaticResponse = MutableLiveData<Resource<UserStatistic>>()
        private set

    var mMyStatic = MutableLiveData<UserStatistic>()

    fun fetchMyStatics() = viewModelScope.launch{
        mMyStaticResponse.value = repository.getMyStatistic()
    }

    fun fetchMyInformation() = viewModelScope.launch {
        when (val myAccountInformation = repository.getMyAccountInformation() ) {
            is Resource.Success -> {
                mMyInformation.value = myAccountInformation.value!!

                when(val avatar =
                    mMyInformation.value?.account_id?.let { repository.getUserAvatar(it) }){
                    is Resource.Success -> {
                        mMyInformation.value!!.avatar = avatar.value
                        mMyInformation.value = mMyInformation.value
                    }
                }
            }
        }
    }

    fun fetchCreditCardAPI() = viewModelScope.launch {
        if(mMyInformation.value?.creditCard == null)
        {
            when (val myCredit = repository.getCreditCard() ) {
                is Resource.Success -> {
                    mMyInformation.value?.creditCard = myCredit.value

                    mMyInformation.value = mMyInformation.value
                }
            }
        }
    }

    fun getListThanhToanOption(): MutableList<ThanhToanOption> {
        return orderOptionAPI.value?.filter { mPostOrderRequest.value?.option_list?.contains(it.option_id)!! }
            ?.map {
                ThanhToanOption(
                    if (it.option_type == 1) "Deadline: ${it.option_name} giờ" else it.option_name,
                    it.option_price
                )
            } as MutableList<ThanhToanOption>
    }

    fun getListTypeNameByLevelID(): MutableList<String>? {
        return orderTypeAPI.value?.filter { it.level_id == currentLevelIndex.value }?.map {
            it.type_name
        }?.toMutableList()
    }

    fun handleItemClick(orderItem: OrderItem) {
        currentCommentIndex = -1
        indexItemClick = 0

        findAndSetIndexEssay(orderItem)
        getSpellError()
        getEssayComment()
        getUserAvatar()
        getOderRating()
    }

    fun payOrderAPI() {
//        val
//
//        repository.payOrder()
    }

    fun getTeacherInformation(): AccountData? {
        return teacherIndexItemClick?.let { liTeacher.value?.get(it) }
    }

    var oderRequest = MutableLiveData<OrderRequest>()
    var liOption = mutableListOf<Int>(0, 1)
    var typeIDSelect: Int? = null

    fun addOptionList(numberOption: Int) {
        val number = liOption.find { it == numberOption }
        this@MainViewModel.allEssay
        number?.let { mNumber ->
            if (numberOption in 2..3) {
                liOption = liOption.filter { it != mNumber }.toMutableList()
            } else {
                liOption = liOption.filter { it < 4 }.toMutableList()
                liOption.add(mNumber)
            }
        } ?: run {
            if (numberOption in 2..3) {
                liOption.add(numberOption)
            } else {
                liOption = liOption.filter { it < 4 }.toMutableList()
                liOption.add(numberOption)
            }
        }
        mPostOrderRequest.value!!.option_list = liOption
        mPostOrderRequest.value = mPostOrderRequest.value
    }

    fun getTypeIDbyTypeName(name: String) {
        val typeID = orderTypeAPI.value?.find { it.type_name == name }?.type_id
        if (typeID != null) {
            mPostOrderRequest.value?.essay?.type_id = typeID
        }
        mPostOrderRequest.value = mPostOrderRequest.value
    }

    fun getPriceOfPostOrder(): Int {
        var price = 0

        price += orderTypeAPI.value?.find { it.type_id == mPostOrderRequest.value?.essay?.type_id }?.type_price!!

        orderOptionAPI.value?.filter { mPostOrderRequest.value?.option_list?.contains(it.option_id)!! }
            ?.map { it.option_price }
            ?.forEach {
                price += it
            }
        return price
    }

    var mPaymentStatus = MutableLiveData<Resource<PaymentStatus>>()
        private set

    fun postOrderToServer(isThanhToan: Boolean = false) = viewModelScope.launch {
        when (val postOrderResponse = mPostOrderRequest.value?.let { repository.postOrder(it) }) {
            is Resource.Success -> {
                allEssay.value?.add(postOrderResponse.value)
                if (isThanhToan) {
                    mPaymentStatus.value = repository.payOrder(postOrderResponse.value.order_id)
//                    {
//                        is Resource.Success -> {
//                            mPaymentStatus.value = thanhtoan.value!!
//                        }
//                        is Resource.Failure -> {
//                            if(!thanhtoan.isNetworkError){
//                                when(thanhtoan.errorCode) {
//                                    404 -> {
//                                        showError("Không tìm thấy card")
//                                    }
//                                    405 -> {
//                                        showError()
//                                    }
//                                }
//                            }
//                        }
//                    }
                }
            }
        }
    }

    private fun getUserAvatar() {
        teacherIndexItemClick?.let { index ->
            liTeacher.value!![index].avatar?.let {
                mTeacherAvatar.value = it
            }
        } ?: run {
            teacherIDClick?.let { fetchTeachAvatarByOrderID(it) }
        }
    }

    private fun getOderRating() {
        this@MainViewModel.allEssay.value!![indexItemClick].rating?.let {
            mOrderRating.value = it
        } ?: run {
            fetchOrderRatingByOrderID(this@MainViewModel.allEssay.value!![indexItemClick].order_id)
        }
    }

    private fun findAndSetIndexEssay(orderItem: OrderItem) {
        indexItemClick = this@MainViewModel.allEssay.value!!.indexOf(orderItem)
        currentOrderIDClick = this@MainViewModel.allEssay.value!![indexItemClick].order_id

        teacherIDClick = this@MainViewModel.allEssay.value!![indexItemClick].teacher_id
        teacherIndexItemClick = liTeacher.value?.indexOfFirst { it.user_id == teacherIDClick }
    }

    private fun getEssayComment() {
        this@MainViewModel.allEssay.value!![indexItemClick].comment?.let {
            mLiCommentResult = it.essay_comments
        } ?: run {
            fetchResultCommentByOrderID(this@MainViewModel.allEssay.value!![indexItemClick].order_id)
        }
    }

    private fun getSpellError() {
        this@MainViewModel.allEssay.value!![indexItemClick].spellError?.let {
            mSpellError.value = it
        } ?: run {
            fetchSpellErrorByOrderID(this@MainViewModel.allEssay.value!![indexItemClick].order_id)
        }
    }


    fun getCurrentEssayByClick(): OrderItem {
        return this@MainViewModel.allEssay.value!![indexItemClick]
    }

    fun getAllEssay() = viewModelScope.launch {
        val allEssay = repository.getAllOrder()
        if (allEssay is Resource.Success) {
            allEssay.value.let {
                this@MainViewModel.allEssay.value = it.toMutableList()

                getAllTeacherInfo()
                getAllResultOrder()
            }
        }
    }

//    fun getTeacherAvatar() = viewModelScope.launch{
//        liTeacher.value?.let {
//            val requestResultOrder = ArrayList<Deferred<Resource<UserAvatar>>>()
//
//            liTeacher.value!!.forEach { item ->
//                requestResultOrder.add(async { repository.getUserAvatar(item.account_id) })
//            }
//            val liOrderResult = requestResultOrder.awaitAll()
//
//            var index = 0
//            for (item in liOrderResult) {
//                when (item) {
//                    is Resource.Success -> {
//                        liTeacher.value!![index++].avatar = item.value
//                        liTeacher.value = liTeacher.value
//                    }
//                }
//            }
//        }
//    }


    private fun getAllResultOrder() = viewModelScope.launch {
        supervisorScope {
            this@MainViewModel.allEssay.value?.let {
                val requestResultOrder = ArrayList<Deferred<Resource<OrderResultAPI>>>()

                val setOrderID = it.getOrderIdSet()

                setOrderID.forEach { id ->
                    requestResultOrder.add(async { repository.getOrderResultById(id) })
                }
                val liOrderResult = requestResultOrder.awaitAll()

                var index = 0
                for (item in liOrderResult) {
                    when (item) {
                        is Resource.Success -> {
                            this@MainViewModel.allEssay.value!![index++].result = item.value
                            this@MainViewModel.allEssay.value = allEssay.value
                        }
                    }
                }
            }
        }
    }

    private fun fetchTeachAvatarByOrderID(userID: Int) = viewModelScope.launch {
        when (val avatar = repository.getUserAvatar(userID)) {
            is Resource.Success -> {
                val index = liTeacher.value!!.indexOfFirst { it.user_id == userID }
                liTeacher.value?.get(index)?.avatar = avatar.value

                this@MainViewModel.mTeacherAvatar.value = avatar.value
            }
        }
    }

    private fun fetchResultCommentByOrderID(orderID: Int) = viewModelScope.launch {
        Log.e("TAG", "fetchResultCommentByOrderID: $orderID")
        when (val commentResult = repository.getCommentResultById(orderID)) {
            is Resource.Success -> {
                val index = this@MainViewModel.allEssay.value!!.getIndexByOrderID(orderID)
                this@MainViewModel.allEssay.value?.get(index)?.comment = commentResult.value

                this@MainViewModel.mLiCommentResult = commentResult.value.essay_comments


                if (commentResult.value.essay_comments.isNotEmpty()) {
                    currentCommentIndex = 0

                    mCurrentComment.value = mLiCommentResult[currentCommentIndex]
                }
            }
        }
    }

    fun postOrderRatingByOrderID(rating: OrderRating) = viewModelScope.launch {
        when (val ratingOrder = repository.postRatingOrder(currentOrderIDClick, rating)) {
            is Resource.Success -> {
                mPostStatus.value = Event(true)
            }
        }
    }

    private fun fetchOrderRatingByOrderID(orderID: Int) = viewModelScope.launch {
        when (val ratingOrder = repository.getOrderRatingAPI(orderID)) {
            is Resource.Success -> {
                val index = this@MainViewModel.allEssay.value!!.getIndexByOrderID(orderID)
                this@MainViewModel.allEssay.value?.get(index)?.rating = ratingOrder.value

                this@MainViewModel.mOrderRating.value = ratingOrder.value
            }
            is Resource.Failure -> {
                val a = this@MainViewModel.mOrderRating.value
                if (a != null) {
                    a.stars = 0
                }
                this@MainViewModel.mOrderRating.value = a
            }
        }
    }

    fun getNextComment() {
        if (currentCommentIndex == (mLiCommentResult.size - 1)) {
            currentCommentIndex = 0
        } else {
            currentCommentIndex++
        }

        mCurrentComment.value = mLiCommentResult[currentCommentIndex]
    }

    fun getBackComment() {
        if (currentCommentIndex == 0) {
            currentCommentIndex = mLiCommentResult.size - 1
        } else {
            currentCommentIndex--
        }

        mCurrentComment.value = mLiCommentResult[currentCommentIndex]
    }

    private fun fetchSpellErrorByOrderID(orderID: Int) = viewModelScope.launch {
        when (val spellError = repository.getSpellErrorByOrderID(orderID)) {
            is Resource.Success -> {
                val index = this@MainViewModel.allEssay.value!!.getIndexByOrderID(orderID)
                this@MainViewModel.allEssay.value?.get(index)?.spellError = spellError.value

                this@MainViewModel.mSpellError.value = spellError.value
            }
        }
    }

    fun getAllTeacherInfo() = viewModelScope.launch {
        supervisorScope {
            this@MainViewModel.allEssay.value?.let {
                val requestTeacherInfor = ArrayList<Deferred<Resource<AccountData>>>()

                val setTeacherId = it.getTeacherIdSet()

                setTeacherId.forEach { id ->
                    requestTeacherInfor.add(async { repository.getTeacherInformationByUserID(id) })
                }

                val liTeacher = requestTeacherInfor.awaitAll()
                val mLiTeacher = mutableListOf<AccountData>()

                for (item in liTeacher) {

                    when (item) {
                        is Resource.Success -> {
                            Log.e("TAG", "setTeacherid: ${item.value} ")
                            mLiTeacher.add(item.value)
                        }
                    }
                }
                this@MainViewModel.liTeacher.value = mLiTeacher
            }
        }
    }



    fun fetchAllJobTypeAPI() = viewModelScope.launch {
        repository.getAllJobType().let {
            when (it) {
                is Resource.Success -> {
                    jobTypeAPI.value = it.value!!
                }
            }
        }
    }

    fun getJobNameByID(id: Int): String? {
        return jobTypeAPI.value?.find { it.job_id == id }?.job_name
    }

    fun getOrderStatusApi() = viewModelScope.launch {
        repository.getOrderStatuses().let {
            when (it) {
                is Resource.Success -> {
                    orderStatusAPI.value = it.value!!
                    repository.saveOrderStatuses((orderStatusAPI.value!!))
                }
            }
        }
    }

    fun getOrderOptionApi() = viewModelScope.launch {
        repository.getOrderOptions().let {
            when (it) {
                is Resource.Success -> {
                    orderOptionAPI.value = it.value!!
                    repository.saveOrderOptions((orderOptionAPI.value!!))
                }
            }
        }
    }

    fun getOrderTypeApi() = viewModelScope.launch {
//        _orderTypeAPI.value = repository.getOrderTypes()
//        if (_orderTypeAPI.value is Resource.Success) {
//            repository.saveOrderTypes((_orderTypeAPI.value as Resource.Success<List<OrderType>>).value)
//        }
        repository.getOrderTypes().let {
            when (it) {
                is Resource.Success -> {
                    orderTypeAPI.value = it.value!!
                    repository.saveOrderTypes((orderTypeAPI.value!!))
                }
            }
        }
    }

    fun getOrderLevelApi() = viewModelScope.launch {
        repository.getOrderLevel().let {
            when (it) {
                is Resource.Success -> {
                    orderLevelAPI.value = it.value!!
                    repository.saveOrderLevels((orderLevelAPI.value!!))
                }
            }
        }
    }
}