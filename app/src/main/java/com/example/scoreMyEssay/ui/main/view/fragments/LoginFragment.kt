package com.example.scoreMyEssay.ui.main.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.scoreMyEssay.R
import com.example.scoreMyEssay.ui.main.view.auth.ActivityLogin
import com.example.scoreMyEssay.data.api.retrofit.ApiBuilder
import com.example.scoreMyEssay.data.api.retrofit.SessionManager
import com.example.scoreMyEssay.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    private lateinit var loginActivity: ActivityLogin

    private lateinit var btnToRegister: Button
    private lateinit var apiBuilder: ApiBuilder
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginActivity = activity as ActivityLogin

//        _binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//        _binding = FragmentLoginBinding.inflate(inflater,container,false).apply { viewmo }
        val view = binding?.root

//        btnToRegister = binding?.btnToRegister!!

        btnToRegister.setOnClickListener {
            loginActivity.loadFragmentSignUp()
        }
        apiBuilder = ApiBuilder(loginActivity)
        sessionManager = SessionManager(loginActivity)


//        binding?.btnLogin?.setOnClickListener{
//            apiBuilder.service.loginUser(LoginRequest("test@gmail.com","test") )
//                .enqueue(object : retrofit2.Callback<LoginResponse>{
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        Log.e("Loi2",t.printStackTrace().toString())
//                    }
//
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val loginResponse = response.body()
//                        Log.e("status", response.code().toString())
//                        Log.e("url", response.raw().request().url().toString())
//                        if (response.code() == 200) {
//                            sessionManager.saveAuthToken(loginResponse!!.authToken)
//
//                            Log.e("Login.token:",loginResponse.authToken)
//                            //do sth
//                            val intent = Intent(loginActivity, ActivityMain::class.java)
//                            intent.putExtra("LoginResponse", loginResponse)
//                            startActivity(intent)
//
//
//                        } else {
//                            Log.e("Loi1","1")
//                            Log.e("Loi1",response.errorBody().toString())
//                            // Error logging in
//                        }
//                    }
//                })
//        }
//
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}