package com.example.scoreMyEssay.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scoreMyEssay.R
import com.example.scoreMyEssay.databinding.ActivityMainBinding
import com.example.scoreMyEssay.data.model.LoginResponse
import com.example.scoreMyEssay.data.api.retrofit.SessionManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityMain : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var sessionManager: SessionManager
    lateinit var fabButton: FloatingActionButton
    lateinit var bottomAppBar: BottomAppBar
    lateinit var topLevelDestination: Set<Int>
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindingView()

        createNavigationBottomAndDrawerLayout()

//        getIntentFromLogin()
        // onclick fab
        fabButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                navController.navigate(R.id.actionCreateEssay)
            }

        })
    }
    private fun navigate(fragment: Int)
    {
        if(fragment in topLevelDestination)
        {

        }
        bottomAppBar.visibility = View.INVISIBLE
        fabButton.visibility = View.INVISIBLE
    }
    private fun bindingView()
    {
        fabButton = binding.fabbutton
        bottomAppBar = binding.bottomAppBar

        bottomAppBar.bringToFront()
        fabButton.bringToFront()
    }
    private fun createNavigationBottomAndDrawerLayout(){
        navController = findNavController(R.id.nav_host_fragment)
        //bottom nav
        binding.bottomNavigationView.setupWithNavController(navController)
        //drawer
        topLevelDestination = setOf(R.id.actionHome, R.id.actionCart, R.id.actionEssay, R.id.actionMe)
        appBarConfiguration = AppBarConfiguration(
                topLevelDestination,
                binding.drawerLayout
        )
        binding.navView.setupWithNavController(navController)

        //
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    private fun getIntentFromLogin()
    {
        sessionManager = SessionManager(this)
        val loginResponse = intent.extras?.get("LoginResponse") as LoginResponse
        sessionManager.saveAuthToken(loginResponse.authToken)
    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }
}


