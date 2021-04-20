package com.example.scoremyessay

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import com.example.scoremyessay.databinding.ActivityTestBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class ActivityTest : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding

    private lateinit var textDate: TextInputEditText
    private lateinit var cal :Calendar
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)



        cal = Calendar.getInstance()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DATE, dayOfMonth)
                updateDateInView()
            }
        }
        textDate.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                DatePickerDialog(this@ActivityTest,dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()

            }
        })



    }
    private fun updateDateInView(){
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.US)
        textDate.setText(sdf.format(cal.time))
    }
}
