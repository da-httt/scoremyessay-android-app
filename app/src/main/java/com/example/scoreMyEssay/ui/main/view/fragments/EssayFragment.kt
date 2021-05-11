package com.example.scoreMyEssay.ui.main.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scoreMyEssay.ui.main.view.ActivityMain
import com.example.scoreMyEssay.data.model.order.AllOrdersServer
import com.example.scoreMyEssay.data.api.retrofit.ApiBuilder
import com.example.scoreMyEssay.data.api.retrofit.SessionManager
import com.example.scoreMyEssay.databinding.FragmentEssayBinding
import retrofit2.Call
import retrofit2.Response


class EssayFragment : Fragment() {
    private var _binding: FragmentEssayBinding? = null
    private val binding get() = _binding
    private lateinit var activityMain: ActivityMain

    private lateinit var apiBuilder: ApiBuilder
    private lateinit var sessionManager: SessionManager
    private lateinit var mainActivity: ActivityMain


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEssayBinding.inflate(inflater,container, false)
        var view = binding?.root

        mainActivity = activity as ActivityMain
        apiBuilder = ApiBuilder(mainActivity)
        sessionManager = SessionManager(mainActivity)

        getAllOrder()


        return view
    }
    private fun getAllOrder()
    {
        apiBuilder.service.getAllOrder().enqueue(object : retrofit2.Callback<AllOrdersServer>{
            override fun onFailure(call: Call<AllOrdersServer>, t: Throwable) {
                Log.e("Loi2",t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<AllOrdersServer>, response: Response<AllOrdersServer>) {
                val allOrdersServer = response.body()
                Log.e("Statuscode",response.code().toString())
            }
        })
    }


}