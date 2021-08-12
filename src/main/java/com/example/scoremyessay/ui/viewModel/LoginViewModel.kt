package com.example.scoremyessay.ui.viewModel

import androidx.lifecycle.*
import com.example.scoremyessay.data.model.LoginRequest
import com.example.scoremyessay.data.model.authentication.login.LoginResponse
import com.example.scoremyessay.data.repository.AuthRepository
import com.example.scoremyessay.utils.Resource
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(loginRequest : LoginRequest) = viewModelScope.launch {
        _loginResponse.value = repository.login(loginRequest)
    }
    suspend fun saveToken(token: String) {
        repository.saveAuthToken(token)
    }
//    private val api = iApiService
//    private val authRepository : AuthRepository = AuthRepository(iApiService)


//    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry() // tim hieu sau
//
//
//    private val loginResponse = MediatorLiveData<Resource<LoginResponse>>()
//
//    val getLoginResponse : LiveData<Resource<LoginResponse>>
//            get() = loginResponse
//
//
//    private val loginRequest = MutableLiveData<LoginRequest>()
//    val getLoginRequest : LiveData<LoginRequest>
//        get() = loginRequest
//
//
//
//
//    @Bindable
//    val userName = MutableLiveData<String>()
//
//    @Bindable
//    val passWord = MutableLiveData<String>()
//
//    fun onLoginButtonClick()
//    {
//        loginRequest.value = LoginRequest(userName.value, passWord.value)
//
//    }
//
//    fun sendLoginRequest()
//        {
////            disposables.add(api.loginUser(loginRequest.value!!)
////            .subscribeOn(Schedulers.io())
////                    .observeOn(AndroidSchedulers.mainThread())
////                    .doOnSubscribe{showLoading(true)}
////                    .doFinally{showLoading(false)}
////                    .subscribe({
////                        when(it.code())
////                        {
////                            200 -> loginResponse.value = it.body()
////                            404 -> showError(BaseResponse("Lỗi", "Chưa xác định", "404"))
////                            422 -> showError(BaseResponse("Lỗi", "Nhập sai tài khoản", "422"))
////                            401 -> showError(BaseResponse("Lỗi", "Nhập sai tài khoản", "401"))
////                        }
////
////                    }, {
////                        showFailure(it)
////                    }))
////            authRepository.loginResponse.observe()
//            loginResponse.addSource(authRepository.getLoginResponse(loginRequest.value!!), Observer {
//                loginResponse.value = it
//            })
//        }
//
//
//    override fun addOnPropertyChangedCallback(
//        callback: Observable.OnPropertyChangedCallback) {
//        callbacks.add(callback)
//    }
//
//    override fun removeOnPropertyChangedCallback(
//        callback: Observable.OnPropertyChangedCallback) {
//        callbacks.remove(callback)
//    }



}