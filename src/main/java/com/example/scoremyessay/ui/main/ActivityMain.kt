package com.example.scoremyessay.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
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
import com.example.scoremyessay.databinding.ActivityMainBinding
import com.example.scoremyessay.data.network.iNetwork.IOrderApi
import com.example.scoremyessay.data.repository.OrdersRepository
import com.example.scoremyessay.ui.viewModel.MainViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ActivityMain : BaseActivity<MainViewModel, ActivityMainBinding, OrdersRepository>() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var fabButton: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var topLevelDestination: Set<Int>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = getActivityBinding(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(applicationContext)
        val factory = ViewModelFactory(getActivityRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        requestServer()
//        viewModel.getAllUser()

        viewModel.allEssay.observe(this, Observer {
            Log.e("Essay", it.toString())
            viewModel.getAllTeacherInfo()
        })

        bindingView()

        createNavigationBottomAndDrawerLayout()
        initListener()

        isBasicDataSaveInLocal()
    }

    private fun requestServer() {
        viewModel.getAllEssay()
    }

    private fun isBasicDataSaveInLocal() {
        userPreferences.orderStatus.asLiveData().observe(this, {
            if (it == null) {
                viewModel.getOrderStatusApi()
            }
        })
        userPreferences.orderOption.asLiveData().observe(this, {
            if (it == null) {
                viewModel.getOrderOptionApi()
            }
        })
        userPreferences.orderType.asLiveData().observe(this, {
            if (it == null) {
                viewModel.getOrderTypeApi()
            }
        })
        userPreferences.orderLevel.asLiveData().observe(this, {
            if (it == null) {
                viewModel.getOrderLevelApi()
            }
        })
    }

    private fun initListener() {
        fabButton.setOnClickListener {
            navigateFragment(R.id.actionCreateEssay)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.actionCreateEssay -> {
                    binding.fabbutton.visibility = View.INVISIBLE
                    binding.bottomAppBar.visibility = View.INVISIBLE
                }
                else -> {
                    binding.bottomAppBar.visibility = View.VISIBLE
                    binding.fabbutton.visibility = View.VISIBLE
                }
            }
        }
    }

    fun navigateFragment(viewId: Int) {
        navController.navigate(viewId)
    }

    private fun navigate(fragment: Int) {
        if (fragment in topLevelDestination) {

        }
        bottomAppBar.visibility = View.INVISIBLE
        fabButton.visibility = View.INVISIBLE
    }

    private fun bindingView() {
        fabButton = binding.fabbutton
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
            setOf(R.id.actionHome, R.id.actionCart, R.id.actionEssay, R.id.actionMe)
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
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
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