package com.example.scoremyessay.ui.auth

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.scoremyessay.base.BaseFragment
import com.example.scoremyessay.utils.LoadingDialog
import com.example.scoremyessay.data.model.*

import com.example.scoremyessay.data.network.iNetwork.IAuthApi
import com.example.scoremyessay.data.repository.AuthRepository

import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.viewModel.LoginViewModel
import com.example.scoremyessay.utils.Resource
import com.example.scoremyessay.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding, AuthRepository>() {
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }
    private val loadingDialog : LoadingDialog by lazy{ LoadingDialog(requireActivity()) }
//    override fun test() {
//        dangnhaptudong()
//    }

    override fun handleTask() {
        initObserve()
        initListener()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener {
            val loginRequest = LoginRequest(binding.txtUsername.text.toString(),binding.txtPassWord.text.toString())
//            val loginRequest = LoginRequest("student@gmail.com","student")
            if(isValidInput(loginRequest))
            {
                viewModel.login(loginRequest)
            }
        }
    }

    fun initObserve() {
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer{
            loadingDialog.dismissDialog()
            when(it){
                is Resource.Loading -> {
                    loadingDialog.startLoading()
                }
                is Resource.Success ->{
                    lifecycleScope.launch{
                        Log.e("TAG", "initObserve: my token", )
                        viewModel.saveToken(it.value.authToken)
                        startActivity(Intent(requireActivity(), ActivityMain::class.java))
                        requireActivity().finish()
                        Toast.makeText(requireContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                    }
                    loadingDialog.dismissDialog()
                }
                is Resource.Failure ->{
                    when(it.isNetworkError){
                        true -> Toast.makeText(requireContext(), "Kiểm tra lại kết nối mạng", Toast.LENGTH_LONG).show()
                        false -> {
                            Log.e("s",it.errorCode.toString())
                            when (it.errorCode)
                            {
                                401 -> Toast.makeText(requireContext(), "Bạn nhập sai tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                    loadingDialog.dismissDialog()
                }
            }
        })

//        binding.btnLogin.setOnClickListener{
//            val email = binding.txtUsername.text.toString().trim()
//            val pass = binding.txtPassWord.text.toString().trim()
//            val loginRequest = LoginRequest(email,pass)
//
//
//            if(isValidInput(loginRequest))
//            {
//                loadingDialog.startLoading()
//                viewModel.login(loginRequest)
//            }
//        }
    }

    private fun dangnhaptudong(){

        val loginRequest = LoginRequest("student@gmail.com","student")
        if(isValidInput(loginRequest))
        {
            loadingDialog.startLoading()
            viewModel.login(loginRequest)
        }
    }


    private fun isValidInput(loginRequest: LoginRequest): Boolean
    {
        var valid: Boolean = true
        if(!loginRequest.isEmailValid())
        {
            binding!!.txtUsername.error = "Xin nhập email hợp lệ";
            valid = false
        }
        if(!loginRequest.isPassWordGreaterThan5())
        {
            binding!!.txtPassWord.error = "Mật khẩu phải có ít nhất 6 ký tự"
            valid = false
        }
        if(loginRequest.isEmailEmpty())
        {
            binding!!.txtUsername.error = "Tên đăng nhập không được rỗng";
            valid = false
        }
        if(loginRequest.isPasswordEmpty())
        {
            binding!!.txtPassWord.error = "Mật khẩu không được rỗng";
            valid = false
        }
        return valid
    }
    override fun getViewModel() = LoginViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentLoginBinding.inflate(inflater, container, false)
    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(IAuthApi::class.java), userPreferences)
    override fun onStop() {
        super.onStop()
    }


//    private var _binding: FragmentLoginBinding? = null
//    private val binding get() = _binding
//    private lateinit var loginActivity: ActivityLogin
//
//    private lateinit var apiBuilder: IOrderApi
//
//
//    private lateinit var loginActivityViewModel: LoginViewModel
//    private lateinit var dataLocalInstance :DataLocalManager
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        setupBasic()
//        setupViewModel()
//        _binding = FragmentLoginBinding.inflate(inflater,container,false).apply {
//            this.lifecycleOwner = requireActivity()
//            this.viewModel = loginActivityViewModel
//        }
//        return binding?.root
//    }
//    override fun setupBasic() {
//        loginActivity = activity as ActivityLogin
//        apiBuilder = ApiBuilder.getApiService(loginActivity)
//        dataLocalInstance = DataLocalManager.getInstance(requireContext())
//    }
//
//    override fun getRootLayoutId(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun getContext(context: Context): Context {
//        return requireContext()
//    }
//
//    override fun setupViewModel() {
//        loginActivityViewModel = LoginViewModel(apiBuilder)
//        loginActivityViewModel.userName.value = "student@gmail.comm"
//        loginActivityViewModel.passWord.value = "student"
//
//    }
//    override fun setupUI(view: View) {
////        loginActivityViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
////        _binding = DataBindingUtil.setContentView(requireActivity(), R.layout.activity_login)
//        initListener()
//        registerObserver()
////        binding?.btnLogin?.setOnClickListener{
////            apiBuilder.serviceI.loginUser(com.example.scoremyessay.data.model.LoginRequest("test@gmail.com","test") )
////                .enqueue(object : retrofit2.Callback<LoginResponse>{
////                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
////                        Log.e("Loi2",t.printStackTrace().toString())
////                    }
////
////                    override fun onResponse(
////                        call: Call<LoginResponse>,
////                        response: Response<LoginResponse>
////                    ) {
////                        val loginResponse = response.body()
////                        Log.e("status", response.code().toString())    .e("url", response.raw().request().url().toString())
////                        if (response.code() == 200) {
////                            sessionManager.saveAuthToken(loginResponse!!.authToken)
////
////                            Log.e("Login.token:",loginResponse.authToken)
////                            //do sth
////                            val intent = Intent(loginActivity, ActivityMain::class.java)
////                            intent.putExtra("LoginResponse", loginResponse)
////                            startActivity(intent)
////
////
////                        } else {
////                            Log.e("Loi1","1")
////                            Log.e("Loi1",response.errorBody().toString())
////                            // Error logging in
////                        }
////                    }
////                })
////        }
////
//    }
//
//    private fun registerObserver()
//    {
//        checkValidInputObserver()
//        loginAPIResponse()
////        setObserveLive(loginActivityViewModel)
//    }
//    private fun checkValidInputObserver()
//    {
//        loginActivityViewModel.getLoginRequest.observe(requireActivity(),
//            {
//                if(!CheckValidUserInput.isEmailValid(loginActivityViewModel.getLoginRequest.value!!))
//                {
//                    binding!!.txtUsername.error = "Xin nhập email hợp lệ";
//                }
//                else if(!CheckValidUserInput.isPassWordGreaterThan5(loginActivityViewModel.getLoginRequest.value!!))
//
//                {
//                    binding!!.txtPassWord.error = "Mật khẩu phải có ít nhất 6 ký tự"
//                }
//                else if(CheckValidUserInput.isEmailEmpty(loginActivityViewModel.getLoginRequest.value!!))
//                {
//                    binding!!.txtUsername.error = "Tên đăng nhập không được rỗng";
//                }
//                else if(CheckValidUserInput.isPasswordEmpty(loginActivityViewModel.getLoginRequest.value!!))
//                {
//                    binding!!.txtPassWord.error = "Mật khẩu không được rỗng";
//                }
//                else{
//                    loginActivityViewModel.sendLoginRequest()
//                }
//            })
//    }
//    private fun loginAPIResponse()
//    {
//        loginActivityViewModel.getLoginResponse.observe(requireActivity(),
//                {
//                    when (it.status) {
//                        Status.SUCCESS -> {Toast.makeText(requireContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
//                            dataLocalInstance.saveUserToken( it.data!!.authToken)
//
//                            //do sth
//                            val intent = Intent(loginActivity, ActivityMain::class.java)
//                            startActivity(intent)
//                            }
////                        Status.LOADING -> showLoadingDialog()
//                        Status.ERROR -> it.message?.let { it -> showError(it) }
//                        Status.Failure -> it.message?.let { it -> showError(it) }
//                    }
//                    if(it.status != Status.LOADING)
//                    {
//                        hideLoadingDialog()
//                    }
//                })
//    }
//    private fun initListener() {
//        binding?.btnToRegister?.setOnClickListener {
//            loginActivity.loadFragmentSignUp()
//        }
//    }
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

}