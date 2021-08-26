package com.example.scoremyessay.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scoremyessay.R
import com.example.scoremyessay.base.BaseActivity
import com.example.scoremyessay.base.ViewModelFactory
import com.example.scoremyessay.data.UserPreferences
import com.example.scoremyessay.data.network.iNetwork.IOrderApi
import com.example.scoremyessay.data.repository.OrdersRepository
import com.example.scoremyessay.databinding.ActivityMainBinding
import com.example.scoremyessay.utils.LoadingDialog
import com.example.scoremyessay.utils.MyCustomDialog
import com.example.scoremyessay.utils.Resource
import com.example.scoremyessay.viewModel.MainViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ActivityMain : BaseActivity<MainViewModel, ActivityMainBinding, OrdersRepository>(){

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var fabButton: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var topLevelDestination: Set<Int>

    private val loadingDialog : LoadingDialog by lazy{ LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = getActivityBinding(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(applicationContext)
        val factory = ViewModelFactory(getActivityRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        requestServer()

        viewModel.allEssay.observe(this, {
            item ->
            Log.e("Essay", item.map { it.result?.grade }.toString())
        })

        bindingView()

        createNavigationBottomAndDrawerLayout()
        initListener()
        initObserver()

        isBasicDataSaveInLocal()
    }

    private fun initObserver() {
        viewModel.mMyStaticResponse.observe(this, {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success ->{
                    loadingDialog.dismissDialog()
                    viewModel.mMyStatic.value = it.value
                }
            }
        })
    }

    private fun requestServer() {
        viewModel.getAllEssay()
        viewModel.fetchMyInformation()
    }

    private fun isBasicDataSaveInLocal() {

        //test
        loadingDialog.startLoading()
        viewModel.fetchAllJobTypeAPI()
        viewModel.getOrderStatusApi()
        viewModel.getOrderOptionApi()
        viewModel.getOrderTypeApi()
        viewModel.getOrderLevelApi()
        viewModel.fetchMyStatics()
        //vai bua mo cai nay
//        userPreferences.orderStatus.asLiveData().observe(this, {
//            if (it == null) {
//                viewModel.getOrderStatusApi()
//            }
//        })
//        userPreferences.orderOption.asLiveData().observe(this, {
//            if (it == null) {
//                viewModel.getOrderOptionApi()
//            }
//        })
//        userPreferences.orderType.asLiveData().observe(this, {
//            if (it == null) {
//                viewModel.getOrderTypeApi()
//            }
//        })
//        userPreferences.orderLevel.asLiveData().observe(this, {
//            if (it == null) {
//                viewModel.getOrderLevelApi()
//            }
//        })
    }

    private fun initListener() {
        fabButton.setOnClickListener {
            navigateFragment(R.id.actionCreateEssay)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.actionCreateEssay,R.id.actionEnterEssay, R.id.createOptions, R.id.paymentFragment, R.id.addImageFragment-> {
                    binding.fabButton.visibility = View.INVISIBLE
                    binding.bottomAppBar.visibility = View.INVISIBLE
                    supportActionBar?.hide()
                }
                R.id.actionMe ->{
                    binding.fabButton.visibility = View.INVISIBLE
                    binding.bottomAppBar.visibility = View.INVISIBLE
                }
                R.id.viewDetailFragment ->{
                    binding.fabButton.visibility = View.INVISIBLE
                    binding.bottomAppBar.visibility = View.INVISIBLE
                }
                else -> {
                    Log.e("TAG", "initListener: cai cuooi", )
                    binding.bottomAppBar.visibility = View.VISIBLE
                    binding.fabButton.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
            }
        }
    }

    fun navigateFragment(viewId: Int) {
        navController.navigate(viewId)
    }

    private fun bindingView() {
        fabButton = binding.fabButton
        bottomAppBar = binding.bottomAppBar

        bottomAppBar.bringToFront()
        fabButton.bringToFront()
    }

    private fun createNavigationBottomAndDrawerLayout() {
        navController = findNavController(R.id.nav_host_fragment)
        //bottom nav
        binding.bottomNavigationView.setupWithNavController(navController)
        //drawer
        topLevelDestination =
            setOf(R.id.actionHome, R.id.actionCart, R.id.actionEssay)
        appBarConfiguration = AppBarConfiguration(
            topLevelDestination,
            binding.drawerLayout
        )
        binding.navView.setupWithNavController(navController)

        //
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    //    private fun getIntentFromLogin()
//    {
//        sessionManager = SessionManager(this)
//        val loginResponse = intent.extras?.get("LoginResponse") as LoginResponse
//        sessionManager.saveAuthToken(loginResponse.authToken)
//    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun getViewModel() = MainViewModel::class.java
    override fun getActivityBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(inflater)

    override fun getActivityRepository() = runBlocking {
        userPreferences.accessToken.first().let {
            OrdersRepository(remoteDataSource.buildApi(IOrderApi::class.java, it), userPreferences)
        }
    }
}