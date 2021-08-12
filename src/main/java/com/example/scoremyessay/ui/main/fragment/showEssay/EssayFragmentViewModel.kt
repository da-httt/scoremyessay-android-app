package com.example.scoremyessay.ui.main.fragment.showEssay

import androidx.lifecycle.ViewModel
import com.example.scoremyessay.data.network.iNetwork.IOrderApi
//import com.example.scoremyessay.data.repository.EssayRepository

class EssayFragmentViewModel(iOrderApi: IOrderApi) : ViewModel(){
//    private val api = iOrderApi
//    private val repos = EssayRepository(api)
//
//
//     var liAllEssay = MutableLiveData<Resource<List<OrderItem>>>()
//     var liResult = MutableLiveData<MutableList<MutableLiveData<Resource<OrderResultAPI>>>>()
////    private var
//    fun getLiAllEssay(): LiveData<Resource<List<OrderItem>>> {
////        liAllEssay = repos.getAllEssay()
//        repos.getAllEssay()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//        return liAllEssay
//    }
////    var getUser()
////    {
////        repos.getAllEssay().map
////    }
//    var order = MutableLiveData<Resource<OrderResultAPI>>()
//    fun getResultByOrderID(orderID: Int): MutableLiveData<Resource<OrderResultAPI>> {
//
//        order = repos.getResultByOrderID(orderID)
//        return order
//    }
}