package com.example.scoremyessay.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.example.scoremyessay.ActivityLogin
import com.example.scoremyessay.R
import com.example.scoremyessay.databinding.FragmentLoginBinding
import com.example.scoremyessay.databinding.FragmentSignUpBinding
import java.text.SimpleDateFormat
import java.util.*


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding

    private lateinit var txtToLogin: TextView

    private lateinit var txtBirthDate: EditText
    private lateinit var cal : Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val view = binding?.root

        txtToLogin  = binding?.txtToLogin!!
        txtBirthDate = binding?.txtBirthDate!!

        txtToLogin.setOnClickListener( object : View.OnClickListener{
            override fun onClick(v: View?) {
                val loginActivity: ActivityLogin = activity as ActivityLogin
                loginActivity.loadFragmentLogin()
            }
        })

        cal = Calendar.getInstance()

        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DATE, dayOfMonth)
                updateDateInView()
            }
        }
        txtBirthDate.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                DatePickerDialog( activity as ActivityLogin,dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()

            }
        })




        // Inflate the layout for this fragment
        return view
    }
    private fun updateDateInView(){
        val myFormat = "MM/dd/yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.US)
        txtBirthDate.setText(sdf.format(cal.time))
    }
}