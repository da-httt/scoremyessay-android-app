package com.example.scoremyessay

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.scoremyessay.databinding.ActivityTestBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class ActivityTest : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        //bottom nav
        binding.bottomNavigationView.setupWithNavController(navController)
        //drawer
        appBarConfiguration = AppBarConfiguration(
                setOf(R.id.actionHome, R.id.actionCart, R.id.actionEssay,R.id.actionCreateEssay,R.id.actionMe),
                binding.drawerLayout
        )
        binding.navView.setupWithNavController(navController)

        //
        setupActionBarWithNavController(navController, appBarConfiguration)



    }
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }
}
