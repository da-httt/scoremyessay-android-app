package com.example.scoremyessay.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.scoremyessay.ActivityLogin
import com.example.scoremyessay.R
import com.example.scoremyessay.databinding.FragmentLoginBinding
import com.example.scoremyessay.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding

    private lateinit var txtToLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val view = binding?.root

        txtToLogin  = binding?.txtToLogin!!

        txtToLogin.setOnClickListener( object : View.OnClickListener{
            override fun onClick(v: View?) {
                val loginActivity: ActivityLogin = activity as ActivityLogin
                loginActivity.loadFragmentLogin()
            }
        })


        // Inflate the layout for this fragment
        return view
    }
}