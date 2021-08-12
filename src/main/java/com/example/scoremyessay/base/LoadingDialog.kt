package com.example.scoremyessay.base

import android.app.Activity
import android.app.AlertDialog
import com.example.scoremyessay.R

class LoadingDialog(private val activity : Activity) {
    private lateinit var dialog: AlertDialog
    fun startLoading()
    {
        //set view
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.item_loading, null)
        //** set dialog */
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }
    fun dismissDialog()
    {
        dialog.dismiss()
    }
}