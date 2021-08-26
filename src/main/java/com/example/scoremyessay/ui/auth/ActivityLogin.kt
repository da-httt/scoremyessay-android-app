package com.example.scoremyessay.ui.auth


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.viewpager.widget.ViewPager
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.network.iNetwork.IOrderApi
import com.example.scoremyessay.ui.main.ActivityMain
import com.example.scoremyessay.R

import com.example.scoremyessay.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout

class ActivityLogin : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

//    private val tabLayout: TabLayout = findViewById(R.id.tab_layout)
//    private val viewPager: ViewPager = findViewById(R.id.view_pager)

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var imageBG : ImageView
    private lateinit var fragmentLayout : ConstraintLayout


    private val manager = supportFragmentManager
    private lateinit var transaction : FragmentTransaction
    private val loginFragment: LoginFragment by lazy { LoginFragment() }
    private val signUpFragment: SignUpFragment by lazy { SignUpFragment() }

    private lateinit var orderApiBuilder: IOrderApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userPreferences = UserPreferences(this)

        //
        userPreferences.accessToken.asLiveData().observe(this, Observer {
            if (it != null)
            {
                startActivity(Intent(this, ActivityMain::class.java))
                finish()
            }
        })

        setContentView(R.layout.activity_login)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // kiểm tra đã lữu token
//        isFirstInstallApp()

//      show login fragment
        showFirstFragment()
//      setUpTabs()
    }
//    private fun isFirstInstallApp()
//    {
//        dataLocalInstance.setFirstInstalled(false)
//        if(!DataLocalManager.getInstance(applicationContext).getFirstInstalled!!)
//        {
//            orderApiBuilder = ApiBuilder.getApiService(applicationContext)
//            dataPrepareViewModel = PrepareDataModel(orderApiBuilder)
////            observeVariable()
//
//            DataLocalManager.getInstance(applicationContext).setFirstInstalled(true)
//        }
//    }
//    private fun observeVariable()
//    {
//
//        dataPrepareViewModel.getLiOrderStatusesResponse().observe(this,{
//            if(it.status == Status.SUCCESS)
//            {
//                DataLocalManager.getInstance(applicationContext).saveObject(DataLocalManager.ORDER_STATUS, it.data)
////                Log.e("2",it.status.toString())
//////                dataPrepareViewModel.isGetStatusSuccess = true
////                dataPrepareViewModel.isDataCompleteReceive.value = dataPrepareViewModel.isGetAllInformation()
//            }
//        })
//        dataPrepareViewModel.getLiOrderLevelsResponse().observe(this,{
//            if(it.status == Status.SUCCESS)
//            {
//                Log.e("3",it.status.toString())
//                DataLocalManager.getInstance(applicationContext).saveObject(DataLocalManager.ORDER_LEVEL, it.data)
//////                dataPrepareViewModel.isGetLevelSuccess = true
////                dataPrepareViewModel.isDataCompleteReceive.value = dataPrepareViewModel.isGetAllInformation()
//            }
//        })
//        dataPrepareViewModel.getLiOrderTypesResponse().observe(this,{
//        if(it.status == Status.SUCCESS)
//        {
//            Log.e("4",it.status.toString())
////            dataPrepareViewModel.isGetTypeSuccess = true
//            DataLocalManager.getInstance(applicationContext).saveObject(DataLocalManager.ORDER_TYPE, it.data)
//
////            dataPrepareViewModel.isDataCompleteReceive.value = dataPrepareViewModel.isGetAllInformation()
//        }
//    })
//        dataPrepareViewModel.getLiOrderOptionsResponse().observe(this,{
//        if(it.status == Status.SUCCESS)
//        {
//            Log.e("5", it.data?.size.toString())
//            DataLocalManager.getInstance(applicationContext).saveObject(DataLocalManager.ORDER_OPTION, it.data)
////            dataPrepareViewModel.isGetOptionSuccess = true
////
////            dataPrepareViewModel.isDataCompleteReceive.value = dataPrepareViewModel.isGetAllInformation()
//        }
//
//    })
//
////        dataPrepareViewModel.getIsDataCompleteReceive.observe(
////                this,{
////                    if(it == true)
////                    {
////                        Log.e("Da luu","a")
////                        Log.e("da luu 1", dataPrepareViewModel.getLiOrderOptionsResponse().value!!.data?.size.toString())
////                        //lưu vao pre
////                        // chay chuong ttrinhh
////                    }
////        }
////        )
//    }

    private fun showFirstFragment()
    {
        transaction = manager.beginTransaction()
        transaction.add(R.id.fragmentLayout, loginFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun loadFragmentLogin()
    {
        transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, loginFragment).addToBackStack(null).commit()
    }
    fun loadFragmentSignUp()
    {
        transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, signUpFragment).addToBackStack(null).commit()
    }



//    private fun setUpTabs(){
//        val adapter = LoginPagerAdapter(supportFragmentManager)
//        adapter.addFragment(LoginFragment(),"Login")
//        adapter.addFragment(SignUpFragment(),"SignUp")
//        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
//        viewPager.adapter = adapter
//        tabLayout.setupWithViewPager(viewPager)
//    }

}