package com.example.scoremyessay.data.repository

import com.example.scoremyessay.base.BaseRepository
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.model.LoginRequest
import com.example.scoremyessay.data.network.iNetwork.IAuthApi

public class AuthRepository (private val api: IAuthApi,
                             private val preferences: UserPreferences
) : BaseRepository(){
    suspend fun login(loginRequest: LoginRequest) = safeApiCall {
        api.loginUser(loginRequest)
    }
    suspend fun saveAuthToken(token: String){
        preferences.saveAccessTokens(token)
    }

//fun getStatusResponse() : LiveData<Resource<List<OrderStatus>>>
//{
//    val liStatus= MutableLiveData<Resource<List<OrderStatus>>>()
//    disposables.add(
//            IOrderApi.getOrderStatus()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnSubscribe{liStatus.value = Resource.loading(null)}
//                    .subscribe({
//                        when(it.code())
//                        {
//                            200 -> {liStatus.value = Resource.success(it.body()!!.data)}
//                            else -> liStatus.value = Resource.error("Lỗi chưa xác định", null)
//                        }
//                    },{
//                        liStatus.value = Resource.failure("Lỗi mất kết nối tới server",null)
//                    })
//    )
//    return liStatus
//}
//    fun getLoginResponse(loginRequest: LoginRequest) : LiveData<Resource<LoginResponse>>{
//        val loginResponse = MutableLiveData<Resource<LoginResponse>>()
//        disposables.add(IOrderApi.loginUser(loginRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe{loginResponse.value = Resource.loading(null)}
////                .doFinally{showLoading(false)}
//                .subscribe({
//                    when(it.code())
//                    {
//                        200 -> {
//                            loginResponse.value = Resource.success(it.body())}
//                        404 -> loginResponse.value = Resource.error("Lỗi chưa xác đinh",it.body())
//                        422 -> loginResponse.value = Resource.error("Nhập sai tài khoản",it.body())
//                        401 -> loginResponse.value = Resource.error("Nhập sai tài khoản",it.body())
//                        else -> loginResponse.value = Resource.error("Lỗi chưa xác đinh",it.body())
//                    }
//                }, {
//                    loginResponse.value = Resource.failure("Lỗi kết nối server",null)
//                }))
//        return loginResponse
//    }
//
//    fun getRegisterResponse(registerRequest: RegisterRequest) : Observable<RegisterResponse>
//    {
//        return IOrderApi.registerUser(registerRequest)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map { it -> RegisterResponse(it.code()) }
//    }
}
