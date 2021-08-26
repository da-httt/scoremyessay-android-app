package com.example.scoremyessay.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.scoremyessay.databinding.ActivityTest2Binding
import com.example.scoremyessay.utils.CustomDialog
import com.example.scoremyessay.utils.MyCustomDialog
import java.io.File
import java.util.*

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTest2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyCustomDialog.newInstance().show(supportFragmentManager,MyCustomDialog.TAG)
    }


}

//    fun View.resize(
//        newWidth: Int = layoutParams.width, // pixels
//        newHeight: Int = layoutParams.height // pixels
//    ){
//        layoutParams.apply {
//            width = newWidth // in pixels
//            height = newHeight // in pixels
//            layoutParams = this
//        }
//    }
