//package com.example.scoreMyEssay.data.repository
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import com.example.scoreMyEssay.data.network.iNetwork.IOrderApi
//import com.example.scoreMyEssay.data.model.ordersList.OrderItem
//import com.example.scoreMyEssay.data.model.orderAttribute.result.OrderResultAPI
//import com.example.scoreMyEssay.data.model.ordersList.AllOrdersAPI
//import com.example.scoreMyEssay.utils.Resource
//import io.reactivex.Observable
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import retrofit2.Response
//
//public class EssayRepository (private val IOrderApi: IOrderApi){
//
//    private val disposables = CompositeDisposable()
//
//    fun getAllEssayObservable(): Observable<Response<AllOrdersAPI>>
//    {
//        return IOrderApi.getAllOrders()
//                .map { api -> {
//                    when(api.code())
//                    {
//                        200 -> {liOrderItem.value = Resource.success(it.body()!!.data)}
//                        else -> liOrderItem.value = Resource.error("Lỗi chưa xác định", null)
//                    }
//                } }
//    }
//    fun getResultByIdObservable(orderID: Int): Observable<Response<OrderResultAPI>>
//    {
//        return IOrderApi.getResultByOrderId(orderID)
//    }
//
//
//    fun getAllEssay(): MutableLiveData<Resource<List<OrderItem>>>
//    {
//        val liOrderItem = MutableLiveData<Resource<List<OrderItem>>>()
//        disposables.add(
//            IOrderApi.getAllOrders()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe{liOrderItem.value = Resource.loading(null)}
//                .subscribe({
//                    when(it.code())
//                    {
//                        200 -> {liOrderItem.value = Resource.success(it.body()!!.data)}
//                        else -> liOrderItem.value = Resource.error("Lỗi chưa xác định", null)
//                    }
//                },{
//                    liOrderItem.value = Resource.failure("Lỗi mất kết nối tới server",null)
//                })
//
//        )
//        return liOrderItem
//    }
//    fun getResultByOrderID(orderID: Int) : MutableLiveData<Resource<OrderResultAPI>>
//    {
//        val resultOfEssay = MutableLiveData<Resource<OrderResultAPI>>()
//        disposables.add(
//                IOrderApi.getResultByOrderId(orderID)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnSubscribe{resultOfEssay.value = Resource.loading(null)}
//                        .subscribe({
//                            when(it.code())
//                            {
//                                200 -> {resultOfEssay.value = Resource.success(it.body()!!)}
//                                else -> {
//                                    Log.e("loi", it.code().toString())
//                                    resultOfEssay.value = Resource.error("Lỗi chưa xác định", null)
//                                }
//                            }
//                        },{
//                            resultOfEssay.value = Resource.failure(it.message!!,null)
//                        })
//        )
//        return resultOfEssay
//    }
////    fun get
//}
//
