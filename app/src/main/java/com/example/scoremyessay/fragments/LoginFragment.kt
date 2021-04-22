package com.example.scoremyessay.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.scoremyessay.ActivityLogin
import com.example.scoremyessay.databinding.FragmentLoginBinding
import com.example.scoremyessay.model.LoginRequest
import com.example.scoremyessay.model.LoginResponse
import com.example.scoremyessay.retrofit.RetrofiClient
import com.example.scoremyessay.retrofit.SessionManager
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private lateinit var loginActivity: ActivityLogin

    private lateinit var txtToRegister: TextView
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginActivity = activity as ActivityLogin
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding?.root

        txtToRegister = binding?.txtToRegister!!

        txtToRegister.setOnClickListener {
            loginActivity.loadFragmentSignUp()
        }
        sessionManager = SessionManager(loginActivity)

        binding?.btnLogin?.setOnClickListener{
            RetrofiClient.service.loginUser(LoginRequest(binding?.edtUsername?.text!!.toString(), binding?.edtPassword?.text!!.toString()) )
                .enqueue(object : retrofit2.Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val loginResponse = response.body()

                        if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
                            sessionManager.saveAuthToken(loginResponse.authToken)
                            Log.e("Login.token:",loginResponse.authToken)
                        } else {
                            // Error logging in
                        }
                    }
                })
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}