package com.example.scoremyessay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.scoremyessay.databinding.ActivityMainBinding
import com.example.scoremyessay.fragments.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_ScoreMyEssay)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        setSupportActionBar(binding.toolBar)
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        val homeFragment = HomeFragment()
        val essayFragment = EssayFragment()
        val createEssayFragment = CreateEssayFragment()
        val cartFragment = CartFragment()
        val meFragment = MeFragment()

        navController = findNavController(R.id.hostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this,navController)

        NavigationUI.setupWithNavController(binding.navigationView,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}